package dao;

import java.util.List;

import dto.Player;

public interface Data {
	
	/*
	 * 读取数据
	 */
	public List<Player> loadData();
	/*
	 * 存储数据
	 */
	public void savaData(Player players);

}
