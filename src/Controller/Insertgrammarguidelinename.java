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

import BEAN.Grammarguideline;
import DAO.GrammarguidelinemanageDAO;
import DB.DBConnection;


@WebServlet("/Insertgrammarguidelinename")
public class Insertgrammarguidelinename extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Insertgrammarguidelinename() {
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
		
		String grammarname = request.getParameter("grammarname");
		
		Grammarguideline grammarguideline = new Grammarguideline();
		grammarguideline.setGrammarname(grammarname);
		
		Connection conn = DBConnection.CreateConnection();
		try 
		{
			boolean kt = GrammarguidelinemanageDAO.Insertgrammarguidelinename(request, conn, grammarguideline);
			
			if (kt)
			{
				int grammarguidelineid = GrammarguidelinemanageDAO.Retrieveidgrammarguideline(request, conn, grammarguideline);
				request.setAttribute("grammarguidelineid", grammarguidelineid);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertgrammarguidelineimage.jsp");
				rd.forward(request,response);
			}
			else
			{
				request.setAttribute("msglistgrammarguidelinemanage","Thêm không thành công");
				RequestDispatcher rd = request.getRequestDispatcher("Listgrammarguidelinemanage");
				rd.forward(request,response);
			}
			
			conn.close();
		} 
		catch (SQLException e) 
		{	
			request.setAttribute("msglistgrammarguidelinemanage",e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Listgrammarguidelinemanage");
			rd.forward(request,response);
		}
		
	}

}
