package ui.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;
import ui.Img;
import ui.Layer;


public class JPanelGame extends JPanel {

	private static final int BTN_SIZE_W = GameConfig.getFrameConfig().getButtonConfig().getButtonW();
	
	private static final int BTN_SIZE_H = GameConfig.getFrameConfig().getButtonConfig().getButtonH();
	
	//多次用同一个对象，可以使用数组；
	private List<Layer> layers = null;
	
	private JButton btnStart;
	
	private JButton btnConfig;
	
	private GameControl gameControl = null;
	
	public JPanelGame(GameControl gameControl, GameDto dto) {
		//连接游戏控制器
		this.gameControl = gameControl;
		//设置布局管理器为自由布局
		this.setLayout(null);
		//初始化组件
		this.initComponent();
        //初始化层
		this.initLayer(dto);
		//安装键盘监听器
		this.addKeyListener(new PlayerControl(gameControl));
	}  

	/*
	 * 初始化组件
	 */
	private void initComponent() {
		//初始化开始按钮
		this.btnStart = new JButton(Img.BTN_START);
		//设置开始按钮位置
		btnStart.setBounds(
				GameConfig.getFrameConfig().getButtonConfig().getStartX(),
				GameConfig.getFrameConfig().getButtonConfig().getStartY(),
				BTN_SIZE_W, BTN_SIZE_H);
		//给开始按钮添加事件监听
		this.btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
			}	
		});	
		//添加按钮到面板
		this.add(btnStart);
		//初始化设置按钮
		this.btnConfig = new JButton(Img.BTN_CONFIG);
		//设置设置按钮位置
		this.btnConfig.setBounds(
				GameConfig.getFrameConfig().getButtonConfig().getUserConfigX(),
				GameConfig.getFrameConfig().getButtonConfig().getUserConfigY(),
				BTN_SIZE_W, BTN_SIZE_H);
		
		this.btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameControl.showUserConfig();
				
			}
		});
		//添加设置按钮
		this.add(btnConfig);
				
	}
	/*
	 * 	初始化层
	 */
	private void initLayer(GameDto dto) {
		try {
			
		//获得游戏配置
		FrameConfig fCfg = GameConfig.getFrameConfig();
		//获得层配置
		List<LayerConfig> layersCfg = fCfg.getLayersConfig();
		//创建游戏层数组
		layers = new ArrayList<Layer> (layersCfg.size());
		//循环创建所有层对象
		for(LayerConfig layerCfg : layersCfg) {	
			//获得类对象
			Class<?> cls = Class.forName(layerCfg.getClassName());
			//获得构造函数
			Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);
			 //调用构造函数
			Layer layer = (Layer)ctr.newInstance(
					layerCfg.getX(),layerCfg.getY(),layerCfg.getW(),layerCfg.getH()
					);
			//设置游戏数据对象
			layer.setDto(dto);
			//把创建的Layer对象放入集合
			layers.add(layer);
				
			}
		}
		catch (Exception e) {
				e.printStackTrace();
		}				
	
		/*
		 * lays = new Layer[] {
		 * 
		 * TODO 硬编码是非常不好的开发习惯，要尽量将数字或者字符串定义成常量，或者写入配置文件；
		 * 
		 * new LayerBackground(0, 0, 0, 0), new LayerDataBase(40, 32, 334, 279), 
		 * new LayerDisk(40, 343, 334, 279), new LayerGame(414, 32, 334, 590), 
		 * new LayerButton(788, 32, 334, 124), new LayerNext(788, 188, 176, 148),
		 * new LayerLevel(964, 188, 158, 148), new LayerPoint(788, 368, 334, 200)
		 * 
		 * };
		 */
	}
	

	public void paintComponent(Graphics g) {
		
		//调用基类方法
		super.paintComponent(g);
		//绘制游戏界面
		for(int i = 0;i < layers.size(); layers.get(i++).paint(g));
		/* 
		// 循环刷新游戏界面
		for (int i = 0; i < layers.size(); i++) {
			// 刷新多层窗口
			layers.get(i).paint(g);
		}*/
		
		//返回焦点
		this.requestFocus();
		
	}
	/*
	 * 控制按钮是否可以点击
	 */
	public void buttonSwith(boolean onOff) {
		this.btnConfig.setEnabled(onOff);
		this.btnStart.setEnabled(onOff);
	}
	
	public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}
}
