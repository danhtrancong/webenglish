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

import BEAN.Listenquestion;
import BEAN.Readquestion;
import DAO.LambtngheDAO;
import DAO.LambtphandocDAO;
import DB.DBConnection;


@WebServlet("/Lambtnghe")
public class Lambtnghe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Lambtnghe() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try 
		{
			String listenexerciseidstr = request.getParameter("listenexerciseid");
			int listenexerciseid = Integer.parseInt(listenexerciseidstr);
			
			
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
			
			List<Listenquestion> list = LambtngheDAO.Hienthicauhoibtnghe(request, pageid, count, conn, listenexerciseid);
			
			
			int sumrow = LambtngheDAO.Demcauhoitheoma(conn, listenexerciseid);
			
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
			request.setAttribute("listenexerciseid",listenexerciseid);
			
			request.setAttribute("danhsachcauhoibtnghe",list);
			
			request.setAttribute("numberpage",Integer.parseInt(pageidstr));
			
			conn.close();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Lambtphannghe.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String kq = request.getParameter("kq");
		
		Connection conn = DBConnection.CreateConnection();
		
		String listenexerciseidstr = request.getParameter("listenexerciseid");
		int listenexerciseid = Integer.parseInt(listenexerciseidstr);
		
		String numstr = request.getParameter("num");
		int num = Integer.parseInt(numstr);
		
		
		if (kq == "")
		{
			request.setAttribute("msglambtphannghe","Yêu cầu trả lời hết các câu hỏi");
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Thongbaoloibtnghe.jsp");
			rd.forward(request,response);
		}
		else
		{	
			List<Listenquestion> list = LambtngheDAO.Xuatdapanbtnghe(request, conn, listenexerciseid, num);
			
			request.setAttribute("dapandungbtnghe",list);
			request.setAttribute("kq",kq);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Ketquabtnghe.jsp");
			rd.forward(request,response);
		}
		
	}

}
