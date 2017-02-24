package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.PlayMusic;
import cn.edu.aust.config.ConfigFactory;
import cn.edu.aust.config.DialogConfig;
import cn.edu.aust.config.GameConfig;
import cn.edu.aust.controller.PlayerController;
import cn.edu.aust.dto.GameDto;

public class GamePanel extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}
	/**
	 * �ñ��������жϿ�ʼ��ť�Ƿ���ã�Ĭ��״̬��Ϊtrue
	 * */
	private boolean isStopStart=true;
	/**
	 * �洢���ڶ������������
	 * */
	private List<Dialog> dialogs = null;
	/**
	 * �������ݴ����������
	 * */
	private GameDto dto;
	/**
	 * ������ʼ��ť
	 * */
	private JButton startbutton;
	/**
	 * ������ͣ��ť
	 * */
	private JButton stopbutton;
	/**
	 * ��ȡ��ͣͼƬ��ǩ
	 * */
	private ImageIcon ICON_STOP=new ImageIcon("Graphics/String/pause_test.png");
	/**
	 * ��ȡ��ʼ��ťͼƬ
	 * */
	private ImageIcon ICON_START=new ImageIcon("Graphics/String/start.png");
	/**
	 * ��ȡ��ť�ı�ǩ
	 * */
	private static final Image IMG_START=
			new ImageIcon("Graphics/String/start.png").getImage();
	
	/**
	 * ��ȡͼƬ���
	 * */
	private final int img_x=IMG_START.getWidth(null);
	/**
	 * ��ȡͼƬ�ĸ߶�
	 * */
	private final int img_y=IMG_START.getHeight(null);
	/**
	 * ��ť��߿����
	 * */
	private static final int PADDING=40;
	/**
	 * ����Ϊ��Ϸ�����
	 */
	public GamePanel(GameDto dto) {
		this.dto=dto;
		
		initButton();
		initDialog();
	}
	private void initDialog() {
		//�����Ϸ����
		GameConfig cfg=ConfigFactory.getGameConfig();
		//�����Ϸ��ÿ�����������
		List<DialogConfig> dialogsConfig=cfg.getDialogsConfig();
		dialogs=new ArrayList<Dialog>(dialogsConfig.size());
		for(DialogConfig dialogConfig :dialogsConfig){
			try {
				//��������
				Class<?> cls = Class.forName(dialogConfig.getClassName());
				//��ù��캯��
				Constructor<?> ctr=cls.getConstructor(int.class,int.class,int.class,int.class);
				//���ù��캯����������
				Dialog dia=(Dialog) ctr.newInstance(
						dialogConfig.getX(),dialogConfig.getY(),
						dialogConfig.getW(),dialogConfig.getH()
				       );
				
				dialogs.add(dia);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//��ʼ��ÿ������ʱ�轫dto���ý�ȥ
//		dialogs = new Dialog[] {
//				// ���ƴ���
//				// Ӳ���룬���ǲ��õı��ϰ�ߣ�TODO ѧXML֮���ٴ���
//				new DialogBackground(0, 0, 0, 0),
//				new DialogDataBase(40, 32, 334, 281),
//				new DialogDisk(40, 333, 334, 281),
//				new DialogGame(419, 32, 326, 582),
//				new DialogButton(788, 32, 334, 124),
//				new DialogNext(788, 188, 176, 148),
//				new DialogLevel(964, 188, 158, 148),
//				new DialogPoint(788, 368, 334, 160),
//				new DialogAbout(788,560,334,54)
//
//		};
		for(int i=0;i<dialogs.size();i++){
			dialogs.get(i).setDto(dto);
		}
	}
	
	private void initButton(){
		//���ڳ�ʼ����ʼ��ť
		this.setLayout(null);
		 startbutton=new JButton(ICON_START);
		startbutton.setBounds(788+PADDING, 32+PADDING, img_x, img_y);
		startbutton.addActionListener(new StartAction());
		this.add(startbutton);
		
		//���ڳ�ʼ����ͣ��ť
		stopbutton =new JButton(ICON_STOP);
		stopbutton.setBounds(788+232, 32+PADDING, img_x, img_y);
		 //Ĭ�������������ͣΪ������״̬
		stopbutton.setEnabled(false);
		stopbutton.addActionListener(new StopAction());
		this.add(stopbutton);
	}

	public void setController(PlayerController playController) {
		this.addKeyListener(playController);
	}

	protected void paintComponent(Graphics g) {
		for (int i = 0; i < dialogs.size(); dialogs.get(i++).paint(g));
		//�����Ϸ���Ľ���
		this.requestFocus();
	}
		
		/**
		 * StartAction���ڼ�����ʼ��ť�¼�����Ӧ
		 * */
	private class StartAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isStopStart){
				dto.getBlock().setGameInit(true);
				//��block���е��߳��ж�������Ϊtrue��ʹ�߳��ܹ�����
				dto.getBlock().setJudgeStop(true);
				//�����߳�
				dto.getBlock().statrMainThread();
				//�ڰ��¿�ʼ��ť֮��������Ϊ������״̬
			    startbutton.setEnabled(false);
			    //����ͣ��ť�ɲ���������Ϊ����
			    stopbutton.setEnabled(true);
			    //�ٽ���ͣ��ť����Ϊ������֮�󣬽�isStopStart��false��������Ϊ��ͣ��ť����֮���Ƿ���Ч��
			    isStopStart=false;
			    PlayMusic.playMusic();
			    
			}
		}
	}
	/**
	 * StopAction���ڼ�����ʼ��ť�¼�����Ӧ
	 * */
	private class StopAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isStopStart){
				//����block�еķ����ı��̵߳���������
				dto.getBlock().setJudgeStop(false);
				//�ٽ���ʼ��ť����Ϊ����
				startbutton.setEnabled(true);
				//�ڽ���ʼ��ť����Ϊ����֮���ٴν�isStopStart��ʼ��Ϊtrue
				//����ͣ��ť����֮���轫������Ϊ������
				stopbutton.setEnabled(false);
				isStopStart=true;
				PlayMusic.stopMusic();
			}
		}
		
	}
	
}
