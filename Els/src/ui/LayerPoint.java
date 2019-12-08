package ui;

import java.awt.Graphics;

import config.GameConfig;

public class LayerPoint extends Layer {
	
	/*
	 * 每个等级中最大行数
	 */
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();
	
	/*
	 * 得分最大位数
	 */
	private static int POINT_BIT = 5;
	
	/*
	 * 分数Y坐标
	 */
	private final int pointY;
	/*
	 * 消行Y坐标
	 */
	private final int rmlineY;
	/*
	 * 经验值Y坐标
	 */
	private final int expY;
	/*
	 * 共通X坐标
	 */
	private final int comX;


	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		// 初始化共通的X坐标
		this.comX = this.w - IMG_NUMBER_W * POINT_BIT - PADDING;
		// 初始化分数显示的y坐标
		this.pointY = PADDING;
		//初始化消行显示的Y坐标
		this.rmlineY = this.pointY + Img.RMLINE.getHeight(null)  + PADDING;
		//初始化经验值显示的Y坐标
		this.expY = this.rmlineY + Img.RMLINE.getHeight(null) + PADDING;
		 //初始化经验值槽的宽度写到父类Layer中
		//this.expW = this.w - (PADDING << 1);
		
	}

	public void paint(Graphics g) {
		this.CreateWindow(g);
		// 绘制标题(分数)
		g.drawImage(Img.POINT, this.x + PADDING, this.y + pointY, null);
		//显示分数
		this.drawNumberLeftPad(comX, pointY, this.dto.getNowPoint(), POINT_BIT, g);
		// 绘制标题(消行)
		g.drawImage(Img.RMLINE, this.x + PADDING, this.y + rmlineY, null);
		//显示消行
		this.drawNumberLeftPad(comX, rmlineY, this.dto.getNowRemoveLine(), POINT_BIT, g);
		//绘制值槽(经验值)
		int rmLine = this.dto.getNowRemoveLine();
		this.drawRect(expY, "下一级", null, (double)(rmLine % LEVEL_UP) / (double)LEVEL_UP, g);
		
 			
	}
		
	/*
	//值槽着色方法
	@Deprecated
	private Color getNowColor(double hp, double maxHp) {
		int colorR = 0;
		int colorG = 255;
		int colorB = 0;
		double hpHalf = maxHp / 2;
		if(hp > hpHalf) {
			colorR = 255 - (int)((hp - hpHalf) / (maxHp / 2) * 255);
			colorG = 255;	
		}
		else {
			colorR = 255;
			colorG = (int)(hp / (maxHp / 2) *255); 
		}
		return new Color(colorR, colorG,colorB);
	}
	*/
	

}
