package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Sliderbanner;
import DAO.HomeDAO;
import DB.DBConnection;
import java.sql.*;
import java.util.*;

@WebServlet("/Homeforward")
public class Homeforward extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    
    public Homeforward() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		List<Sliderbanner> list = HomeDAO.Displayslidebanner(conn);
		
		request.setAttribute("listslidebanner",list);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Home.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
