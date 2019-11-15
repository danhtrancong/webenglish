package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuanlybtdocDAO;
import DAO.QuanlybtngheDAO;
import DB.DBConnection;


@WebServlet("/Themcauhoibtnghe")
public class Themcauhoibtnghe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Themcauhoibtnghe() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String listenexerciseid = request.getParameter("listenexerciseid");
		request.setAttribute("listenexerciseid",listenexerciseid);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themcauhoibtnghe.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		
		String listenexerciseidstr = request.getParameter("listenexerciseid");
		int listenexerciseid = Integer.parseInt(listenexerciseidstr);
		
		
		String test = QuanlybtngheDAO.Uploadcauhoibtnghe(conn, request, response, listenexerciseid);
		
		if (test.equals("Success"))
		{
			QuanlybtngheDAO.Kiemtracauhoibtnghe(request, conn,1 , listenexerciseid);
			
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsquanlybtnghe?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgthemcauhoibtnghe",test);
			request.setAttribute("listenexerciseid", listenexerciseid);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themcauhoibtnghe.jsp");
			rd.forward(request,response);		 
		}
	}

}
