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

import BEAN.Vocabularycontent;
import DAO.HdhoctuvungDAO;
import DB.DBConnection;


@WebServlet("/Xemnoidungtuvung")
public class Xemnoidungtuvung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Xemnoidungtuvung() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String vocabularyguidelineidstr = request.getParameter("vocabularyguidelineid");
		
		int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidstr);
		
		Connection conn = DBConnection.CreateConnection();
		
		
		List<Vocabularycontent> list = HdhoctuvungDAO.Hienthinoidungtuvung(request, conn,vocabularyguidelineid);
		
		
		request.setAttribute("noidungtuvung",list);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Noidunghdtuvung.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
