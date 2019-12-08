package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Player;

public class DataBase implements Data {
	
	//Java连接JDBC驱动
	//private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	//private static String DB_URL = "jdbc:sqlserver://127.0.0.1:1433;database=game_Test";
	private final String dbUrl;
	//private static String DB_UESR = "gn";
	private final String dbUser;
	//private static String DB_PWD  = "gn1234";
	private final String dbPwd;
	
	private static String LOAD_SQL = "SELECT TOP 5 user_name, point FROM user_point WHERE type_id = 1 ORDER BY point DESC";
	
	private static String SAVE_SQL = "INSERT INTO user_point(user_name, point, type_id) VALUES (?,?,?)";
	
	public DataBase(HashMap<String, String> param) {

		this.dbUrl = param.get("dbUrl");
		this.dbUser = param.get("dbUser");
		this.dbPwd = param.get("dbPwd");
		try {
			Class.forName(param.get("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Player> loadData() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Player> players = new ArrayList<Player>();
		try {
			conn =  DriverManager.getConnection(dbUrl, dbUser,dbPwd);
			stmt = conn.prepareStatement(LOAD_SQL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				players.add(new Player(rs.getString(1), rs.getInt(2)));				
				//测试用例
//				System.out.print(rs.getString(1) + "");
//				System.out.print(rs.getString(2));
//				System.out.println();		
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(conn != null) stmt.close();
				if(conn != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return players;
	}

	@Override
	public void savaData(Player players) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn =  DriverManager.getConnection(dbUrl, dbUser,dbPwd);
			stmt = conn.prepareStatement(SAVE_SQL);
			stmt.setObject(1, players.getName());
			stmt.setObject(2, players.getPoint());
			stmt.setObject(3, 1);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/*
	 * 测试能否成功向数据库中添加数据
	 */
//	public static void main(String[] arga) {
//			DataBase db = new DataBase();
//			db.savaData(new Player("GN",8888));
//			System.out.println(123);
//	}
	/*
	 * 测试数据库是否正确链接
	 */
//	public static void main(String[] arga) {
//		DataBase db = new DataBase();
//		List<Player> players = db.loadData();
////		for(Player p : players) {
////			System.out.println(p.getName() + "" +p.getPoint());
////
////		}
//	}
	


}
