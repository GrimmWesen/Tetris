package cn.edu.aust.service;

import java.awt.Point;
import java.util.Random;

import cn.edu.aust.domain.Data;
import cn.edu.aust.dto.GameDto;

/**
 * ������������������ݴ�������
 * 
 * @author GAOH
 * 
 */
public class GameService {
	/**
	 *  ����ýӿڵ����ã����ڽ������ݿ���������ݼ��ؽ���
	 * */
	private Data dataA;
	/**
	 *  ����ýӿڵ����ã����ڽ���Ӳ�̶��������ݼ��ؽ���
	 * */
	private Data dataB;

	private boolean isGameOver=true;
	
	
	public boolean isGameOver() {
		return isGameOver;
	}

	private GameDto dto;
	/**
	 * ��������ÿ����5���õȼ���1
	 * ���ڼ���
	 * */
	private int i=1;
	/**
	 * ����ò����ͱ��������ж��Ƿ����У�
	 * ����ֱ�Ӷ��巽��Ϊ��������ʱ����ֶ���ͬʱ����ʱ������ȥ������
	 * ������return��ֱ����ֹ���ѭ��
	 * */
	private boolean isDelete=true;
	
	public GameService(GameDto dto, Data dataA, Data dataB)  {
		super();
		this.dto = dto;
		this.dataA=dataA;
		this.dataB=dataB;
		this.dto.setDiskRecord(dataA.loadData());
		this.dto.setDbRecord(dataB.loadData());
		
	}

	public void keyUp() {
		this.dto.getBlock().rotate(this.dto.getGameMap());
	}

	public void keyDown() {
		if (this.dto.getBlock().move(0, 1, this.dto.getGameMap())) {
			return;
		}
		/**
		 * ���з���ĵط�������ͼ��������
		 * */
		 boolean [][] map = this.dto.getGameMap();
		Point[] block = this.dto.getBlock().getPoints();
		for (int i = 0; i < block.length; i++) {
			map[block[i].x][block[i].y] = true;
		}
		// ����һ���µ�ͼ��
		/**
		 * ����һ��״̬�ķ��鴫�����
		 * ����������һ���µķ���
		 * �ȳ�ʼ���������������һ������
		 * */
		//if() �ж���Ϸ�Ƿ����
		for(int x=0;x<GameDto.MAP_W;x++){
			if(map[x][0]){
				chageGameOver();
				return ;
			}
		}
		this.dto.getBlock().init(this.dto.getNext());
		int temp=new Random().nextInt(100000)%7;
		this.dto.setNext(temp);
		
		
		/**
		 * ���й���
		 * */
		deleteFullLine(map);
		if(!isDelete){
			int nowDeteleNum=this.dto.getNowDeleteLine()+1;
			this.dto.setNowDeleteLine(nowDeteleNum);
			int nowPoint=this.dto.getNowPoint()+10;
			this.dto.setNowPoint(nowPoint);
			isDelete=true;
		}
		/**
		 * ��ʾ�ȼ��ͷ���
		 * */
		if(this.dto.getNowDeleteLine()>=5*i){
			int nowLevel=this.dto.getNowLevel()+1;
			this.dto.setNowLevel(nowLevel);
			++i;
		}
	}

	private void chageGameOver() {
		this.isGameOver=false;
	}

	private void deleteFullLine(boolean [][] map) {
		/**
		 * ע�����в����߼��Ƚϸ��ӣ�ע��ע��
		 * */
		for(int y=0;y<GameDto.MAP_H;y++){
			boolean temp=true;
			for(int x=0;x<GameDto.MAP_W;x++){
				if(!map[x][y]){
					temp=false;
				}
			}
			//��if�жϱ������ڴ�ѭ������֮��֮ǰ�����ڴ�ѭ������´���һֱû�ҳ���
			if(temp){
				//�ж��ܷ����У�����ܾ���ȥ
				deteleLine(y,map);
				isDelete=false;
				//return true; //error ���ڴ˴���returnʱ���ܻᵼ�µ����ֶ���ʱ�޷��������У���Ϊreturn��ʾֱ�ӽ����÷���
			}
			
		}
	}

	private void deteleLine(int lineNum, boolean[][] map) {
		/**
		 * ���в���
		 * */
		for(int y=lineNum;y>0;y--){
			for(int x=0;x<GameDto.MAP_W;x++){
				map[x][y]=map[x][y-1];
			}
			
		}
		
	}

	public void keyLeft() {
		this.dto.getBlock().move(-1, 0, this.dto.getGameMap());
	}

	public void keyRight() {
		this.dto.getBlock().move(1, 0, this.dto.getGameMap());
	}
}
