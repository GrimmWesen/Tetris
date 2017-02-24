package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * �������ڽ���һ�����������ķ����������Ϸ�����
 * @author GAOH
 *
 */
public class DialogNext extends Dialog {
	private static Image[] IMG_NEXT;
	/**
	 * �����������洢ͼƬ����
	 * ʹ�þ�̬��ʦ������ʼ���������ע�����ַ���
	 * */
	static {
		IMG_NEXT=new Image[7];
		for(int i=0;i<IMG_NEXT.length;i++){
			IMG_NEXT[i]=new ImageIcon("Graphics/game/"+i+".png").getImage();
		}
	}
		
	public DialogNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		if(this.dto.getBlock().getGameInit()){
			drawOfCenter(g);
		}
		
	}
	
	/**
	 * �÷���ʵ�����л�ͼ
	 * */
	private void drawOfCenter(Graphics g){
		int img_w=IMG_NEXT[this.dto.getNext()].getWidth(null);
		int img_h=IMG_NEXT[this.dto.getNext()].getHeight(null);
		int now_x=(this.w-img_w)/2;
		int now_y=(this.h-img_h)/2;
		g.drawImage(IMG_NEXT[this.dto.getNext()], this.x+now_x,this.y+ now_y, null);
	}
}
