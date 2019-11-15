package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Cmtgrammar;

public class CommentgrammarDAO 
{
	public static void Insertcmtgrammar(HttpServletRequest request, Connection conn, Cmtgrammar cmtgrammar)
	{
		PreparedStatement ptmt = null;
		
		String sql = "insert into cmtgrammar(cmtgrammarcontent,memberid,grammarguidelineid) values (?,?,?)";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			String cmtgrammarcontent = cmtgrammar.getCmtgrammarcontent();
			int memberid = cmtgrammar.getMemberid();
			int grammarguidelineid = cmtgrammar.getGrammarguidelineid();
			
			
			ptmt.setString(1,cmtgrammarcontent);
			ptmt.setInt(2,memberid);
			ptmt.setInt(3,grammarguidelineid);
			
			ptmt.executeUpdate();
			
			ptmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
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
	
	//xuat ten cua thanh vien theo ma
	public static String Namemember(Connection conn,int memberid)
	{
		String name = "";
		
		
		String sql = "select name from member where memberid="+memberid;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			rs.next();
			
			name = rs.getString("name");
			
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return name;
	}
	
	public static int Countrow(Connection conn,int grammarguidelineid)
	{
		int count = 0;
		
		
		String sql = "select count(*) from cmtgrammar where grammarguidelineid="+grammarguidelineid;
		
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
}
