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
import DB.DBConnection;


@WebServlet("/Uploadgrammarguidelineimage")
public class Uploadgrammarguidelineimage extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public Uploadgrammarguidelineimage() {
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
		String grammarguidelineidstr = request.getParameter("grammarguidelineid");
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		
		String test = GrammarguidelinemanageDAO.Uploadimagegrammerguideline(conn, request, response,grammarguidelineid);
		if (test.equals("success"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("Listgrammarguidelinemanage");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgrammarguidelineimage",test);
			request.setAttribute("grammarguidelineid", grammarguidelineid);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertgrammarguidelineimage.jsp");
			rd.forward(request,response);		 
		}
		
	}

}
