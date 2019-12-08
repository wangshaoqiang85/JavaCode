package ui.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import config.FrameConfig;
import config.GameConfig;
import util.FrameUtil;

public class JFrameGame extends JFrame {
	
	public JFrameGame(JPanelGame panelGame) {
		
		//�����Ϸ����
		FrameConfig fCfg = GameConfig.getFrameConfig();
		//���ñ���
		//this.setTitle("����˹����");
		this.setTitle(fCfg.getTitle());
		
		//���Ĭ�Ϲر����ԣ����������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���ô��ڴ�С
		//this.setSize(1172, 1168);
		this.setSize(fCfg.getWidth(), fCfg.getHeight());
		
		//�������û��ı䴰�ڴ�С
		this.setResizable(false);
		
		//����
		FrameUtil.setFrameCenter(this);
		//д��������FrameUtil��ȥ
//		Toolkit toolkit = Toolkit.getDefaultToolkit();
//		Dimension screen = toolkit.getScreenSize();
//		int x =  screen.width - this.getWidth() >> 1;
//		int y = (screen.height - this.getHeight() >> 1) - fCfg.getWindowUp();
//		this.setLocation(x, y);
		
		//����Ĭ��Panel
		this.setContentPane(panelGame);
		//this.setContentPane(new JPanelGame());
		//Ĭ�ϴ�����ʾ
		this.setVisible(true);
	}
	


}
