package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class DialogPoint extends Dialog {
	private static final Image IMG_POINT=
			new ImageIcon("Graphics/String/point.png").getImage();
	private static final Image IMG_RMLINE=
			new ImageIcon("Graphics/string/rmline.png").getImage();
	private static final Image IMG_NUM=
			new ImageIcon("Graphics/String/num_.png").getImage();
	
	/**
	 * ����ͼƬ�ĸ߶�
	 * */
	private  final int IMG_H=36;
	/**
	 * ����ͼƬ�Ŀ��
	 * */
	private  final int IMG_W=26;
	/**
	 * С�߿����
	 * */
	private static final int PADDING=16;
	/**
	 * ��߿����
	 * */
	private  final int DIS=156;
	/**
	 * ����ֵ�۵Ŀ��
	 * */
	private  final int exp_x=334-2*PADDING;
	/**
	 * ����ֵ�۵ĸ߶�
	 * */
	private final int exp_y=28;
	/**
	 * �������ͼ��Ĺ�ͬ�ĺ�����
	 * */
	private final int com_x=this.x+PADDING;
	
	
	private final int LEVEL_UP=5;
	
	public DialogPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);	
		g.drawImage(IMG_POINT, com_x, this.y+PADDING, null);
		g.drawImage(IMG_RMLINE, com_x, this.y+(PADDING+48), null);
		drawNumber(g);
		drawLineNum(g);
		
		//����ֵ��
		String title="��һ��";
		int temp=this.dto.getNowDeleteLine();
		//�˴���Ӳ�������ڴ���ֵ��ǰ�˷�������
		drawRect(com_x,this.y+(PADDING<<2)+48,exp_x,exp_y,
				title,g,temp,LEVEL_UP,0);
		
	}

	/**
	 * �÷������ڻ��ƶ�λ������
	 * ���Ʒ�������
	 * */
	private void drawNumber(Graphics g){
		String str=String.valueOf(this.dto.getNowPoint());
		for(int i=0;i<str.length();i++){
			int temp=Integer.parseInt(str.charAt(i)+"");
			g.drawImage(IMG_NUM, 
					this.x+PADDING+DIS+IMG_W*i, 
					this.y+PADDING, 
					this.x+PADDING+DIS+IMG_W+IMG_W*i, 
					this.y+PADDING+IMG_H, 
					temp*IMG_W, 0, temp*IMG_W+IMG_W, IMG_H, null);
		}
	}
	/**
	 * ������������
	 * */
	private void drawLineNum(Graphics g){
		String str=String.valueOf(this.dto.getNowDeleteLine());
		for(int i=0;i<str.length();i++){
			int temp=Integer.parseInt(str.charAt(i)+"");
			g.drawImage(IMG_NUM, 
					this.x+PADDING+DIS+IMG_W*i, 
					this.y+PADDING+48, 
					this.x+PADDING+DIS+IMG_W+IMG_W*i, 
					this.y+PADDING+48+IMG_H, 
					temp*IMG_W, 0, temp*IMG_W+IMG_W, IMG_H, null);
		}
	}
}