package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BEAN.Grammarguideline;
import DAO.SearchDAO;
import DB.DBConnection;


@WebServlet("/Searchcontroller")
public class Searchcontroller extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public Searchcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		String grammarname = request.getParameter("grammarname");
		
		List<Grammarguideline> list = SearchDAO.Displayresult(request, conn, grammarname);
		
		
		request.setAttribute("listsearch",list);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Ketquatimkiem.jsp");
		rd.forward(request,response);
	}

}
