package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Vocabularyguideline;
import DAO.QuanlyhdtuvungDAO;
import DB.DBConnection;


@WebServlet("/Themchudetuvung")
public class Themchudetuvung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Themchudetuvung() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		if (request.getCharacterEncoding()==null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		String vocabularyname = request.getParameter("vocabularyname");
		
		Vocabularyguideline vocabularyguideline = new Vocabularyguideline();
		vocabularyguideline.setVocabularyname(vocabularyname);
		
		Connection conn = DBConnection.CreateConnection();
		try 
		{
			boolean kt = QuanlyhdtuvungDAO.Themtenchudetuvung(request, conn, vocabularyguideline);
			
			if (kt)
			{
				int vocabularyguidelineid = QuanlyhdtuvungDAO.Xuatmachudetuvung(request, conn, vocabularyguideline);
				
				QuanlyhdtuvungDAO.Kiemtrandchudetuvung(request, conn,0, vocabularyguidelineid);
				
				request.setAttribute("vocabularyguidelineid", vocabularyguidelineid);
				
				//RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themhinhdethi.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themhinhchudetuvung.jsp");
				rd.forward(request,response);
			}
			else
			{
				request.setAttribute("msgdstuvung","Thêm chủ đề từ vựng không thành công");
				
				RequestDispatcher rd = request.getRequestDispatcher("Hienthidstuvung?pageid=1");
				rd.forward(request,response);
			}
			
			conn.close();
		} 
		catch (SQLException e) 
		{	
			request.setAttribute("msgdstuvung",e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidstuvung?pageid=1");
			rd.forward(request,response);
		}
	}

}
