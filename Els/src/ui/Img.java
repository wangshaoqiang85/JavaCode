package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;

public class Img {
	
	private Img() {}
	
	/*
	 * ����ǩ��
	 */
	public static Image SIGN = new ImageIcon("Graphic/string/els.jpg").getImage();
    /*
     * ����߿�ͼƬ
     */
	public static Image WINDOW = new ImageIcon("Graphic/window/win.png").getImage(); 
	/*
	 * ����ͼƬ 260*36
	 */
	public static Image NUMBER = new ImageIcon("Graphic/string/num.jpg").getImage(); 
	/*
	 * ����ֵ��
	 */
	public static  Image RECT = new ImageIcon("Graphic/window/rect.jpg").getImage();
	/*
	 * ���ݿⴰ�ڱ���
	 */
	public static Image DB = new ImageIcon("Graphic/string/db.jpg").getImage(); 
	/*
	 * ���ؼ�¼���ڱ���
	 */
	public static Image DISK = new ImageIcon("Graphic/string/���ؼ�¼.jpg").getImage(); 
	/*
	 * ����ͼƬ
	 */
	public static Image ACT = new ImageIcon("Graphic/game/rect.jpg").getImage();
	/*
	 * ��Ӱ����
	 */
	public static Image SHADOW = new ImageIcon("Graphic/game/shadow.png").getImage();
	/*
	 * �ȼ�����ͼƬ
	 */
	public static Image LEVEL = new ImageIcon("Graphic/string/level.jpg").getImage(); 
	/*
	 *���� ���ڱ��⣨������
	 */
	public static Image POINT = new ImageIcon("Graphic/string/point.jpg").getImage();
	/*
	 * �������ڱ��⣨���У�
	 */
	public static Image RMLINE = new ImageIcon("Graphic/string/rmline.jpg").getImage();
	/*
	 * ��ʼ��ť
	 */
	public static ImageIcon BTN_START = new ImageIcon("Graphic/string/start.jpg");
	/*
	 * ���ð�ť
	 */
	public static ImageIcon BTN_CONFIG = new ImageIcon("Graphic/string/shezhi.png");
	/*
	 * ��ͣ��ť
	 */
	public static Image PAUSE = new ImageIcon("Graphic/string/pause.jpg").getImage();

	/*
	 * ��һ��ͼƬ����
	 */
	public static Image[] NEXT_ACT;
	
	public static List<Image> BG_LIST;

	
	static {
		//��һ������ͼƬ
		//NEXT_ACT = new Image[7];
		NEXT_ACT = new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		for(int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i] = new ImageIcon("Graphic/game/" + i +".jpg").getImage();
		}
		//����ͼƬ����
		File dir = new File("Graphic/background");
		File[] files = dir.listFiles();
		BG_LIST = new ArrayList<Image>();
		for(File file : files) {
			//��������ļ�����ʽ���򱣴�·��
			if(!file.isDirectory()) {			
				BG_LIST.add(new ImageIcon(file.getPath()).getImage());
				
//			String path = file.getPath();
//			System.out.println(path);
			}
		}
		
		
	}
	


}
