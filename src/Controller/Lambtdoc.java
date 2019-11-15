package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Readexercise;
import BEAN.Readquestion;
import DAO.LambtphandocDAO;
import DAO.QuanlybtdocDAO;
import DB.DBConnection;


@WebServlet("/Lambtdoc")
public class Lambtdoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Lambtdoc() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try 
		{
			String readexeriseidstr = request.getParameter("readexeriseid");
			int readexeriseid = Integer.parseInt(readexeriseidstr);
			
			
			String pageidstr = request.getParameter("pageid");
			int pageid = Integer.parseInt(pageidstr);
			
			
			int count = 1;
			
			
			if (pageid == 1)
			{
				
			}
			else 
			{
				pageid = pageid -1;
				pageid = pageid * count +1;
			}
			
			
			Connection conn = DBConnection.CreateConnection();
			
			List<Readquestion> list = LambtphandocDAO.Hienthicauhoibtdoc(request, pageid, count, conn, readexeriseid);
			
			
			int sumrow = LambtphandocDAO.Demcauhoitheoma(conn, readexeriseid);
			
			int maxpageid= 0;
			
			if ((sumrow/count)%2==0)
			{
				maxpageid = (sumrow/count);
			}
			else
			{
				maxpageid = (sumrow/count)+1;
			}
			
			request.setAttribute("maxpageid",maxpageid);
			request.setAttribute("readexeriseid",readexeriseid);
			
			request.setAttribute("danhsachcauhoibtdoc",list);
			
			request.setAttribute("numberpage",Integer.parseInt(pageidstr));
			
			conn.close();
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglambtphandoc",e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Lambtphandoc.jsp");
		rd.forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		String kq = request.getParameter("kq");
		
		Connection conn = DBConnection.CreateConnection();
		
		String readexeriseidstr = request.getParameter("readexeriseid");
		int readexeriseid = Integer.parseInt(readexeriseidstr);
		
		String numstr = request.getParameter("num");
		int num = Integer.parseInt(numstr);
		
		
		if (kq == "")
		{
			request.setAttribute("msglambtphandoc","Yêu cầu trả lời hết các câu hỏi");
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Thongbaoloibtdoc.jsp");
			rd.forward(request,response);
		}
		else
		{	
			List<Readquestion> list = LambtphandocDAO.Xuatdapanbtdoc(request, conn, readexeriseid, num);
			
			request.setAttribute("dapandungbtdoc",list);
			request.setAttribute("kq",kq);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Ketquabtdoc.jsp");
			rd.forward(request,response);
		}
		
		
	}

}
