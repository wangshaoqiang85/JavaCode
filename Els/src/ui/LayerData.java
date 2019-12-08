package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import config.GameConfig;
import dto.Player;

public abstract class LayerData extends Layer {

	/*
	 * ��������������ļ�
	 */
	private static final int MAX_ROW = GameConfig.getDataConfig().getMaxRow();
	/*
	 * ��ʼY����
	 */
	private static int STATR_Y = 0;
	/*
	 * ֵ���⾶
	 */
	private static final int RECT_H = IMG_RECT_H + 4;
	/*
	 * ���
	 */
	private static int SPA = 0;
	
	/*
	 * ���캯��
	 */
	public LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		//�����¼���Ƽ��
		SPA = (this.h - RECT_H * 5 - (PADDING << 1) - Img.DB.getHeight(null)) /MAX_ROW;
		//������ʼY����
		STATR_Y = PADDING + Img.DB.getHeight(null) + SPA;	
	}

	//���󷽷�
	@Override
	abstract public void paint(Graphics g);
	
	
	
	/*
	 * @param imgTitle ����ͼƬ
	 * @param players  ����Դ
	 * @param g  ����
	 */
	public void showData(Image imgTitle, List<Player> players, Graphics g) {
		//���Ʊ���
		g.drawImage(imgTitle, this.x+PADDING, this.y + PADDING, null);
		//������ݶ���
		//players = this.dto.getDbRecode();
		//������ڷ���
		int nowPoint = this.dto.getNowPoint();
		//ѭ�����ƶ���
	    for(int i = 0; i < MAX_ROW; i++) {
	    	//���һ����Ҽ�¼
	    	Player pla = players.get(i);
	    	//��ø÷���
	    	int recodePoint = pla.getPoint();
	    	//�������ڷ������¼������ֵ
	    	double percent = (double)nowPoint / recodePoint;
	    	//����Ѿ��Ƽ�¼����ֵ��Ϊ100%
	    	percent  = percent > 1 ? 1.0 : percent;
	    	//���Ƶ�����¼
	    	String strPoint = recodePoint == 0 ? null : Integer.toString(recodePoint);
	    	this.drawRect(STATR_Y + i * (RECT_H + SPA), 
	    			pla.getName(), strPoint, percent, g);
	    }
	}

}
