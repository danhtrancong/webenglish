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
import DAO.QuanlydethiDAO;
import DB.DBConnection;

/**
 * Servlet implementation class Themhinhdethi
 */
@WebServlet("/Themhinhdethi")
public class Themhinhdethi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Themhinhdethi() {
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
		
		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		
		String test = QuanlydethiDAO.Themhinhdethi(conn, request, response, examinationid);
		if (test.equals("Success"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsquanlydethi?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgthemhinhdethi",test);
			request.setAttribute("examinationid", examinationid);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themhinhdethi.jsp");
			rd.forward(request,response);		 
		}
	}

}
