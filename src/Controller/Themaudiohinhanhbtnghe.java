package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuanlybtngheDAO;
import DAO.QuanlydethiDAO;
import DB.DBConnection;


@WebServlet("/Themaudiohinhanhbtnghe")
public class Themaudiohinhanhbtnghe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Themaudiohinhanhbtnghe() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themaudiohinhanhbtnghe.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		
		
		String test = QuanlybtngheDAO.Themaudiohinhanhbtnghe(conn, request, response);
				
		if (test.equals("Success"))
		{
			
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsquanlybtnghe?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgthemaudiohinhanhbtnghe",test);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themaudiohinhanhbtnghe.jsp");
			rd.forward(request,response);		 
		}
	}

}
