package cn.edu.aust.domain;

import java.util.List;

import cn.edu.aust.dto.Player;

/**
 * �ýӿ��ṩ�����ݵĳ־ò����
 * @author GAOH
 *
 */
public interface Data {
	public List<Player>  loadData() ;
	
	public void saveData(String name, int i);
}
