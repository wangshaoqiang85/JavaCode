package control;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
import dto.GameDto;
import dto.Player;
import service.GameService;
import service.GameTetris;
import ui.window.JFrameConfig;
import ui.window.JFrameGame;
import ui.window.JFrameSavePoint;
import ui.window.JPanelGame;

/*
 * ������ҿ���
 * ������Ҽ����¼�
 * ���ƻ���
 * ������Ϸ�߼�
 */
public class GameControl {
	
	/*
	 * ���ݷ��ʽӿ�A
	 */
	private Data dataA;
	/*
	 * ���ݷ��ʽӿ�B
	 */
	private Data dataB;
	/*
	 * ��Ϸ�߼��� 
	 */
	private GameService gameService;
	/*
	 * ��Ϸ�����
	 */
	private JPanelGame panelGame;
	/*
	 * ��Ϸ���ÿ��ƴ���
	 */
	private JFrameConfig frameConfig;
	/*
	 * �����������
	 */
	private JFrameSavePoint frameSavePoint;
	/*
	 * ��Ϸ��Ϊ����
	 */
	private Map<Integer, Method> actionList;
	/*
	 *��Ϸ����Դ 
	 */
	private GameDto dto = null;	
	/*
	 * ��Ϸ�߳�
	 */
	private Thread gameThread = null;
	
	public GameControl() {
		//������Ϸ����Դ
		this.dto = new GameDto();
		//������Ϸ�߼���(������Ϸ����Դ)
		this.gameService = new GameTetris(dto);
		//�����ݽӿ�A������ݿ��¼
		this.dataA = createDataObject(GameConfig.getDataConfig().getDataA());
		//�������ݿ��¼����Ϸ
		this.dto.setDbRecode(dataA.loadData());
		//�����ݽӿ�B������ݿ��¼
		this.dataB = createDataObject(GameConfig.getDataConfig().getDataB());
		//���ñ��ش��̼�¼����Ϸ
		this.dto.setDiskRecode(dataB.loadData());
		//������Ϸ���
		this.panelGame = new JPanelGame(this, dto);
		//��ȡ�û���������
		this.setControlConfig();
		//��ʼ�û����ô���
		this.frameConfig =new JFrameConfig(this);
		//��ʼ�������������
		this.frameSavePoint = new JFrameSavePoint(this);
		//��ʼ����Ϸ������,��װ��Ϸ���
		new JFrameGame(this.panelGame);
		
	}
	
	/*
	 * ��ȡ�û���������
	 */
	private void setControlConfig() {
		//�����������뷽������ӳ������
		this.actionList = new HashMap<Integer, Method>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/control.dat"));
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>)ois.readObject();
			Set<Entry<Integer, String>> entryset = cfgSet.entrySet();
			for(Entry<Integer, String> e : entryset) {
				actionList.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 *�������ݶ��� 
	 */
	private Data createDataObject(DataInterfaceConfig cfg) {
		try {
			//��������
			Class<?> cls = Class.forName(cfg.getClassName());
			//��ù�����
			Constructor<?> ctr = cls.getConstructor(HashMap.class);
			//��������
			return (Data)ctr.newInstance(cfg.getParam());
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}
	/*
	 * ������ҿ�����������Ϊ
	 */
	public void actionByKeyCode(int keyCode) {
		try {
			if(this.actionList.containsKey(keyCode)) {
				this.actionList.get(keyCode).invoke(this.gameService);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		this.panelGame.repaint();
	}	

	/*
	 * ��ʾ��ҿ��ƴ���
	 */
	public void showUserConfig() {
		this.frameConfig.setVisible(true);
	}
	/*
	 * �Ӵ��ڹر��¼�
	 */
	public void setOver() {
		this.panelGame.repaint();
		this.setControlConfig();
	}

	/*
	 * ��ʼ��ť�¼�
	 */
	public void start() {
		//���尴ť����Ϊ���ɵ��
		this.panelGame.buttonSwith(false);
		//�رմ���
		this.frameConfig.setVisible(false);
		this.frameSavePoint.setVisible(false);
		//��Ϸ���ݳ�ʼ��
		this.gameService.startGame();	
		//�����̶߳���
		this.gameThread = new MainThread();
		//�����߳�
		this.gameThread.start();
		//ˢ�»���
		this.panelGame.repaint();	

	}
	
	/*
	 * �������
	 */
	public void savePoint(String name) {
		Player pla = new Player(name, this.dto.getNowPoint());
		//�����¼�����ݿ�
		this.dataA.savaData(pla);
		//�����¼�����ش���
		this.dataB.savaData(pla);
		//�������ݿ��¼����Ϸ
		this.dto.setDbRecode(dataA.loadData());
		//���ñ��ش��̼�¼����Ϸ
		this.dto.setDiskRecode(dataB.loadData());
		//ˢ�»���
		this.panelGame.repaint();
	}
	
	/*
	 * ʧ�ܺ�Ĵ���
	 */
	private void afterLose() {
		//���ѡ�����ף�����ʾ���洰��
		if(!this.dto.isCheat()) {
			//��ʾ����÷ִ���
			this.frameSavePoint.showWindow(this.dto.getNowPoint());
		}
		//this.frameSavePoint.showWindow(this.dto.getNowPoint());
		//ʹ��ť���Ե��
		this.panelGame.buttonSwith(true);
	}
	
	private class MainThread extends Thread {
		public void run() {
			//ˢ�»���
			panelGame.repaint();
			//��ѭ��
			while(dto.isStart()) {
				try {
					//�ȴ�0.5��
					//Thread.sleep(500);
					Thread.sleep(dto.getSleepTime());
					//�������ʱ��
					//System.out.println(dto.getSleepTime());
					//�����ͣ����ô��ִ������Ϊ
					if(dto.isPause()) {
						continue;
					}
					//��Ϸ����Ϊ
					gameService.mainAction();
					//ˢ�»���
					panelGame.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			afterLose();
		}
	}


}
