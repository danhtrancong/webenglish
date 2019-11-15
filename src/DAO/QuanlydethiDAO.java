package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import BEAN.Examination;
import BEAN.Grammarguideline;
import BEAN.Examinationquestion;

public class QuanlydethiDAO 
{
	
	//hien thi danh sach de thi va phan trang
	public static List<Examination> Hienthidsdethi(HttpServletRequest request,int start, int count,Connection conn)
	{
		List<Examination> list = new ArrayList<Examination>();
		
		String sql = "select * from examination limit "+(start-1)+", "+count+"";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.isBeforeFirst())
			{
				while (rs.next())
				{
					Examination examination = new Examination();
					
					int examinationid = rs.getInt("examinationid");
					String examinationame = rs.getString("examinationame");
					String examinatioimage = rs.getString("examinatioimage");
					int checkedcauhoi = rs.getInt("checkedcauhoi");
					
					examination.setExaminationid(examinationid);
					examination.setExaminationame(examinationame);
					examination.setExaminatioimage(examinatioimage);
					examination.setCheckedcauhoi(checkedcauhoi);
					
					list.add(examination);
				}
			}
			else 
			{
				request.setAttribute("msgquanlydethi","Không có bài hướng dẫn nào trong danh sách");
			}
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msgquanlydethi",e.getMessage());
		}
				
		return list;
	}
	
	//dem so hang cua 1 bang
	public static int Countrow(Connection conn)
	{
		int count = 0;
		
		
		String sql = "select count(*) from examination";
		
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
	
		//them ten de thi moi
		public static boolean Themtendethi(HttpServletRequest request, Connection conn, Examination examination)
		{
			PreparedStatement ptmt = null;
			
			String sql = "insert into examination(examinationame) values (?)";
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
				
				String examinationame = examination.getExaminationame();
				
				
				ptmt.setString(1,examinationame);
				
				int kt = ptmt.executeUpdate();
				
				if (kt != 0)
				{
					return true;
				}
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			return false;	
		}
		
		
		//xuat ma de thi
		public static int Xuatmadethi(HttpServletRequest request, Connection conn, Examination examination)
		{
			int examinationid = 0;
			
			PreparedStatement ptmt = null;
			
			
			String sql = "select examinationid from examination where examinationame='"+examination.getExaminationame()+"'";
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
				
				
				ResultSet rs = ptmt.executeQuery();
				
				while (rs.next())
				{
					examinationid = rs.getInt("examinationid");		
				}
				
				ptmt.close();
				rs.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			return examinationid;
		}
		
		
				//update ten hinh cho de thi
				public static void Updatetenhinhdethi(HttpServletRequest request, Connection conn,String image,int examinationid)
				{
					PreparedStatement ptmt = null;
					
					String sql = "update examination set examinatioimage=? where examinationid="+examinationid;
					
					try 
					{
						ptmt = conn.prepareStatement(sql);
						
						
						
						ptmt.setString(1,image);
						
						ptmt.executeUpdate();
						
						ptmt.close();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
				
				
				//ham them file anh va update ten de thi
				
				public static String Themhinhdethi(Connection conn, HttpServletRequest request,HttpServletResponse response,int examinationid) 
						throws ServletException, IOException 
				{
					String test = "";
					ServletContext context = request.getServletContext();
					response.setContentType("text/html; charset=UTF-8");
					
					final String Address = context.getRealPath("Imageaudiodethi/");
				
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
						    			QuanlydethiDAO.Updatetenhinhdethi(request, conn, fileName, examinationid);
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
				
				//ham theo file excel vao thu muc fildethi trong project
				
				public static String Uploadcauhoidethi(Connection conn, HttpServletRequest request,HttpServletResponse response,int examinationid) 
						throws ServletException, IOException 
				{
					String test = "";
					ServletContext context = request.getServletContext();
					response.setContentType("text/html; charset=UTF-8");
					
					final String Address = context.getRealPath("Filedethi/");
				
				
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
						    			try
						    			{
						    				QuanlydethiDAO.Themcauhoituexcel(request, response, conn, pathFile, examinationid);
						    			}
						    			catch(NullPointerException e)
						    			{
						    				test = e.getMessage();
						    			}
						    			
						    			
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
				
				
				
				//them cau hoi de thi tu file excel
				public static void Themcauhoituexcel(HttpServletRequest request,HttpServletResponse response, Connection conn, String address, int examinationid) 
						throws ServletException, IOException
				{
					FileInputStream inp;
					try 
					{
						inp = new FileInputStream(address);
						HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
						
						Sheet sheet = wb.getSheetAt(0);
						
						
						
						for (int i=1; i<=sheet.getLastRowNum();i++)
						{
							Row row = sheet.getRow(i);
							
							int num = (int) row.getCell(0).getNumericCellValue();			
							String imagequestion = row.getCell(1).getStringCellValue();						
							String audiogg = row.getCell(2).getStringCellValue();
							String audiomp3 = row.getCell(3).getStringCellValue();
							String paragraph = row.getCell(4).getStringCellValue();
							String question = row.getCell(5).getStringCellValue();
							String option1 = row.getCell(6).getStringCellValue();
							String option2 = row.getCell(7).getStringCellValue();
							String option3 = row.getCell(8).getStringCellValue();
							String option4 = row.getCell(9).getStringCellValue();
							String correctanswer = row.getCell(10).getStringCellValue();
							
							Examinationquestion ex = new Examinationquestion();
							
							ex.setNum(num);
							ex.setImagequestion(imagequestion);
							ex.setAudiogg(audiogg);
							ex.setAudiomp3(audiomp3);
							ex.setParagraph(paragraph);
							ex.setQuestion(question);
							ex.setOption1(option1);
							ex.setOption2(option2);
							ex.setOption3(option3);
							ex.setOption4(option4);
							ex.setCorrectanswer(correctanswer);
							ex.setExaminationid(examinationid);
							
							QuanlydethiDAO.Themcauhoivaomysql(request, ex, conn);
							
						}
						
						wb.close();
						
					} 
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
						
					}
					catch (IOException e) 
					{
						e.printStackTrace();
						
					}
				}
				
				
				
				//Them cau hoi vao mysql
				public static void Themcauhoivaomysql(HttpServletRequest request,Examinationquestion ex, Connection conn)
				{
					String sql = "insert into examinationquestion(num,imagequestion,audiogg,audiomp3,paragraph,question,"
							+ "option1,option2,option3,option4,correctanswer,examinationid) values (?,?,?,?,?,?,?,?,?,?,?,?)";
					try 
					{
						PreparedStatement ptmt = conn.prepareStatement(sql);
						
						
						ptmt.setInt(1,ex.getNum());
						ptmt.setString(2,ex.getImagequestion());
						ptmt.setString(3,ex.getAudiogg());
						ptmt.setString(4,ex.getAudiomp3());
						ptmt.setString(5,ex.getParagraph());
						ptmt.setString(6,ex.getQuestion());
						ptmt.setString(7,ex.getOption1());
						ptmt.setString(8,ex.getOption2());
						ptmt.setString(9,ex.getOption3());
						ptmt.setString(10,ex.getOption4());
						ptmt.setString(11,ex.getCorrectanswer());
						ptmt.setInt(12,ex.getExaminationid());
						
						ptmt.executeUpdate();
						
						ptmt.close();
						
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
		
				//update checked cau hoi de thi
				public static void Kiemtracauhoidethi(HttpServletRequest request, Connection conn,int checkedcauhoi,int examinationid)
				{
					PreparedStatement ptmt = null;
					
					String sql = "update examination set checkedcauhoi=? where examinationid="+examinationid;
					
					try 
					{
						ptmt = conn.prepareStatement(sql);
							
						
						ptmt.setInt(1,checkedcauhoi);
						
						ptmt.executeUpdate();
						
						ptmt.close();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}		
				
				
				//hàm thêm audio và hình ảnh cho đề thi
				public static String Themaudiohinhanhdethi(Connection conn, HttpServletRequest request,HttpServletResponse response) 
						throws ServletException, IOException 
				{
					String test = "";
					ServletContext context = request.getServletContext();
					response.setContentType("text/html; charset=UTF-8");
					
					final String Address = context.getRealPath("Imageaudiodethi/");
				
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
				
}
