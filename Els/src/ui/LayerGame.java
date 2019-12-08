package ui;

import java.awt.Graphics;
import java.awt.Point;

import config.GameConfig;
import entity.GameAct;


public class LayerGame extends Layer {
	/*
	 * 左位移偏移量
	 */
	private static final int SIZE_ROL = GameConfig.getFrameConfig().getSizeRol();
	
	private static final int LEFT_SIDE = 0;
	/*
	 * 右边距
	 */
	private static final int RIGHT_SIDE = GameConfig.getSystemConfig().getMaxX();
	
	private static final int LOSE_IDX = GameConfig.getFrameConfig().getLoseIdx();
	
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}	

	public void paint(Graphics g) {
		this.CreateWindow(g);
		//获得方块数组集合
		GameAct act = this.dto.getGameAct();
		if(act != null) {
			Point[] points = this.dto.getGameAct().getActPoints();
			//绘制阴影
			this.drawShadow(points, g);
			//绘制活动方块
			this.drawMainAct(points, g);
		}
			
		//绘制游戏地图
		this.drawMap(g);
		//暂停
		if(this.dto.isPause()) {
			this.drawImageAtCenter(Img.PAUSE, g);
		}
	}
	/*
	 * 绘制活动方块
	 */
	private void drawMainAct(Point[] points, Graphics g) {
		//获得方块类型编号(0 ~ 6)
		int typeCode = this.dto.getGameAct().getTypeCode();
		//绘制方块
		for(int i = 0; i < points.length; i++) {
			this.drawActByPoint(points[i].x, points[i].y, typeCode + 1, g);
		}	   		
	}
	
	/*
	 * 绘制游戏地图
	 */
	private void drawMap(Graphics g) {
		boolean[][] map = this.dto.getGameMap();
		//计算当前堆积颜色
		int lv = this.dto.getNowLevel();
		int imgIdX = lv == 0 ? 0 : (lv - 1) % 7 + 1;
		for(int x = 0; x < map.length; x++) {
			for(int y = 0; y < map[x].length; y++) {
				if(map[x][y]) {
					//如果是输的情况 imgIdX = 8 
					this.drawActByPoint(x, y, imgIdX, g);

				}
			}
		}		
	}
	/*
	 * 绘制阴影
	 */
	private void drawShadow(Point[] points, Graphics g) {
		if(!this.dto.isShowShadow()) {
			return;
		}
		int leftX = RIGHT_SIDE;
		int rightX = LEFT_SIDE;
		for(Point p : points) {
			leftX = p.x < leftX ? p.x : leftX;
			rightX = p.x > rightX ? p.x : rightX;
		}	
		g.drawImage(Img.SHADOW,
				this.x + BORDER + (leftX << SIZE_ROL), 
				this.y + BORDER,
				(rightX - leftX + 1) << SIZE_ROL,
				this.h - (BORDER << 1),
		null);
		
	}

	/*
	 * 绘制正方形块
	 *
	 */
	private void drawActByPoint(int x, int y, int imgIdx, Graphics g) {
		imgIdx = this.dto.isStart() ? imgIdx : LOSE_IDX;
		g.drawImage(Img.ACT,
				this.x + (x << SIZE_ROL) + BORDER,
				this.y + (y << SIZE_ROL) + BORDER,
				this.x + (x + 1 << SIZE_ROL) + BORDER,
				this.y + (y + 1 << SIZE_ROL) + BORDER,
				imgIdx << SIZE_ROL, 0, (imgIdx + 1) << SIZE_ROL, 1 << SIZE_ROL, null);
				
	}

}
