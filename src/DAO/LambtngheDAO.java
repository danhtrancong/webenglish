package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Listenexercise;
import BEAN.Listenquestion;
import BEAN.Readquestion;

public class LambtngheDAO 
{
	//hien thi danh sach de thi va phan trang
	
	public static List<Listenexercise> Hienthidsbtnghe(HttpServletRequest request,int start, int count,Connection conn)
	{
		List<Listenexercise> list = new ArrayList<Listenexercise>();
		
		String sql = "select * from listenexercise limit "+(start-1)+", "+count+"";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.isBeforeFirst())
			{
				while (rs.next())
				{
					Listenexercise listenexercise = new Listenexercise();
					
					int listenexerciseid = rs.getInt("listenexerciseid");
					String listenexercisename = rs.getString("listenexercisename");
					String listenexerciseimage = rs.getString("listenexerciseimage");
					int checkcauhoi = rs.getInt("checkcauhoi");
					
					listenexercise.setListenexerciseid(listenexerciseid);
					listenexercise.setListenexercisename(listenexercisename);
					listenexercise.setListenexerciseimage(listenexerciseimage);
					listenexercise.setCheckcauhoi(checkcauhoi);
					list.add(listenexercise);
				}
			}
			else 
			{
				request.setAttribute("msgdsbtnghe","Không có bài hướng dẫn nào trong danh sách");
			}
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msgdsbtnghe",e.getMessage());
		}
				
		return list;
	}
	
	//dem so hang cua 1 bang
	public static int Countrow(Connection conn)
	{
		int count = 0;
		
		
		String sql = "select count(*) from listenexercise";
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
			
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	//hien thi danh sach de thi va phan trang
	
	public static List<Listenquestion> Hienthicauhoibtnghe(HttpServletRequest request,int start, int count,Connection conn,int listenexerciseid)
	{
		List<Listenquestion> list = new ArrayList<Listenquestion>();
		
		String sql = "select * from listenquestion where listenexerciseid= "+listenexerciseid+" limit "+(start-1)+", "+count+"";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
				while (rs.next())
				{
					Listenquestion listenquestion = new Listenquestion();
					
					int num = rs.getInt("num");
					String imagename  = rs.getString("imagename");
					String audiomp3  = rs.getString("audiomp3");
					String audiogg  = rs.getString("audiogg");
					String question = rs.getString("question");
					String option1 = rs.getString("option1");
					String option2 = rs.getString("option2");
					String option3 = rs.getString("option3");
					String option4 = rs.getString("option4");
				
					
					listenquestion.setNum(num);
					listenquestion.setImagename(imagename);
					listenquestion.setAudiomp3(audiomp3);
					listenquestion.setAudiogg(audiogg);
					listenquestion.setQuestion(question);
					listenquestion.setOption1(option1);
					listenquestion.setOption2(option2);
					listenquestion.setOption3(option3);
					listenquestion.setOption4(option4);
					
					list.add(listenquestion);
				}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
				
		return list;
	}
	
	//dem so hang cua 1 bang
	public static int Demcauhoitheoma(Connection conn,int listenexerciseid)
	{
		int count = 0;
		
		
		String sql = "select count(*) from listenquestion where listenexerciseid="+listenexerciseid;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
			
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	//xuat dap an dung theo cau hoi bt doc
	
	public static List<Listenquestion> Xuatdapanbtnghe(HttpServletRequest request,Connection conn,int listenexerciseid,int num)
	{
		List<Listenquestion> list = new ArrayList<Listenquestion>();
		
		String sql = "select * from listenquestion where listenexerciseid= "+listenexerciseid+" and num="+num;
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
				while (rs.next())
				{
					Listenquestion listenquestion = new Listenquestion();
					
					int num1 = rs.getInt("num");
					String imagename  = rs.getString("imagename");
					String audiomp3  = rs.getString("audiomp3");
					String audiogg  = rs.getString("audiogg");
					String question = rs.getString("question");
					String option1 = rs.getString("option1");
					String option2 = rs.getString("option2");
					String option3 = rs.getString("option3");
					String option4 = rs.getString("option4");
					String correctanswer = rs.getString("correctanswer");
					
					listenquestion.setNum(num1);
					listenquestion.setImagename(imagename);
					listenquestion.setAudiomp3(audiomp3);
					listenquestion.setAudiogg(audiogg);
					listenquestion.setQuestion(question);
					listenquestion.setOption1(option1);
					listenquestion.setOption2(option2);
					listenquestion.setOption3(option3);
					listenquestion.setOption4(option4);
					listenquestion.setCorrectanswer(correctanswer);
					
					list.add(listenquestion);
				}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
				
		return list;
	}
	
}
