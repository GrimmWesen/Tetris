package cn.edu.aust.controller;


import util.PlayMusic;
import cn.edu.aust.domain.Data;
import cn.edu.aust.domain.DataDataBase;
import cn.edu.aust.domain.DataDisk;
import cn.edu.aust.dto.GameDto;
import cn.edu.aust.entities.GameBlock;
import cn.edu.aust.service.GameService;
import cn.edu.aust.view.GameFrame;
import cn.edu.aust.view.GamePanel;


/**
 * GameControllerΪ��Ϸ�Ŀ�������
 * @author GAOH
 *
 */
public class GameController  {
	
	private GameDto dto;
	private GameService service;
	private GameBlock block;
	private GamePanel panel;
	private PlayerController playController;
	private Data dataA;
	private Data dataB;
	
	
	public GameController() {
		      super();
		      // ��������Dto����
				 dto = new GameDto();
				  dataA=new DataDisk();
				  dataB=new DataDataBase();
				//������Ϸ�����󣬲�����dto����
				 panel = new GamePanel(dto);
				//����ҵ���߼����󣬸ö������ڶ���Ϸ�е����ݽ��д�������Dto����
				 service = new GameService(dto,dataA,dataB);
				//������ҿ�������������ҵ���߼��������Ϸ������
				 playController = new PlayerController(this);
				//����ҿ�������װ��panel��
				panel.setController(playController);
				//�����������
				 block = new GameBlock(service,panel);
				//��������󴫸�dto
				dto.setBlock(block);
				
				//�������������
				new GameFrame(panel,dataA,dataB,dto);
				//�����������ֵĶ���
				new PlayMusic();
	}

	/**
	 * ��ͣ״̬����Ҫ�ж��߳���������������ͣ״̬�²������ƶ�����
	 * */
	public void keyUp() {
		if(this.block.isJudgeStop()){
			this.service.keyUp();
		   this.panel.repaint();
		}
	}

	public void keyDown() {
		if(this.block.isJudgeStop()){
		this.service.keyDown();
		this.panel.repaint();
		}
	}

	public void keyLeft() {
		if(this.block.isJudgeStop()){
		this.service.keyLeft();
		this.panel.repaint();
		}
	}

	public void keyRight() {
		if(this.block.isJudgeStop()){
		this.service.keyRight();
		this.panel.repaint();
		}
	}

}
