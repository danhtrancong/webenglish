package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Answeruser;
import BEAN.Examinationquestion;
import BEAN.Result;
import DAO.LambaithiDAO;
import DB.DBConnection;


@WebServlet("/Lambaithitoeic")
public class Lambaithitoeic extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    
    public Lambaithitoeic() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		
		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		
		
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("sessionuser")!=null)
		{
			int memberid = (int) session.getAttribute("sessionmemberid");
			
			request.setAttribute("kitutrongdatabase","\n");
			request.setAttribute("kitutronghtml","<br/>");
			
			request.setAttribute("examinationid",examinationid);
			request.setAttribute("memberid",memberid);
			
			List<Examinationquestion> list = LambaithiDAO.Hienthicauhoidethi(conn, examinationid);
			
			request.setAttribute("dscauhoi",list);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Lambaithitoeic.jsp");
			rd.forward(request,response);
			
		}
		else
		{
			request.setAttribute("msgloidnlambai","Đăng nhập trước khi làm bài thi");
			
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsdethi?pageid=1");
			rd.forward(request,response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		Connection conn = DBConnection.CreateConnection();
		
		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		
		String memberidstr = request.getParameter("memberid");
		int memberid = Integer.parseInt(memberidstr);
		
		
		int countrow = LambaithiDAO.Demsocauhoidethi(conn, examinationid);
		
		List<Examinationquestion> listcorrectanswer = LambaithiDAO.Xuatdapandungdethi(conn, examinationid);
		
		List<Answeruser> listansweruser = new ArrayList<Answeruser>();
		
		
		int socaudung=0;
		int socaudungphannghe=0;
		int socaudungphandoc=0;
		for (int i =1; i<= countrow; i++)
		{
			String answer = request.getParameter("ans["+i+"]");
			
			if (answer != null)
			{
				//luu danh sach dap an cua user
				Answeruser au = new Answeruser();	
				au.setNum(i);
				au.setAnswer(answer);		
				listansweruser.add(au);
				
				String dapandung = LambaithiDAO.Dapancua1cauhoi(conn, examinationid,i);
				
				if (i<=4)
				{
					if (answer.equals(dapandung))
					{
						socaudung++;
						socaudungphannghe++;
					}
				}
				else 
				{
					if (i>=5)
					{
						if (answer.equals(dapandung))
						{
							socaudung++;
							socaudungphandoc++;
						}
					}
				}
					

					
			}
			else
			{
				Answeruser au = new Answeruser();	
				au.setNum(i);
				au.setAnswer("Không chọn");		
				listansweruser.add(au);
			}
				
			
		}
		
		
		Date date = new Date();
		
		
		int socausai = countrow - socaudung;
		
		
		Result result = new Result();
		
		result.setCorrectanswernum(socaudung);
		result.setIncorrectanswernum(socausai);
		result.setTime(date.toString());
		result.setExaminationid(examinationid);
		result.setMemberid(memberid);
		result.setCorrectanswerlisten(socaudungphannghe);
		result.setCorrectanswerread(socaudungphandoc);
		
		LambaithiDAO.Luuketquathi(conn, result);
		
		request.setAttribute("kitutrongdatabase","\n");
		request.setAttribute("kitutronghtml","<br/>");
		request.setAttribute("listcorrectanswer",listcorrectanswer);
		request.setAttribute("listansweruser",listansweruser);
		
		
		List<Result> list = LambaithiDAO.Xuatketquathi(conn,date.toString(), examinationid, memberid);
		
		request.setAttribute("ketquathi",list);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Ketquathi.jsp");
		rd.forward(request,response);
		
			
			
	}

}
