package ui;

import java.awt.Graphics;

public class LayerLevel extends Layer {
	

	//����ͼƬ���
	private static final int IMG_LV_W = Img.LEVEL.getWidth(null);


	/*
	 * ���캯��
	 */
	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paint(Graphics g) {
		this.CreateWindow(g);	
		//���ڱ���
		int centerX = this.w - IMG_LV_W >> 1; //���ñ������
		g.drawImage(Img.LEVEL, this.x + centerX, this.y + PADDING, null);
		//��ʾ�ȼ�
		this.drawNumberLeftPad(centerX, 64, this.dto.getNowLevel(), 2, g);
	}



}
