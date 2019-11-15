package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Readexercise;
import BEAN.Readquestion;

public class LambtphandocDAO 
{
			//hien thi danh sach de thi va phan trang
	
			public static List<Readexercise> Hienthidsbtdoc(HttpServletRequest request,int start, int count,Connection conn)
			{
				List<Readexercise> list = new ArrayList<Readexercise>();
				
				String sql = "select * from readexercise limit "+(start-1)+", "+count+"";
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					if (rs.isBeforeFirst())
					{
						while (rs.next())
						{
							Readexercise readexercise = new Readexercise();
							
							int readexeriseid = rs.getInt("readexeriseid");
							String readname = rs.getString("readname");
							String readimage = rs.getString("readimage");
							int checkcauhoi = rs.getInt("checkcauhoi");
							
							readexercise.setReadexeriseid(readexeriseid);
							readexercise.setReadname(readname);
							readexercise.setReadimage(readimage);
							readexercise.setCheckcauhoi(checkcauhoi);
							
							list.add(readexercise);
						}
					}
					else 
					{
						request.setAttribute("msgdsbtdoc","Không có bài tập nào trong danh sách");
					}
					
				} 
				catch (SQLException e) 
				{
					request.setAttribute("msgdsbtdoc",e.getMessage());
				}
						
				return list;
			}
			
			//dem so hang cua 1 bang
			public static int Countrow(Connection conn)
			{
				int count = 0;
				
				
				String sql = "select count(*) from readexercise";
				
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
			
			public static List<Readquestion> Hienthicauhoibtdoc(HttpServletRequest request,int start, int count,Connection conn,int readexeriseid)
			{
				List<Readquestion> list = new ArrayList<Readquestion>();
				
				String sql = "select * from readquestion where readexeriseid= "+readexeriseid+" limit "+(start-1)+", "+count+"";
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					
						while (rs.next())
						{
							Readquestion readquestion = new Readquestion();
							
							int num = rs.getInt("num");
							String question = rs.getString("question");
							String option1 = rs.getString("option1");
							String option2 = rs.getString("option2");
							String option3 = rs.getString("option3");
							String option4 = rs.getString("option4");
						
							
							readquestion.setNum(num);
							readquestion.setQuestion(question);
							readquestion.setOption1(option1);
							readquestion.setOption2(option2);
							readquestion.setOption3(option3);
							readquestion.setOption4(option4);
							
							list.add(readquestion);
						}
					
				} 
				catch (SQLException e) 
				{
					request.setAttribute("msglambtphandoc",e.getMessage());
				}
						
				return list;
			}
			
			//dem so hang cua 1 bang
			public static int Demcauhoitheoma(Connection conn,int readexeriseid)
			{
				int count = 0;
				
				
				String sql = "select count(*) from readquestion where readexeriseid="+readexeriseid;
				
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
			
			public static List<Readquestion> Xuatdapanbtdoc(HttpServletRequest request,Connection conn,int readexeriseid,int num)
			{
				List<Readquestion> list = new ArrayList<Readquestion>();
				
				String sql = "select * from readquestion where readexeriseid= "+readexeriseid+" and num="+num;
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					
						while (rs.next())
						{
							Readquestion readquestion = new Readquestion();
							
							int num1 = rs.getInt("num");
							String question = rs.getString("question");
							String option1 = rs.getString("option1");
							String option2 = rs.getString("option2");
							String option3 = rs.getString("option3");
							String option4 = rs.getString("option4");
							String correctanswer = rs.getString("correctanswer");
							
							readquestion.setNum(num1);
							readquestion.setQuestion(question);
							readquestion.setOption1(option1);
							readquestion.setOption2(option2);
							readquestion.setOption3(option3);
							readquestion.setOption4(option4);
							readquestion.setCorrectanswer(correctanswer);
							
							list.add(readquestion);
						}
					
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
						
				return list;
			}
			
}
