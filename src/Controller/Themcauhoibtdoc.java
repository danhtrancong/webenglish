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
import DAO.QuanlydethiDAO;
import DB.DBConnection;


@WebServlet("/Themcauhoibtdoc")
public class Themcauhoibtdoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Themcauhoibtdoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String readexeriseid = request.getParameter("readexeriseid");
		request.setAttribute("readexeriseid",readexeriseid);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themcauhoibtdoc.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		
		String readexeriseidstr = request.getParameter("readexeriseid");
		int readexeriseid = Integer.parseInt(readexeriseidstr);
		
		
		String test = QuanlybtdocDAO.Uploadcauhoibtdoc(conn, request, response, readexeriseid);
		
		if (test.equals("Success"))
		{
			QuanlybtdocDAO.Kiemtracauhoibtdoc(request, conn, 1 , readexeriseid); 
			
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsbtdoc?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgthemcauhoibtdoc",test);
			request.setAttribute("readexeriseid", readexeriseid);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themcauhoibtdoc.jsp");
			rd.forward(request,response);		 
		}
	}

}
