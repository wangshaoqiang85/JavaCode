package ui;

import java.awt.Graphics;

public class LayerNext extends Layer {

	
	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paint(Graphics g) {
		this.CreateWindow(g);
		//如果是开始状态才绘制下一个方块
		if(this.dto.isStart()) {
			//g.drawImage(NEXT_ACT[this.dto.getNext()], this.x + 32, this.y + 32, null);
			this.drawImageAtCenter(Img.NEXT_ACT[this.dto.getNext()], g);
		}
	}	
	
}
