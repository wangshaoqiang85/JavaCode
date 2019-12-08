package config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * 游戏配置器
 */

public class GameConfig {
	
	
	private static FrameConfig FRAME_CONFIG = null;
	
	private static DataConfig DATA_CONFIG = null;
	
	private static SystemConfig SYSTEM_CONFIG = null;
	
	static {

		try {
			//创建XML读取器
			SAXReader reader = new SAXReader();//xml读取器
			//读取XML文档
			Document doc = reader.read("data/cfg.xml");
			//获得XML根节点
			Element game = doc.getRootElement();
			//创建界面配置对象
			FRAME_CONFIG = new FrameConfig(game.element("frame"));
			//创建系统对象
			SYSTEM_CONFIG = new SystemConfig(game.element("system"));
			//创建数据访问对象
			DATA_CONFIG = new DataConfig(game.element("data"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 构造器私有化
	 */
	private GameConfig() {}
	
	
	/*
	 * 获得窗口配置
	 */
	public static FrameConfig getFrameConfig() {
		
		return FRAME_CONFIG;
	}
	/*
	 * 获得系统配置
	 */
	
	public static SystemConfig getSystemConfig() {
		return SYSTEM_CONFIG;
	}
	/*
	 * 获得数据访问配置
	 */
	public static DataConfig getDataConfig() {
		return DATA_CONFIG;
	}
	


//	private int width;//窗口宽度；
//	private int height;//窗口高度；
//	private int windowSize;//边框尺寸
//	private int padding;//边框内边距；
//	private String title;//标题
//	private int windowUp;//窗口拔高

	/*
	 * 构造函数配置，
	 * 读取XML文档，获取全部设置参数
	 */
//	public GameConfig() throws Exception  {
//		
//		//配置窗口参数
//		 this.setUiConfig();
//		//配置系统参数
//		this.setSystemConfig();
//		//配置数据访问参数
//		this.setDataConfig();
//					
//	}
}
