package entity;

import java.awt.Point;
import java.util.List;

import config.GameConfig;

public class GameAct { 
	
	/*
	 * ��������
	 */
	private Point[] actPoints = null;
	/* 
	 * ������
	 */ 
	private int typeCode;
	
	//д�������ļ�
//	private static int MIN_X = 0;
//	private static int MAX_X = 9;
//	private static int MIN_Y = 0;
//	private static int MAX_Y = 17;
	private static int MIN_X = GameConfig.getSystemConfig().getMinX();
	private static int MAX_X = GameConfig.getSystemConfig().getMaxX();
	private static int MIN_Y = GameConfig.getSystemConfig().getMinY();
	private static int MAX_Y = GameConfig.getSystemConfig().getMaxY();
	//���ˢ�µķ���
	private static List<Point[]> TYPE_CONFIG = GameConfig.getSystemConfig().getTypeConfig();
	//�жϷ����Ƿ�����ת
	private final static List<Boolean> TYPE_ROUND = GameConfig.getSystemConfig().getTypeRound();	

	
//	private static List<Point[]> TYPE_CONFIG;	
//	static {
//		TYPE_CONFIG = new ArrayList<Point[]>(7);
//		TYPE_CONFIG.add(new Point[] {new Point(4,0), new Point(3,0), new Point(5,0), new Point(6,0)});
//		TYPE_CONFIG.add(new Point[] {new Point(4,0), new Point(3,0), new Point(5,0), new Point(4,1)});
//		TYPE_CONFIG.add(new Point[] {new Point(4,0), new Point(3,0), new Point(5,0), new Point(3,1)});
//		TYPE_CONFIG.add(new Point[] {new Point(4,0), new Point(5,0), new Point(3,1), new Point(4,1)});
//		TYPE_CONFIG.add(new Point[] {new Point(4,0), new Point(5,0), new Point(4,1), new Point(5,1)});
//		TYPE_CONFIG.add(new Point[] {new Point(4,0), new Point(3,0), new Point(5,0), new Point(5,1)});
//		TYPE_CONFIG.add(new Point[] {new Point(4,0), new Point(3,0), new Point(4,1), new Point(5,1)});
//	}
	
	public GameAct(int typeCode) {
		this.init(typeCode);
		
	}
	
	public void init(int typeCode) {
		this.typeCode = typeCode;
		Point[] points = TYPE_CONFIG.get(typeCode);
		actPoints = new Point[points.length];
		for(int i = 0; i < points.length;i++) {
			actPoints[i] = new Point(points[i].x, points[i].y);
		}
	}

	public Point[] getActPoints() {
		return actPoints;
	}
	
	/*
	 * �����ƶ�
	 * @parame moveX  X��ƫ����
	 * @parame moveY  Y��ƫ����
	 */
	public boolean move(int moveX, int moveY,boolean[][] gameMap) {
		//�ƶ�����
		 for(int i = 0; i < actPoints.length; i++) {
			 int newX = actPoints[i].x + moveX;
			 int newY = actPoints[i].y + moveY;
		     if(isOverZone(newX,newY, gameMap))
		    	 return false;
		 }
		 for(int i = 0; i < actPoints.length; i++) {
			 actPoints[i].x += moveX;
			 actPoints[i].y += moveY;
		 }
		 return true;
	}
	
	/*
	 * ������ת
	 * ˳ʱ�빫ʽ
	 * A.x = o.y + o.x - B��y
	 * A.y = o.y - o.x + B��y
	 */
	public void round(boolean[][] gameMap) {
		//�жϷ����Ƿ�����ת
		if(!TYPE_ROUND.get(typeCode)) {
			return;
		}
//		if(this.typeCode == 4) {
//			return;
//		}
		
		for(int i = 0; i < actPoints.length; i++) {
			 int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
			 int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
		     if(this.isOverZone(newX, newY, gameMap))
		    	 return;
		}
		for(int i = 0; i < actPoints.length; i++) {
			 int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
			 int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
			 actPoints[i].x = newX;
			 actPoints[i].y = newY;
		}
	
	} 
	/*
	 * �ж��Ƿ񳬳��߽�
	 */
	private boolean isOverZone(int x, int y, boolean[][] gameMap) {
	    return x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y ||  gameMap[x][y] || gameMap[x][y ];

	}

	/*
	 * ��÷������ͱ��
	 */
	public int getTypeCode() {
		return typeCode;
	}




}
