package ui;

import java.awt.Graphics;

import config.GameConfig;

public class LayerPoint extends Layer {
	
	/*
	 * ÿ���ȼ����������
	 */
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();
	
	/*
	 * �÷����λ��
	 */
	private static int POINT_BIT = 5;
	
	/*
	 * ����Y����
	 */
	private final int pointY;
	/*
	 * ����Y����
	 */
	private final int rmlineY;
	/*
	 * ����ֵY����
	 */
	private final int expY;
	/*
	 * ��ͨX����
	 */
	private final int comX;


	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		// ��ʼ����ͨ��X����
		this.comX = this.w - IMG_NUMBER_W * POINT_BIT - PADDING;
		// ��ʼ��������ʾ��y����
		this.pointY = PADDING;
		//��ʼ��������ʾ��Y����
		this.rmlineY = this.pointY + Img.RMLINE.getHeight(null)  + PADDING;
		//��ʼ������ֵ��ʾ��Y����
		this.expY = this.rmlineY + Img.RMLINE.getHeight(null) + PADDING;
		 //��ʼ������ֵ�۵Ŀ��д������Layer��
		//this.expW = this.w - (PADDING << 1);
		
	}

	public void paint(Graphics g) {
		this.CreateWindow(g);
		// ���Ʊ���(����)
		g.drawImage(Img.POINT, this.x + PADDING, this.y + pointY, null);
		//��ʾ����
		this.drawNumberLeftPad(comX, pointY, this.dto.getNowPoint(), POINT_BIT, g);
		// ���Ʊ���(����)
		g.drawImage(Img.RMLINE, this.x + PADDING, this.y + rmlineY, null);
		//��ʾ����
		this.drawNumberLeftPad(comX, rmlineY, this.dto.getNowRemoveLine(), POINT_BIT, g);
		//����ֵ��(����ֵ)
		int rmLine = this.dto.getNowRemoveLine();
		this.drawRect(expY, "��һ��", null, (double)(rmLine % LEVEL_UP) / (double)LEVEL_UP, g);
		
 			
	}
		
	/*
	//ֵ����ɫ����
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
