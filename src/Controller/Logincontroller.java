package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Member;
import DAO.LoginDAO;
import DB.DBConnection;

import java.sql.*;

@WebServlet("/Logincontroller")
public class Logincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Logincontroller() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		String membername = request.getParameter("membername");
		String memberpass = request.getParameter("memberpass");
		Member member = new Member();
		member.setMembername(membername);
		member.setMemberpass(memberpass);
		
		String name = LoginDAO.Exportnamemember(request, conn, member);
		int memberid = LoginDAO.Exportmemberid(request, conn, member);
		
		String authentication = LoginDAO.Authenticationmember(request, conn, member);
		if (authentication.equals("success"))
		{
			int authorization = LoginDAO.Authorizationmember(request, conn, member);
			if (authorization==1)
			{
				 
				HttpSession session = request.getSession(true);
				
				session.setAttribute("sessionuser",name);
				session.setAttribute("sessionmemberid",memberid);
				
				RequestDispatcher rd = request.getRequestDispatcher("Homeforward");
				rd.forward(request,response);
			}
			else
			{
				if (authorization==2)
				{
					HttpSession session = request.getSession(true);
					
					session.setAttribute("sessionadmin",name);
					
					RequestDispatcher rd = request.getRequestDispatcher("Adminforward");
					rd.forward(request,response);
				}
				
			}
		}
		else 
		{
			if (authentication.equals("fail"))
			{
				request.setAttribute("msglogin","Tên hoặc mật khẩu sai");
				RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
				rd.forward(request,response);
			}
			
		}
	}

}
