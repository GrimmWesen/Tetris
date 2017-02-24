package cn.edu.aust.domain;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class JdbcUtils {
	//�����࣬һ�㲻����new�� ���󣬽����췽�����˽�У��Ҹ���һ�㲻����̳�
	private static String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url="jdbc:sqlserver://localhost:1433;DatabaseName=game_tetris";
	private static String user="sa";
	private static String password="czc";
	private static boolean isConnected=true;
	
	public static boolean isConnected() {
		return isConnected;
	}

	private JdbcUtils(){
		
	}
	/*
	 * ��̬�����ִֻ��һ�Σ�һ�㶨��������
	 * ���ã����������ĳ�ʼ������̬�����������ļ��ض�ִ�У�����ִֻ��һ��
	 * new�������ִֻ��һ��
	 * �������������ͬһ�����У�������������ִ��
	 */
	
	
	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();ע��˴���׽�쳣�ķ�ʽ
			
			throw new ExceptionInInitializerError(e);
		}
	  }
	
	 public static Connection getConnection() {
		 Connection connection=null;
		try {
			connection= DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 isConnected=false;
			 JOptionPane.showMessageDialog(null, "���ݿ�δ���ӣ�");
		}
		return connection;
	  }
	
	 public static void free(ResultSet rs,Statement st,Connection conn){
		       try {
				 if(rs!=null)
				 rs.close();
			  } catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(st!=null)
					 st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null)
						 conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		
	 }
}
