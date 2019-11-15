package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GrammarguidelinemanageDAO;
import DAO.QuanlybtdocDAO;
import DAO.QuanlydethiDAO;
import DB.DBConnection;

@WebServlet("/Themhinhbtdoc")
public class Themhinhbtdoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
    public Themhinhbtdoc() {
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
		Connection conn = DBConnection.CreateConnection();
		
		String readexeriseidstr = request.getParameter("readexeriseid");
		int readexeriseid = Integer.parseInt(readexeriseidstr);
		
		//String test = QuanlydethiDAO.Themhinhdethi(conn, request, response, examinationid);
		
		String test = QuanlybtdocDAO.Themhinhbtdoc(conn, request, response, readexeriseid);
		
		if (test.equals("Success"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsbtdoc?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgthemhinhbtdoc",test);
			request.setAttribute("readexeriseid", readexeriseid);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themhinhbtdoc.jsp");
			rd.forward(request,response);		 
		}
	}

}
