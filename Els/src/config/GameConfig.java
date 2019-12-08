package config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * ��Ϸ������
 */

public class GameConfig {
	
	
	private static FrameConfig FRAME_CONFIG = null;
	
	private static DataConfig DATA_CONFIG = null;
	
	private static SystemConfig SYSTEM_CONFIG = null;
	
	static {

		try {
			//����XML��ȡ��
			SAXReader reader = new SAXReader();//xml��ȡ��
			//��ȡXML�ĵ�
			Document doc = reader.read("data/cfg.xml");
			//���XML���ڵ�
			Element game = doc.getRootElement();
			//�����������ö���
			FRAME_CONFIG = new FrameConfig(game.element("frame"));
			//����ϵͳ����
			SYSTEM_CONFIG = new SystemConfig(game.element("system"));
			//�������ݷ��ʶ���
			DATA_CONFIG = new DataConfig(game.element("data"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ������˽�л�
	 */
	private GameConfig() {}
	
	
	/*
	 * ��ô�������
	 */
	public static FrameConfig getFrameConfig() {
		
		return FRAME_CONFIG;
	}
	/*
	 * ���ϵͳ����
	 */
	
	public static SystemConfig getSystemConfig() {
		return SYSTEM_CONFIG;
	}
	/*
	 * ������ݷ�������
	 */
	public static DataConfig getDataConfig() {
		return DATA_CONFIG;
	}
	


//	private int width;//���ڿ�ȣ�
//	private int height;//���ڸ߶ȣ�
//	private int windowSize;//�߿�ߴ�
//	private int padding;//�߿��ڱ߾ࣻ
//	private String title;//����
//	private int windowUp;//���ڰθ�

	/*
	 * ���캯�����ã�
	 * ��ȡXML�ĵ�����ȡȫ�����ò���
	 */
//	public GameConfig() throws Exception  {
//		
//		//���ô��ڲ���
//		 this.setUiConfig();
//		//����ϵͳ����
//		this.setSystemConfig();
//		//�������ݷ��ʲ���
//		this.setDataConfig();
//					
//	}
}
