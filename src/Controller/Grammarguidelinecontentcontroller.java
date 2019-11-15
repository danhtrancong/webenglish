package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Grammarguideline;
import DAO.GrammarguidelinemanageDAO;
import DB.DBConnection;


@WebServlet("/Grammarguidelinecontentcontroller")
public class Grammarguidelinecontentcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Grammarguidelinecontentcontroller() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
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
		Connection conn = DBConnection.CreateConnection();
		
		String content = request.getParameter("content");
		String grammarguidelineidstr = request.getParameter("grammarguidelineid");
		
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		
		Grammarguideline grammarguideline = new Grammarguideline();
		
		grammarguideline.setContent(content);
		
		
		boolean kt = GrammarguidelinemanageDAO.Insertgrammarguidelinecontent(request, conn, grammarguideline,grammarguidelineid);
		if (kt)
		{
			RequestDispatcher rd = request.getRequestDispatcher("Listgrammarguidelinemanage");
			rd.forward(request,response);
		}
		else 
		{	
			request.setAttribute("msggrammarguidelinecontent","Thêm nội dung không thành công");
			request.setAttribute("grammarguidelineid",grammarguidelineid);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertgrammarguidelinecontent.jsp");
			rd.forward(request,response);	
		}
			
	}

}
