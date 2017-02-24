package cn.edu.aust.view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cn.edu.aust.dto.GameDto;

/**
 * ������������װ���߿��һ����
 * @author GAOH
 *
 */
public abstract class Dialog {
	protected GameDto dto;
	
	private static final int PADDING=32;
	/**
	 * ���߿��ȶ���Ϊ����
	 * */
	private static final int  SIZE =3;
	/**
	 *  ��ͼƬ��������Ϊ����
	 *  Image img=Toolkit.getDefaultToolkit("ͼƬ·��").getImage(str);
	 * ע�⣺����ø÷�������ȡͼƬʱ����Ҫ��ͣ���ػ��������Ļ����ʾ��ͼƬ
	 */
	private static final Image IMG=new ImageIcon("Graphics/Window/Window_test.png").getImage();
	/**
	 * ��ȡͼƬ���
	 * 	 * */
	private static final int IMG_W=IMG.getWidth(null);
	/**
	 * ��ȡͼƬ�߶�
	 * */
	private static final int IMG_H=IMG.getHeight(null);
	/**
	 * ��ȡRECTͼƬ
	 * */
	private static final Image IMG_RECT=
			new ImageIcon("Graphics/window/rect.png").getImage();
	
	/**
	 * RECT�Ŀ�� 
	 * */
	private final  int RECT_W=IMG_RECT.getWidth(null);
	/**
	 * RECT�ĸ߶�
	 * */
	private final  int RECT_H=IMG_RECT.getHeight(null);
	/**
	 * �����߿�ĺ�����
	 * */
	protected int x;
	/**
	 * �����߿��������
	 * */
	protected int y;
	/**
	 * ���������߿�Ŀ��
	 * */
	protected int w;
	/**
	 * ���������߿�ĸ߶�
	 * */
	protected int h;
	
	
	public Dialog(int x,int y,int w,int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	
	public void createWindow(Graphics g){
		        // ����
				g.drawImage(IMG, x, y, x + SIZE, y + SIZE, 0, 0, SIZE, SIZE, null);
				//����
				g.drawImage(IMG, x+SIZE, y, (w+x)-SIZE, y+SIZE,SIZE, 0, IMG_W-SIZE,SIZE, null);
				//����
				g.drawImage(IMG, w+x-SIZE, y, w+x, y+SIZE, IMG_W-SIZE, 0, IMG_W, SIZE, null);
				//����
				g.drawImage(IMG, x, y+SIZE, x+SIZE, h+y-SIZE, 0, SIZE, SIZE, IMG_H-SIZE, null);
				//����
				g.drawImage(IMG, x+SIZE, y+SIZE, w+x-SIZE, y+h-SIZE, SIZE, SIZE, IMG_W-SIZE, IMG_H-SIZE, null);
				//����
				g.drawImage(IMG,  w+x-SIZE, y+SIZE, w+x, h+y-SIZE,IMG_W-SIZE, SIZE, IMG_W, IMG_H-SIZE, null);
				//����
				g.drawImage(IMG,  x, h+y-SIZE, x+SIZE, h+y, 0, IMG_H-SIZE, SIZE, IMG_H, null);
				//����
				g.drawImage(IMG, x+SIZE, y+h-SIZE, x+w-SIZE, y+h, SIZE, IMG_H-SIZE, IMG_W-SIZE, IMG_H, null);
				//����
				g.drawImage(IMG, x+w-SIZE, y+h-SIZE, x+w, y+h, IMG_W-SIZE, IMG_H-SIZE, IMG_W, IMG_H, null);
				
	}
	
	/**
	 * �ڸ����г�����÷�����
	 * ���ڻ���ֵ��
	 * @param x ��ʾֵ�۵����ϽǺ�����
	 * @param y ��ʾֵ�۵����Ͻ�������
	 * @param w ��ʾֵ�۵Ŀ��
	 * @param h ��ʾֵ�۵ĸ߶�
	 * @param title ��ʾֵ�۵���������
	 * @param g ����
	 * @param value ��ʾֵ��ÿһ�������Ļ���ֵ
	 * @param maxvalue ��ʾֵ������������
	 * @param strnum ��ʾֵ���������Ƶ�����
	 */
	protected void drawRect(int x,int y,int w,int h,String title,Graphics g,int value,int maxvalue,int num){
		
		//���Ʊ���
		g.setColor(Color.white);
		g.fillRect(x, y, w, h);
		g.setColor(Color.black);
		g.fillRect(x+1, y+1, w-2, h-2);
		//����ֵ��
		double p=(double)(value%maxvalue)/(double)maxvalue;
		int width=(int) (p*(w-2));//TODO
		//widthΪÿ�����ӵĲ������
		int subIdx=(int) (p*RECT_W);
		//subIdx����RECT����ͼƬ�Ͻ�ȡ��λ�������ʾ
		g.drawImage(IMG_RECT, x+1, y+1, this.x+PADDING+ width-16, y+1+h-2, //TODO
				0, 0, subIdx, RECT_H,null);//subIdx, 0, subIdx+1, RECT_H,null);
		g.setColor(Color.WHITE);
		//g.setFont(DEF_FONT);
		//TODO setFont�ᵼ����Ļˢ�±���
		g.drawString(title, x+1+16, y+1+16);
		if(num==0){
			return;
		}
		String strnum=String.valueOf(num);
		//���Ʒ���    �ַ����Ļ��������������ַ����ز������
		g.drawString(strnum, x+200, y+1+16);
	}
	public void setDto(GameDto dto){
		this.dto=dto;
	}
	public abstract void paint(Graphics g);
}
