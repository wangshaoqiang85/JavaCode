package ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class TextCtrl extends JTextField {
	
	private int keyCode;
	
	private final String methodName;
	
	public TextCtrl(int x, int y, int w, int h, String methodName) {
		//�����ı���λ��
		this.setBounds(x, y, w, h);
		//��ʼ��������
		this.methodName = methodName;
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			/*
			 * �����ɿ�
			 */
			public void keyPressed(KeyEvent e) {

				setKeyCode(e.getKeyCode());
			}
			public void keyReleased(KeyEvent e) {}			
			
		});
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		this.setText(KeyEvent.getKeyText(this.keyCode));
	}

	public String getMethodName() {
		return methodName;
	}

	
}
