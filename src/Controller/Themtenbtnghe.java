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

import BEAN.Listenexercise;
import BEAN.Readexercise;
import DAO.QuanlybtdocDAO;
import DAO.QuanlybtngheDAO;
import DB.DBConnection;

@WebServlet("/Themtenbtnghe")
public class Themtenbtnghe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Themtenbtnghe() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
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
		
		String listenexercisename = request.getParameter("listenexercisename");
		
		Listenexercise listenexercise = new Listenexercise();
		listenexercise.setListenexercisename(listenexercisename);
		
		
		Connection conn = DBConnection.CreateConnection();
		try 
		{
			boolean kt = QuanlybtngheDAO.Themtenbtnghe(request, conn, listenexercise);
			
			if (kt)
			{
				
				
				int listenexerciseid = QuanlybtngheDAO.Xuatmabtnghe(request, conn, listenexercise);
				
				QuanlybtngheDAO.Kiemtracauhoibtnghe(request, conn,0, listenexerciseid);
				
				
				request.setAttribute("listenexerciseid", listenexerciseid);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themhinhbtnghe.jsp");
				rd.forward(request,response);
			}
			else
			{
				request.setAttribute("msgquanlydsbtdoc","Thêm không thành công");
				RequestDispatcher rd = request.getRequestDispatcher("Hienthidsbtdoc?pageid=1");
				rd.forward(request,response);
			}
			
			conn.close();
		} 
		catch (SQLException e) 
		{	
			request.setAttribute("msgquanlydsbtdoc",e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsbtdoc?pageid=1");
			rd.forward(request,response);
		}
	}

}
