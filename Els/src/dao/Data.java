package dao;

import java.util.List;

import dto.Player;

public interface Data {
	
	/*
	 * ��ȡ����
	 */
	public List<Player> loadData();
	/*
	 * �洢����
	 */
	public void savaData(Player players);

}
