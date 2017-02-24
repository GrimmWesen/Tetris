package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class DialogGame extends Dialog {
	/**
	 * ��ȡ��Ϸ����
	 * */
	private static final Image IMG_BLOCK=
		new ImageIcon("Graphics/game/rect.png").getImage();
	/**
	 * ��ȡ��Ϸ����ͼƬ
	 * */
	private static final Image IMG_OVER=
			new ImageIcon("Graphics/String/Gameover.jpg").getImage();
	private static final int BLOCK=32;
	private static final int SIZE=3;
	public DialogGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		Point [] points=this.dto.getBlock().getPoints();
		/**
		 * ��ȡ�����λ����������
		 * */
		int blockNum=this.dto.getBlock().getblockNum();
		/**
		 * ��ȡ����ı��
		 * */
		if(this.dto.getBlock().getGameInit()){
			 for(int i=0;i<points.length;i++){
					g.drawImage(IMG_BLOCK,
							this.x+points[i].x*BLOCK+SIZE,
							this.y+points[i].y*BLOCK+SIZE,
							this.x+points[i].x*BLOCK+BLOCK+SIZE,
							this.y+points[i].y*BLOCK+BLOCK+SIZE, 
							BLOCK*(blockNum+1), 0, 
							BLOCK*(blockNum+1)+BLOCK, BLOCK, null);
				}
		}
		
		/**
		 * ���Ʒ������ʱ�ĵ�ͼ
		 * */
		boolean [][] map=this.dto.getGameMap();
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[x].length;y++){
				if(map[x][y]){
				g.drawImage(IMG_BLOCK,
						this.x+x*BLOCK+SIZE,
						this.y+y*BLOCK+SIZE,
						this.x+x*BLOCK+BLOCK+SIZE,
						this.y+y*BLOCK+BLOCK+SIZE, 
						BLOCK*(this.dto.getNowLevel()-1), 0, 
						BLOCK*(this.dto.getNowLevel()), BLOCK, null);
				}
			}
		}
		if(!this.dto.getBlock().getGameBoolean()){
			g.drawImage(IMG_OVER, this.x+32, this.y+128, null);
		}
	}
	
	
		
}
