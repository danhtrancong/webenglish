package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Cmtgrammar;
import BEAN.Grammarguideline;


public class BaihdnguphapDAO 
{
	public static List<Grammarguideline> Displaygrammarguideline(int start, int count, Connection conn)
	{
		List<Grammarguideline> list = new ArrayList<Grammarguideline>();
		
		String sql = "select * from grammarguideline limit "+(start-1)+", "+count+"";
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				Grammarguideline grammarguideline = new Grammarguideline();
				
				int grammarguidelineid = rs.getInt("grammarguidelineid");
				String grammarname = rs.getString("grammarname");
				String grammarimage = rs.getString("grammarimage");
				
				grammarguideline.setGrammarguidelineid(grammarguidelineid);
				grammarguideline.setGrammarname(grammarname);
				grammarguideline.setGrammarimage(grammarimage);
				
				list.add(grammarguideline);
			}
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	public static int Countrow(Connection conn)
	{
		int count = 0;
		
		
		String sql = "select count(*) from grammarguideline";
		
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
	
	public static String Displaygrammarcontent(Connection conn, int mabaihdnguphap)
	{
		String content = "";
		
		String sql = "select * from grammarguideline where grammarguidelineid="+mabaihdnguphap;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				content = rs.getString("content");			
			}
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}	
		return content;
	}
	
	public static List<Cmtgrammar> Displaycmtgrammar (Connection conn, int grammarguidelineid)
	{
		List<Cmtgrammar> list = new ArrayList<Cmtgrammar>();
		
		String sql = "select * from cmtgrammar where grammarguidelineid="+grammarguidelineid;
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
			while (rs.next())
			{
				Cmtgrammar cmt = new Cmtgrammar();
				int memberid = rs.getInt("memberid");
				String cmtgrammarcontent = rs.getString("cmtgrammarcontent");
				
				String name = CommentgrammarDAO.Namemember(conn,memberid);
				
				
				cmt.setCmtgrammarcontent(cmtgrammarcontent);
				cmt.setName(name);
				
				list.add(cmt);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
}
