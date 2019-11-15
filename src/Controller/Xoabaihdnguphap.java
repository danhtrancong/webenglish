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

import DAO.GrammarguidelinemanageDAO;
import DB.DBConnection;


@WebServlet("/Xoabaihdnguphap")
public class Xoabaihdnguphap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Xoabaihdnguphap() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		
		String grammarguidelineidstr = request.getParameter("grammarguidelineid");
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		
		try 
		{
			
			
			GrammarguidelinemanageDAO.Xoamahdnguphaptrongcmtgrammar(conn, grammarguidelineid);
			GrammarguidelinemanageDAO.Xoabaihdnguphap(conn, grammarguidelineid);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("Listgrammarguidelinemanage?pageid=1");
			rd.forward(request,response);
			
			conn.close();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
