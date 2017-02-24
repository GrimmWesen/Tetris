package cn.edu.aust.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.aust.dto.Player;

public class DataDataBase implements Data {
	
	@Override
	public List<Player> loadData()  {
		/**
		 * ���ڴ����ݿ��м�������
		 * */
		    String sql = "select top 5 tb_game.name, tb_game.point from tb_game order by point desc;" ;
		    List<Player> players=players=new ArrayList<Player>();
		    Connection conn = null;
			Statement st = null;
			ResultSet rs = null;

			try {
			conn = JdbcUtils.getConnection();
				// ��������
				if(conn!=null){
					st = conn.createStatement();
					// �������
					rs = st.executeQuery(sql);
					// ִ�����
					while (rs.next()) {
						// ������
						players.add(new Player(rs.getString(1),rs.getInt(2)));
						//rs.getString(i)��ȡ��ǰ�еĵ�i���ֶε�ֵ
					  }
				}
				
			}catch(SQLException e){
				//�˴������ݿ�����ʧ��ʱ������ӡ�쳣��Ϣ����������Ϸ���治��ӡ���ݿ����
				
				//e.printStackTrace();
			} 
			finally {
				JdbcUtils.free(rs, st, conn);
			}
			return players;
	}

	@Override
	public void saveData(String name,int point) {
		/**
		 * ���ڽ����ݱ��������ݿ�
		 * */
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
		conn = JdbcUtils.getConnection();
			// ��������
			if(conn!=null){
					String sql="select * from  tb_game;";
					ps = conn.prepareStatement(sql);
					// �������
					rs = ps.executeQuery();
					// ִ�����
					while (rs.next()) {
						// ������
						
						if(rs.getString(2).equals(name)){
							
							updateDB(conn,ps,name,point);
						}
				}
					inserDB(conn,ps,name,point);
			}
			
		}catch(SQLException e){
			//�˴������ݿ�����ʧ��ʱ������ӡ�쳣��Ϣ����������Ϸ���治��ӡ���ݿ����
			e.printStackTrace();
		} 
		finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

	private void inserDB(Connection conn, PreparedStatement ps, String name, int point) {
			String sql="insert into tb_game values(?,?);";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2, point);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	private void updateDB(Connection conn, PreparedStatement ps, String name, int point) {
		String sql="update tb_game set point=? where name=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, point);
			ps.setString(2, name);
			// �������
		    ps.executeUpdate();
			// ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
