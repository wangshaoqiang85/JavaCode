package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

import dto.Player;

public class DataDisk implements Data {

	
	private  final String filePath;
	
	public DataDisk(HashMap<String, String> param) {
		this.filePath = param.get("path");
			
	}
	@Override
	public List<Player> loadData() {	
		ObjectInputStream ois = null;
		List<Player> players = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filePath));
			players = (List<Player>)ois.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch(IOException e) {
				e.printStackTrace();			
			}
		}		
		return players;
	}

	@Override
	public void savaData(Player pla) {
		//先取出数据
		List<Player> players = this.loadData();
		
		//判断记录是否超过5条，如果超过5条，去掉分数低的
		
		//追加新纪录
		players.add(pla);	
		//重新写入
		ObjectOutputStream oos = null;
		try {	
			oos = new ObjectOutputStream(new FileOutputStream(filePath));
			oos.writeObject(players);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch(IOException e) {
				e.printStackTrace();			
			}
		}
		
	}
	/*
	 * 测试用例
	 */
//	public static void main(String[] args) throws Exception{
//		DataDisk dd = new DataDisk();
//		List<Player> players = new ArrayList<Player>();
//		players.add(new Player("刘明", 1542));
//		players.add(new Player("刘红", 666 ));
//		players.add(new Player("刘奥", 2032));
//		players.add(new Player("刘三", 55  ));
//		players.add(new Player("刘四", 333 ));
//
//		dd.savaData(players);
//		System.out.println("保存完毕");
//		
//		List<Player> dataFromDisk = dd.loadData();
//		
//		for(Player p : dataFromDisk) {
//			System.out.println(p.getName() + ":" + p.getPoint());
//
//		}		
//	}

}
