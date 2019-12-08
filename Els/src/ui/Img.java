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
	 * 个人签名
	 */
	public static Image SIGN = new ImageIcon("Graphic/string/els.jpg").getImage();
    /*
     * 导入边框图片
     */
	public static Image WINDOW = new ImageIcon("Graphic/window/win.png").getImage(); 
	/*
	 * 数字图片 260*36
	 */
	public static Image NUMBER = new ImageIcon("Graphic/string/num.jpg").getImage(); 
	/*
	 * 矩阵值槽
	 */
	public static  Image RECT = new ImageIcon("Graphic/window/rect.jpg").getImage();
	/*
	 * 数据库窗口标题
	 */
	public static Image DB = new ImageIcon("Graphic/string/db.jpg").getImage(); 
	/*
	 * 本地记录窗口标题
	 */
	public static Image DISK = new ImageIcon("Graphic/string/本地记录.jpg").getImage(); 
	/*
	 * 方块图片
	 */
	public static Image ACT = new ImageIcon("Graphic/game/rect.jpg").getImage();
	/*
	 * 阴影部分
	 */
	public static Image SHADOW = new ImageIcon("Graphic/game/shadow.png").getImage();
	/*
	 * 等级标题图片
	 */
	public static Image LEVEL = new ImageIcon("Graphic/string/level.jpg").getImage(); 
	/*
	 *分数 窗口标题（分数）
	 */
	public static Image POINT = new ImageIcon("Graphic/string/point.jpg").getImage();
	/*
	 * 分数窗口标题（消行）
	 */
	public static Image RMLINE = new ImageIcon("Graphic/string/rmline.jpg").getImage();
	/*
	 * 开始按钮
	 */
	public static ImageIcon BTN_START = new ImageIcon("Graphic/string/start.jpg");
	/*
	 * 设置按钮
	 */
	public static ImageIcon BTN_CONFIG = new ImageIcon("Graphic/string/shezhi.png");
	/*
	 * 暂停按钮
	 */
	public static Image PAUSE = new ImageIcon("Graphic/string/pause.jpg").getImage();

	/*
	 * 下一个图片数组
	 */
	public static Image[] NEXT_ACT;
	
	public static List<Image> BG_LIST;

	
	static {
		//下一个方块图片
		//NEXT_ACT = new Image[7];
		NEXT_ACT = new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		for(int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i] = new ImageIcon("Graphic/game/" + i +".jpg").getImage();
		}
		//背景图片数组
		File dir = new File("Graphic/background");
		File[] files = dir.listFiles();
		BG_LIST = new ArrayList<Image>();
		for(File file : files) {
			//如果不是文件夹形式，则保存路径
			if(!file.isDirectory()) {			
				BG_LIST.add(new ImageIcon(file.getPath()).getImage());
				
//			String path = file.getPath();
//			System.out.println(path);
			}
		}
		
		
	}
	


}
