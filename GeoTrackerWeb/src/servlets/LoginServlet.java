package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import api.API;
import api.UserAPI;
import models.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doExecute(request,response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		doExecute(request,response);
	}
	
	private void doExecute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session;
		String strResul = null;
		UserAPI api = new UserAPI();
		User user = null;
		User user2 = null;
		String strUser = null;
		String strPass = null;
		String strMessage = null;
		strUser = request.getParameter("uname");
		strPass = request.getParameter("psw");
		
		user = new User();
		user.setName(strUser);
		user.setPassword(strPass);
		
		
		try {
			user2 = api.getUserByName(strUser);
			if(user == null)
			{
				strResul = "error";
				strMessage = "User did not found in database!";
			}
			else
			{
				if(strPass.equals(user2.getPassword()))
				{
					strResul = "ok";
				}
				else
				{
					strResul = "error";
					strMessage = "Your password is not correct!";
				}
			}
			//compare name name psw psw 
			//user.getName()
			
			
		//set strResul to ok or error	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		session = request.getSession(true);
		session.setAttribute("username", strUser);
		session.setAttribute("password", strPass);
		session.setAttribute("resul",strResul);
		session.setAttribute("error",strMessage);
		
		
		RequestDispatcher rd = null;
			
			try
			{
				ServletContext context = getServletContext();
				if(strResul.equals("ok"))
				{
					rd = context.getRequestDispatcher("/default.jsp");
				}
				else
				{
					rd = context.getRequestDispatcher("/login.jsp");
				}
				rd.forward(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		//error message
	}
}
