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
		//��ȡ������
		List<Player> players = this.loadData();
		
		//�жϼ�¼�Ƿ񳬹�5�����������5����ȥ�������͵�
		
		//׷���¼�¼
		players.add(pla);	
		//����д��
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
	 * ��������
	 */
//	public static void main(String[] args) throws Exception{
//		DataDisk dd = new DataDisk();
//		List<Player> players = new ArrayList<Player>();
//		players.add(new Player("����", 1542));
//		players.add(new Player("����", 666 ));
//		players.add(new Player("����", 2032));
//		players.add(new Player("����", 55  ));
//		players.add(new Player("����", 333 ));
//
//		dd.savaData(players);
//		System.out.println("�������");
//		
//		List<Player> dataFromDisk = dd.loadData();
//		
//		for(Player p : dataFromDisk) {
//			System.out.println(p.getName() + ":" + p.getPoint());
//
//		}		
//	}

}
