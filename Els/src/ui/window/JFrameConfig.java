package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import control.GameControl;
import util.FrameUtil;

public class JFrameConfig extends JFrame {
	
	private JButton btnOk = new JButton("确定");
	
	private JButton btnCancel = new JButton("取消");

	private JButton btnUser = new JButton("应用");
	
	private TextCtrl[] keyText = new TextCtrl[8];
	
	private JLabel errorMsg = new JLabel();
	
	private JList skinList = null;
	
	private JPanel skinView = null;
	
	private DefaultListModel skinData = new DefaultListModel();
	
	private GameControl gameControl;
	
	private final static Image IMG_PSP = new ImageIcon("data/psp.jpg").getImage(); 
	
	private Image temp_skinImg = new ImageIcon("Graphic/view.jpg").getImage(); 
	
	private final static String[] METHOD_NAMES = {
		"keyRight", "keyUp", "keyLeft", "keyDown",
		"keyFunLeft", "keyFunUp", "keyFunRight", "keyFunDown"			
	};
	
	private final static String PATH = "data/control.dat";
	
	public JFrameConfig(GameControl gameControl) {
		//获得游戏控制器对象
		this.gameControl = gameControl;
		//设置布局管理器为“边界布局”
		this.setLayout(new BorderLayout());
		//设置标题
		this.setTitle("设置");
		//初始化按键输入
		this.initkeyText();
		//添加主面板
		this.add(creatMaiPanel(), BorderLayout.CENTER);
		//添加按钮面板
		this.add(this.creatButtonPanel(),BorderLayout.SOUTH);
		//设置不能改变大小
		this.setResizable(false);
		//设置窗口大小
		this.setSize(650, 360);
		//居中
		FrameUtil.setFrameCenter(this);
		
//		//TODO  测试用例
//		this.setDefaultCloseOperation(3);
//		this.setVisible(true);
	}

	/*
	 * 初始化按键输入框
	 */
	private void initkeyText() {

		int x = 20;
		int y = 52;
		int w = 64;
		int h = 20;
		for(int i = 0; i < 4; i++) {
			keyText[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 28;
		}
		x = 560;
		y = 52;
		for(int i = 4; i < 8; i++) {
			keyText[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 28;
		}	
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>)ois.readObject();
			ois.close();
			
			Set<Entry<Integer, String>> entryset = cfgSet.entrySet();
			for(Entry<Integer, String> e : entryset) {
				for(TextCtrl tc : keyText) {
					if(tc.getMethodName().equals(e.getValue())) {
						tc.setKeyCode(e.getKey());
					}
				}
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/*
	 * 创建按钮面板
	 */
	private JPanel creatButtonPanel() {
		//创建按钮面板 --流式布局
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//给确定按钮增加事件监听
		this.btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(writeConfig()) {
					setVisible(false);
					gameControl.setOver();;
				}
			}
		});
		this.errorMsg.setForeground(Color.RED);//错误信息提示
		jp.add(this.errorMsg);
		jp.add(this.btnOk);
		
		this.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gameControl.setOver();;

			}
		});
		jp.add(this.btnCancel);
		
		this.btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeConfig();			
			}
		});
		jp.add(this.btnUser);
		
		return jp;
	}

	/*
	 * 创建主面板 选项卡面板
	 */
	private JTabbedPane creatMaiPanel() {
		JTabbedPane jtp = new JTabbedPane();
		//jtp.addTab("控制设置", new JLabel("控制"));
		jtp.addTab("控制设置", this.createControlPanel());
		jtp.addTab("皮肤设置", this.creatSkinPanel());
		return jtp;
	}
	/*
	 * 玩家皮肤面板
	 */
	private JPanel creatSkinPanel() {
		JPanel panel = new JPanel(new  BorderLayout());
		//添加内容
		this.skinData.addElement("默认皮肤");
		this.skinData.addElement("皮肤一");
		this.skinData.addElement("皮肤二");
		this.skinData.addElement("皮肤三");
		this.skinData.addElement("皮肤四");
		this.skinList = new JList(this.skinData);
		
		this.skinView = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(temp_skinImg, 0, 0, null);
			}
		};
		
		panel.add(new JScrollPane(this.skinList), BorderLayout.WEST);
		panel.add(this.skinView, BorderLayout.CENTER);	
		return panel;
	}
	/*
	 * 玩家控制设置面板
	 */
	private JPanel createControlPanel() {
		//内部构造类
		JPanel jp = new JPanel() {		
			public void paintComponent(Graphics g) {
				g.drawImage(IMG_PSP, 0, 0, null);
			}		
		};
		//设置布局管理器
		jp.setLayout(null);
		for(int i =0; i < keyText.length; i++) {
			jp.add(keyText[i]);
		}
		return jp;
	}

	/*
	 * 写入游戏配置
	 */
	private  boolean writeConfig() {
		HashMap<Integer, String> keySet = new HashMap<Integer, String>();
		for(int i =0; i < this.keyText.length; i++) {
			int keyCode = this.keyText[i].getKeyCode();
			if(keyCode == 0) {
				this.errorMsg.setText("错误按键");
				return false;
			}
			keySet.put(this.keyText[i].getKeyCode(), this.keyText[i].getMethodName());
		}
		if(keySet.size() != 8) {
			this.errorMsg.setText("重复按键");
			return false;
		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
			//System.out.println("写入成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.errorMsg.setText(e.getMessage());
			return false;
		} 
		this.errorMsg.setText(null);
		return true;		
	}
	
//	public static void main(String[] args) {
//		new FrameConfig();
//	}
//	
	
}
