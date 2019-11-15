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
import BEAN.Examinationquestion;
import BEAN.Vocabularycontent;
import BEAN.Vocabularyguideline;

public class QuanlyhdtuvungDAO 
{
		//hien thi danh sach de thi va phan trang
		public static List<Vocabularyguideline> Hienthidstuvung(HttpServletRequest request,int start, int count,Connection conn)
		{
			List<Vocabularyguideline> list = new ArrayList<Vocabularyguideline>();
			
			String sql = "select * from vocabularyguideline limit "+(start-1)+", "+count+"";
			try 
			{
				PreparedStatement ptmt = conn.prepareStatement(sql);
				
				ResultSet rs = ptmt.executeQuery();
				
				if (rs.isBeforeFirst())
				{
					while (rs.next())
					{
						Vocabularyguideline vocabularyguideline = new Vocabularyguideline();
						
						int vocabularyguidelineid = rs.getInt("vocabularyguidelineid");
						String vocabularyname = rs.getString("vocabularyname");
						String vocabularyimage = rs.getString("vocabularyimage");
						int checknoidung = rs.getInt("checknoidung");
						
						vocabularyguideline.setVocabularyguidelineid(vocabularyguidelineid);
						vocabularyguideline.setVocabularyname(vocabularyname);
						vocabularyguideline.setVocabularyimage(vocabularyimage);
						vocabularyguideline.setChecknoidung(checknoidung);
						
						list.add(vocabularyguideline);
					}
				}
				else 
				{
					request.setAttribute("msgdstuvung","Không có tiêu đề bài từ vựng nào");
				}
				
			} 
			catch (SQLException e) 
			{
				request.setAttribute("msgdstuvung",e.getMessage());
			}
					
			return list;
		}
		
		//dem so hang cua 1 bang
		public static int Countrow(Connection conn)
		{
			int count = 0;
			
			
			String sql = "select count(*) from vocabularyguideline";
			
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
		
				//them ten de chu de tu vung
				public static boolean Themtenchudetuvung(HttpServletRequest request, Connection conn,Vocabularyguideline vocabularyguideline)
				{
					PreparedStatement ptmt = null;
					
					String sql = "insert into vocabularyguideline(vocabularyname) values (?)";
					
					try 
					{
						ptmt = conn.prepareStatement(sql);
						
						String vocabularyname = vocabularyguideline.getVocabularyname();
						
						
						ptmt.setString(1,vocabularyname);
						
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
				
				
				//xuat ma chu de tu vung
				public static int Xuatmachudetuvung(HttpServletRequest request, Connection conn, Vocabularyguideline vocabularyguideline)
				{
					int vocabularyguidelineid = 0;
					
					PreparedStatement ptmt = null;
					
					
					String sql = "select vocabularyguidelineid from vocabularyguideline where vocabularyname='"+vocabularyguideline.getVocabularyname()+"'";
					
					try 
					{
						ptmt = conn.prepareStatement(sql);
						
						
						ResultSet rs = ptmt.executeQuery();
						
						while (rs.next())
						{
							vocabularyguidelineid = rs.getInt("vocabularyguidelineid");		
						}
						
						ptmt.close();
						rs.close();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
					return vocabularyguidelineid;
				}
			
				//update checked chu de tu vung
				public static void Kiemtrandchudetuvung(HttpServletRequest request, Connection conn,int checknoidung,int vocabularyguidelineid)
				{
					PreparedStatement ptmt = null;
					
					String sql = "update vocabularyguideline set checknoidung=? where vocabularyguidelineid="+vocabularyguidelineid;
					
					try 
					{
						ptmt = conn.prepareStatement(sql);
							
						
						ptmt.setInt(1,checknoidung);
						
						ptmt.executeUpdate();
						
						ptmt.close();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
				
				
				//ham them file anh va update ten chu de tu vung
				
				public static String Themhinhchudetuvung(Connection conn, HttpServletRequest request,HttpServletResponse response,int vocabularyguidelineid) 
						throws ServletException, IOException 
				{
					String test = "";
					ServletContext context = request.getServletContext();
					response.setContentType("text/html; charset=UTF-8");
					
					final String Address = context.getRealPath("Imageaudiohdtuvung/");
				
	
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
						    			
						    			QuanlyhdtuvungDAO.Updatetenhinhchudetuvung(request, conn, fileName, vocabularyguidelineid);
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
				
				//update ten hinh cho chu de tu vung
				public static void Updatetenhinhchudetuvung(HttpServletRequest request, Connection conn,String image,int vocabularyguidelineid)
				{
					PreparedStatement ptmt = null;
					
					String sql = "update vocabularyguideline set vocabularyimage=? where vocabularyguidelineid="+vocabularyguidelineid;
					
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
				
				//ham them noi dung tu file excel cho chu de tu vung
				
				public static String Uploadndchudetuvung(Connection conn, HttpServletRequest request,HttpServletResponse response,int vocabularyguidelineid) 
						throws ServletException, IOException 
				{
					String test = "";
					ServletContext context = request.getServletContext();
					response.setContentType("text/html; charset=UTF-8");
					
					final String Address = context.getRealPath("/Filendchudetuvung");
				
				
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
						    				
						    				QuanlyhdtuvungDAO.Themndchudetuvungtuexcel(request, response, conn, pathFile, vocabularyguidelineid);
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
				
				//them noi dung chu de tu vung tu file excel
				public static void Themndchudetuvungtuexcel(HttpServletRequest request,HttpServletResponse response, Connection conn, String address, int vocabularyguidelineid) 
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
							String vocabularycontentname = row.getCell(1).getStringCellValue();						
							String transcribe = row.getCell(2).getStringCellValue();
							String image = row.getCell(3).getStringCellValue();
							String audiomp3 = row.getCell(4).getStringCellValue();
							String audiogg = row.getCell(5).getStringCellValue();
							String mean = row.getCell(6).getStringCellValue();
							
							
							Vocabularycontent ex = new Vocabularycontent();
							
							ex.setNum(num);
							ex.setVocabularycontentname(vocabularycontentname);
							ex.setTranscribe(transcribe);
							ex.setImage(image);
							ex.setAudiomp3(audiomp3);
							ex.setAudiogg(audiogg);
							ex.setMean(mean);
							ex.setVocabularyguidelineid(vocabularyguidelineid);
							
							QuanlyhdtuvungDAO.Themndtuvungvaomysql(request, ex, conn);
							
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
				
				
				//Them noi dung tu vung vao mysql
				public static void Themndtuvungvaomysql(HttpServletRequest request,Vocabularycontent ex, Connection conn)
				{
					String sql = "insert into vocabularycontent(num,vocabularycontentname,transcribe,image,audiomp3,audiogg,mean,vocabularyguidelineid) values (?,?,?,?,?,?,?,?)";
							
					try 
					{
						PreparedStatement ptmt = conn.prepareStatement(sql);
						
						
						ptmt.setInt(1,ex.getNum());
						ptmt.setString(2,ex.getVocabularycontentname());
						ptmt.setString(3,ex.getTranscribe());
						ptmt.setString(4,ex.getImage());
						ptmt.setString(5,ex.getAudiomp3());
						ptmt.setString(6,ex.getAudiogg());
						ptmt.setString(7,ex.getMean());			
						ptmt.setInt(8,ex.getVocabularyguidelineid());
						
						ptmt.executeUpdate();
						
						ptmt.close();
						
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
				
				//hàm thêm audio và hình ảnh cho hướng dẫn từ vựng
				public static String Themaudiohinhanhtuvung(Connection conn, HttpServletRequest request,HttpServletResponse response) 
						throws ServletException, IOException 
				{
					String test = "";
					ServletContext context = request.getServletContext();
					response.setContentType("text/html; charset=UTF-8");
					
					final String Address = context.getRealPath("Imageaudiohdtuvung/");
				
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
