package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Player;

public class DataTest implements Data {

	public DataTest(HashMap<String, String> param) {
		
	}
	
	@Override
	public List<Player> loadData() {
		List<Player> players = new ArrayList<Player>();
		//players.add(new Player("����", 6542));
		players.add(new Player("����", 666 ));
		players.add(new Player("����", 2032));
		//players.add(new Player("����", 55  ));
		players.add(new Player("����", 333 ));
		return players;
	}

	@Override
	public void savaData(Player players) {
		//System.out.println();	
	}

}
