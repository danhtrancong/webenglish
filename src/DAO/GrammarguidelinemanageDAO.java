package DAO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import BEAN.Grammarguideline;

public class GrammarguidelinemanageDAO 
{
	
	public static List<Grammarguideline> Displaysgrammarguidelinemanage (HttpServletRequest request,int start, int count,Connection conn)
	{
		List<Grammarguideline> list = new ArrayList<Grammarguideline>();
		
		String sql = "select * from grammarguideline limit "+(start-1)+", "+count+"";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.isBeforeFirst())
			{
				while (rs.next())
				{
					Grammarguideline gmgl = new Grammarguideline();
					
					int grammarguidelineid = rs.getInt("grammarguidelineid");
					String grammarname = rs.getString("grammarname");
					String grammarimage = rs.getString("grammarimage");
					String content = rs.getString("content");
					
					gmgl.setGrammarguidelineid(grammarguidelineid);
					gmgl.setGrammarname(grammarname);
					gmgl.setGrammarimage(grammarimage);
					gmgl.setContent(content);
					
					list.add(gmgl);
				}
			}
			else 
			{
				request.setAttribute("msglistgrammarguidelinemanage","Không có bài hướng dẫn nào trong danh sách");
			}
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglistgrammarguidelinemanage",e.getMessage());
		}
				
		return list;
	}
	
	//dem bang do co bao nhieu hang
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
	
	
	
	//them ten bai huong dan vao csdl
	public static boolean Insertgrammarguidelinename(HttpServletRequest request, Connection conn, Grammarguideline grammarguideline)
	{
		PreparedStatement ptmt = null;
		
		String sql = "insert into grammarguideline(grammarname) values (?)";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			String grammarname = grammarguideline.getGrammarname();
			
			
			ptmt.setString(1,grammarname);
			
			int kt = ptmt.executeUpdate();
			
			if (kt != 0)
			{
				return true;
			}
			
			ptmt.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglistgrammarguidelinemanage",e.getMessage());
		}
		
		return false;	
	}
	
	//ham them file anh cua cac de thi trong phan huong dan hoc ngu phap
	
	public static String Uploadimagegrammerguideline(Connection conn, HttpServletRequest request,HttpServletResponse response,int grammarguidelineid) 
			throws ServletException, IOException 
	{
		String test = "";
		ServletContext context = request.getServletContext();
		response.setContentType("text/html; charset=UTF-8");
		
		final String Address = context.getRealPath("Imageupload/");
	
		//final String Address = "F://";
		final int MaxMemorySize = 1024 * 1024 * 3; //3MB
		final int MaxRequestSize = 1024 * 1024 * 50; //50 MB
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart)
		{
			test = "Thiếu multipart/form-data trong form";
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		
		// Set factory constraints
		factory.setSizeThreshold(MaxMemorySize);

		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		
		// Set overall request size constraint
		upload.setSizeMax(MaxRequestSize);
		
		
		
		try 
		{
			// Parse the request
			List<FileItem> items = upload.parseRequest(request);
			
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			
			while (iter.hasNext()) 
			{
			    FileItem item = iter.next();

			    if (!item.isFormField()) 
			    {
			    	 String fileName = item.getName();
			    	 
			    	 //pathFile: vị trí mà chúng ta muốn upload file vào
			    	 //gửi cho server
			    	 
			    	String pathFile = Address + File.separator + fileName;
			    	 
			    	File uploadedFile = new File(pathFile);
			    	
			    	
			    	boolean kt = uploadedFile.exists();
			    	 
			    	try 
			    	{
			    		
			    		if (kt == true)
			    		{
			    					    
			    			test = "file tồn tại. Yêu cầu chọn file khác";
			    		}
			    		else
			    		{		    			
			    			item.write(uploadedFile);
			    			GrammarguidelinemanageDAO.Insertgrammarimage(request, conn,fileName, grammarguidelineid);
			    			test="Success";
			    		}
						
						
					} 
			    	catch (Exception e) 
			    	{

			    		
			    		test = e.getMessage();
					}   	 
			    } 
			    else 
			    {
			    	test = "thêm file thất bại";
			    }
			}
			
		} 
		catch (FileUploadException e) 
		{
			test = e.getMessage();
		}
		
		return test;
	}
	
	//xuat id cua bai huong dan ngu phap
	public static int Retrieveidgrammarguideline(HttpServletRequest request, Connection conn, Grammarguideline grammarguideline)
	{
		int grammarguidelineid = 0;
		
		PreparedStatement ptmt = null;
		
		
		String sql = "select grammarguidelineid from grammarguideline where grammarname='"+grammarguideline.getGrammarname()+"'";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				grammarguidelineid = rs.getInt("grammarguidelineid");		
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msgrammarguidelineimage",e.getMessage());
		}
		
		return grammarguidelineid;
	}
	
	
	
	
		//update ten hinh cho bai huong dan dua theo id cua bai huong dan
		public static void Insertgrammarimage(HttpServletRequest request, Connection conn,String image,int grammarguidelineid)
		{
			PreparedStatement ptmt = null;
			
			String sql = "update grammarguideline set grammarimage=? where grammarguidelineid="+grammarguidelineid;
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
				
				
				
				ptmt.setString(1,image);
				
				ptmt.executeUpdate();
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{
				request.setAttribute("msglistgrammarguidelinemanage",e.getMessage());
			}
		}
	
		//them noi dung vao bai huong dan
		public static boolean Insertgrammarguidelinecontent(HttpServletRequest request, Connection conn, Grammarguideline grammarguideline,int id)
		{
			PreparedStatement ptmt = null;
			
			String sql = "update grammarguideline set content=? where grammarguidelineid="+id;
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
				
				String content = grammarguideline.getContent();
				
				
				ptmt.setString(1,content);
				
				int kt = ptmt.executeUpdate();
				
				if (kt != 0)
				{
					return true;
				}
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{
				request.setAttribute("msggrammarguidelinecontent",e.getMessage());
			}
			
			return false;	
		}
		
		//xoa bai huong dan
		public static void Xoabaihdnguphap(Connection conn, int grammarguidelineid)
		{
			String sql = "delete from grammarguideline where grammarguidelineid="+grammarguidelineid;
			try 
			{
				PreparedStatement ptmt = conn.prepareStatement(sql);
				
				ptmt.executeUpdate();
				
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{

				e.printStackTrace();
			}
		}
		
		//xoa ma bai huong dan trong cmtgrammar
		public static void Xoamahdnguphaptrongcmtgrammar(Connection conn, int grammarguidelineid)
		{
			String sql = "delete from cmtgrammar where grammarguidelineid="+grammarguidelineid;
			try 
			{
				PreparedStatement ptmt = conn.prepareStatement(sql);
				
				ptmt.executeUpdate();
				
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{

				e.printStackTrace();
			}
		}
}
