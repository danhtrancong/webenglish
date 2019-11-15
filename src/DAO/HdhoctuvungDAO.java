package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Vocabularycontent;
import BEAN.Vocabularyguideline;

public class HdhoctuvungDAO 
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
						request.setAttribute("msgdschudetuvung","Không có tiêu đề bài từ vựng nào");
					}
					
				} 
				catch (SQLException e) 
				{
					request.setAttribute("msgdschudetuvung",e.getMessage());
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
			
			//hien thi noi dung tu vung
			public static List<Vocabularycontent> Hienthinoidungtuvung(HttpServletRequest request,Connection conn,int vocabularyguidelineid)
			{
				List<Vocabularycontent> list = new ArrayList<Vocabularycontent>();
				
				String sql = "select * from vocabularycontent where vocabularyguidelineid="+vocabularyguidelineid;
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					if (rs.isBeforeFirst())
					{
						while (rs.next())
						{
							Vocabularycontent vocabularycontent = new Vocabularycontent();
							
							
							String vocabularycontentname = rs.getString("vocabularycontentname");
							String transcribe = rs.getString("transcribe");
							String image = rs.getString("image");
							String audiomp3 = rs.getString("audiomp3");
							String audiogg = rs.getString("audiogg");
							String mean = rs.getString("mean");
							
							vocabularycontent.setVocabularycontentname(vocabularycontentname);
							vocabularycontent.setTranscribe(transcribe);
							vocabularycontent.setImage(image);
							vocabularycontent.setAudiomp3(audiomp3);
							vocabularycontent.setAudiogg(audiogg);
							vocabularycontent.setMean(mean);
							
							
							list.add(vocabularycontent);
						}
					}
					else 
					{
						request.setAttribute("msgndchudetuvung","Không có nội dung nào");
					}
					
				} 
				catch (SQLException e) 
				{
					request.setAttribute("msgndchudetuvung",e.getMessage());
				}
						
				return list;
			}
}
