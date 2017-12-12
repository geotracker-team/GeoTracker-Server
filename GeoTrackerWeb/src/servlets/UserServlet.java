package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class ProjectsServlet
 */
@WebServlet(description = "Manage User query, creation, modification and deletion", urlPatterns = {
		"/UserServlet" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doExecute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		doExecute(request, response);
	}

	protected void doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session;
		String strAction;
		String strResul;
		API api = new API();
		ArrayList<User> users;
		UserAPI usersAPI = new UserAPI();
		User user = null;
		int intCode = 0;
		String strCode, strName, strPassword;
 
		// Getting parameter information
		strAction = request.getParameter("action");
		strCode = request.getParameter("new_code");
		if ((strCode == null) || (strCode.equals("")))
			intCode = 0;
		else
			intCode = Integer.parseInt(strCode);
		
		strName = request.getParameter("nameRow"+ strCode);
		strPassword = request.getParameter("passRow"+ strCode);
		
		user = new User(intCode,strName,1,strPassword);
		
		//usersAPI = new UserAPI();
		
		// Process information
		strResul = "ok";
		switch (strAction) {
		case "add":
			try {
				usersAPI.createUser(user);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println("add: " + user.toString());
			break;
		case "save":
			usersAPI.updateUser(user);
			System.out.println("save:" + user.toString());			
			break;
		case "delete":
			try {
				usersAPI.deleteUser(user);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("delete: " + user.toString());
			break;
		}
		
		// projects = api.getAllProjects();

		// Return result
		
		session = request.getSession(true);
//		session.setAttribute("models.project", project);
		session.setAttribute("resul", strResul);
	//	session.setAttribute(errmessage, Incorrect user);
		// session.setAttribute("projects", projects);

		
		//	Redirect
		try {
			
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/users.jsp");
			rd.forward(request,  response);
			
		} catch(Exception e)	{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
