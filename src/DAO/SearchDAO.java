package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Grammarguideline;

public class SearchDAO 
{
	public static List<Grammarguideline> Displayresult (HttpServletRequest request,Connection conn, String name1)
	{
		List<Grammarguideline> list = new ArrayList<Grammarguideline>();
		
		String sql = "select * from grammarguideline where grammarname like '%"+name1+"%'";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (!rs.isBeforeFirst())
			{
				request.setAttribute("ketqua","Không có kết quả");
			}
			else 
			{
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
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("ketqua",e.getMessage());
		}
		
		return list;
	}
}
