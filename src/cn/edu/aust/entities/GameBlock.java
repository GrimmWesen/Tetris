package cn.edu.aust.entities;

import java.awt.Point;
import cn.edu.aust.service.GameService;
import cn.edu.aust.view.GamePanel;

/**
 * С������
 * 
 * @author GAOH
 * 
 */

public class GameBlock {
	private Thread thread;
	private Point points[];
	private GameService service;
	private GamePanel panel;
	private boolean judgeStop=false;
	/**
	 * �ñ��������ж�view���з���ĵ�һ����ʾ��false����ʾ��true��ʾ
	 * */
	private boolean isGameInit=false;
	private int blockNum;
	public int getblockNum() {
		return blockNum;
	}
	public boolean isJudgeStop() {
		return judgeStop;
	}
	public void setJudgeStop(boolean judgeStop) {
		this.judgeStop = judgeStop;
	}
	/**
	 * points���ڴ洢�����λ������
	 * */
	public GameBlock(GameService service,GamePanel panel) {
		this.service=service;
		this.panel=panel;
		init(4);
	}

	public void init(int blockNum) {
		/**
		 * �˴�switch case�Ĵ���ʽ�����ã�TODO
		 * */
		this.blockNum=blockNum;
		switch (blockNum) {
		case 0:
			points = new Point[]
			{ new Point(5, 0), new Point(4, 0),new Point(6, 0), new Point(6, 1) };
			break;
		case 1:
			points=new Point[]
			{new Point(5,0),new Point(4,0),new Point(4,1),new Point(6,0)};
			break;
		case 2:
			points=new Point[]
				{new Point(5,1),new Point(4,1),new Point(5,0),new Point(6,0)};	
			break;
		case 3:
			points=new Point[]
				{new Point(5,0),new Point(4,0),new Point(5,1),new Point(6,1)};
			break;
	    case 4:
		points=new Point[]
				{new Point(4,0),new Point(3,0),new Point(5,0),new Point(6,0)};
		break;
	    case 5:
		points=new Point[]
				{new Point(5,0),new Point(4,0),new Point(4,1),new Point(5,1)};
		break;
	    case 6:
		points=new Point[]
				{new Point(5,0),new Point(4,0),new Point(5,1),new Point(6,0)};
		break;
		
		}
	}

	public Point[] getPoints() {
		return points;
	}

	public boolean move(int moveX, int moveY, boolean[][] gameMap) {
		for (int i = 0; i < points.length; i++) {
			int temp_x = points[i].x + moveX;
			int temp_y = points[i].y + moveY;
			if (this.isOverBorder(temp_x, temp_y, gameMap)) {
				return false;
			}
		}
		for (int i = 0; i < points.length; i++) {
			points[i].x += moveX;
			points[i].y += moveY;
		}
		return true;
	}
	

	public void rotate(boolean[][] gameMap) {
		// ���巽����ĳһ����ת
		/**
		 * ����ÿ����ת֮����������·����ı䣬�ʱ����ж��Ƿ������ת
		 * */
		
		//���������εķ��飬�����������
		if(this.blockNum==5){
			return;
		}
		for (int i = 1; i < points.length; i++) {
			/**
			 * x'=y0+x0-y, y'=y0-x0+x ��ת��ʽ
			 * */
			int temp_x = points[0].y + points[0].x - points[i].y;
			int temp_y = points[0].y - points[0].x + points[i].x;
			if (this.isOverBorder(temp_x, temp_y, gameMap)) {
				return;
			}
		}
		for (int i = 1; i < points.length; i++) {
			int temp_x = points[0].y + points[0].x - points[i].y;
			int temp_y = points[0].y - points[0].x + points[i].x;
			points[i].x = temp_x;
			points[i].y = temp_y;
		}
	}

	private boolean isOverBorder(int temp_x, int temp_y, boolean[][] gameMap) {
		/**
		 * ����boolean���Ͳ����жϷ�������������ʱ�ܷ��������
		 * */
		return temp_x < 0 || temp_x > 9 || temp_y < 0 || temp_y > 17
				|| gameMap[temp_x][temp_y];

	}
	public void statrMainThread() {
		thread=  new Thread(new GameDriver());
		thread.start();
	}

	public boolean getGameBoolean(){
		return this.service.isGameOver();
	}
		
	class GameDriver implements Runnable{

		@Override
		public void run() {
			while(judgeStop){
				service.keyDown();
				panel.repaint();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	
	public boolean  getGameInit(){
		return isGameInit;
	}
	public void setGameInit(boolean isGameInit) {
		this.isGameInit=isGameInit;
	}



}
