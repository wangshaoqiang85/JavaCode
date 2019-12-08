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
	
	private JButton btnOk = new JButton("ȷ��");
	
	private JButton btnCancel = new JButton("ȡ��");

	private JButton btnUser = new JButton("Ӧ��");
	
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
		//�����Ϸ����������
		this.gameControl = gameControl;
		//���ò��ֹ�����Ϊ���߽粼�֡�
		this.setLayout(new BorderLayout());
		//���ñ���
		this.setTitle("����");
		//��ʼ����������
		this.initkeyText();
		//��������
		this.add(creatMaiPanel(), BorderLayout.CENTER);
		//��Ӱ�ť���
		this.add(this.creatButtonPanel(),BorderLayout.SOUTH);
		//���ò��ܸı��С
		this.setResizable(false);
		//���ô��ڴ�С
		this.setSize(650, 360);
		//����
		FrameUtil.setFrameCenter(this);
		
//		//TODO  ��������
//		this.setDefaultCloseOperation(3);
//		this.setVisible(true);
	}

	/*
	 * ��ʼ�����������
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
	 * ������ť���
	 */
	private JPanel creatButtonPanel() {
		//������ť��� --��ʽ����
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//��ȷ����ť�����¼�����
		this.btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(writeConfig()) {
					setVisible(false);
					gameControl.setOver();;
				}
			}
		});
		this.errorMsg.setForeground(Color.RED);//������Ϣ��ʾ
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
	 * ��������� ѡ����
	 */
	private JTabbedPane creatMaiPanel() {
		JTabbedPane jtp = new JTabbedPane();
		//jtp.addTab("��������", new JLabel("����"));
		jtp.addTab("��������", this.createControlPanel());
		jtp.addTab("Ƥ������", this.creatSkinPanel());
		return jtp;
	}
	/*
	 * ���Ƥ�����
	 */
	private JPanel creatSkinPanel() {
		JPanel panel = new JPanel(new  BorderLayout());
		//�������
		this.skinData.addElement("Ĭ��Ƥ��");
		this.skinData.addElement("Ƥ��һ");
		this.skinData.addElement("Ƥ����");
		this.skinData.addElement("Ƥ����");
		this.skinData.addElement("Ƥ����");
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
	 * ��ҿ����������
	 */
	private JPanel createControlPanel() {
		//�ڲ�������
		JPanel jp = new JPanel() {		
			public void paintComponent(Graphics g) {
				g.drawImage(IMG_PSP, 0, 0, null);
			}		
		};
		//���ò��ֹ�����
		jp.setLayout(null);
		for(int i =0; i < keyText.length; i++) {
			jp.add(keyText[i]);
		}
		return jp;
	}

	/*
	 * д����Ϸ����
	 */
	private  boolean writeConfig() {
		HashMap<Integer, String> keySet = new HashMap<Integer, String>();
		for(int i =0; i < this.keyText.length; i++) {
			int keyCode = this.keyText[i].getKeyCode();
			if(keyCode == 0) {
				this.errorMsg.setText("���󰴼�");
				return false;
			}
			keySet.put(this.keyText[i].getKeyCode(), this.keyText[i].getMethodName());
		}
		if(keySet.size() != 8) {
			this.errorMsg.setText("�ظ�����");
			return false;
		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
			//System.out.println("д��ɹ�");
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
