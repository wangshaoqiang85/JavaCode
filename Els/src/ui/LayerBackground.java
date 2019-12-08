package ui;

import java.awt.Graphics;

public class LayerBackground extends Layer {
	
//	private static Image IMG_BG = new ImageIcon("Graphic/background/01.jpg").getImage(); 


	public LayerBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		//g.drawImage(Img.BG_LIST.get(5), 0, 0, 1162, 654, null);
		int bgIdx = this.dto.getNowLevel() % Img.BG_LIST.size();
		g.drawImage(Img.BG_LIST.get(bgIdx), 0, 0, 1162, 654, null);

	}

}
