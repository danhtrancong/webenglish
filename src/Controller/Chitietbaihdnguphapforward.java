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

import BEAN.Cmtgrammar;
import DAO.BaihdnguphapDAO;
import DAO.CommentgrammarDAO;
import DB.DBConnection;


@WebServlet("/Chitietbaihdnguphapforward")
public class Chitietbaihdnguphapforward extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public Chitietbaihdnguphapforward() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String mabaihdnguphapstr = request.getParameter("mabaihdnguphap");
		
		int mabaihdnguphap = Integer.parseInt(mabaihdnguphapstr);
		
		Connection conn = DBConnection.CreateConnection();
		
		String content = BaihdnguphapDAO.Displaygrammarcontent(conn, mabaihdnguphap);
		
		//xuat so binh luan cua bai viet
		int countrow = CommentgrammarDAO.Countrow(conn, mabaihdnguphap);
		
		request.setAttribute("mabaihdnguphap",mabaihdnguphap);
		request.setAttribute("grammarguidelinecontent",content);
		request.setAttribute("kitutrongdatabase","\n");
		request.setAttribute("kitutronghtml","<br/>");
		request.setAttribute("countrow",countrow);
		
		
		List<Cmtgrammar> list = BaihdnguphapDAO.Displaycmtgrammar(conn, mabaihdnguphap);
		
		request.setAttribute("listcommentgrammar",list);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Chitietbaihdnguphap.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
