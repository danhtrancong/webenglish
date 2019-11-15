package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import BEAN.Member;

public class LoginDAO 
{
	public static String Authenticationmember(HttpServletRequest request, Connection conn, Member member)
	{
		PreparedStatement ptmt = null;
		
		String test = "fail";
		String sql = "select membername,memberpass from member";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				String membername = rs.getString("membername");
				String memberpass= rs.getString("memberpass");
				
				if (member.getMembername().equals(membername) && member.getMemberpass().equals(memberpass))
				{
					test = "success";
				}				
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglogin",e.getMessage());
		}
		
		return test;
		
	}
	
	public static int Authorizationmember(HttpServletRequest request, Connection conn, Member member)
	{
		PreparedStatement ptmt = null;
		
		
		String sql = "select categorymemberid from member where membername='"+member.getMembername()+"' AND memberpass='"+member.getMemberpass()+"'";
		int categorymemberid = 0;
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				categorymemberid = rs.getInt("categorymemberid");		
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglogin",e.getMessage());
		}
		
		return categorymemberid;
	}
	
	
	public static String Exportnamemember(HttpServletRequest request, Connection conn, Member member)
	{
		PreparedStatement ptmt = null;
		
		
		String sql = "select name from member where membername='"+member.getMembername()+"' AND memberpass='"+member.getMemberpass()+"'";
		String name = "";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				name = rs.getString("name");
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglogin",e.getMessage());
		}
		
		return name;
	}
	
	//xuat ma thanh vien theo username va password
	public static int Exportmemberid(HttpServletRequest request, Connection conn, Member member)
	{
		PreparedStatement ptmt = null;
		
		
		String sql = "select memberid from member where membername='"+member.getMembername()+"' AND memberpass='"+member.getMemberpass()+"'";
		int memberid = 0;
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				memberid = rs.getInt("memberid");
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglogin",e.getMessage());
		}
		
		return memberid;
	}
	
	//
	
}
