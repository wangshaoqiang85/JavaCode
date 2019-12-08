package ui.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import config.FrameConfig;
import config.GameConfig;
import util.FrameUtil;

public class JFrameGame extends JFrame {
	
	public JFrameGame(JPanelGame panelGame) {
		
		//获得游戏配置
		FrameConfig fCfg = GameConfig.getFrameConfig();
		//设置标题
		//this.setTitle("俄罗斯方块");
		this.setTitle(fCfg.getTitle());
		
		//设计默认关闭属性（程序结束）
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置窗口大小
		//this.setSize(1172, 1168);
		this.setSize(fCfg.getWidth(), fCfg.getHeight());
		
		//不容许用户改变窗口大小
		this.setResizable(false);
		
		//剧中
		FrameUtil.setFrameCenter(this);
		//写到工具类FrameUtil中去
//		Toolkit toolkit = Toolkit.getDefaultToolkit();
//		Dimension screen = toolkit.getScreenSize();
//		int x =  screen.width - this.getWidth() >> 1;
//		int y = (screen.height - this.getHeight() >> 1) - fCfg.getWindowUp();
//		this.setLocation(x, y);
		
		//设置默认Panel
		this.setContentPane(panelGame);
		//this.setContentPane(new JPanelGame());
		//默认窗口显示
		this.setVisible(true);
	}
	


}
