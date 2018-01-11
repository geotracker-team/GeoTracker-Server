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

import org.jboss.resteasy.spi.HttpRequest;

import api.API;
import api.UserAPI;
import models.Record;
import models.User;

/**
 * Servlet implementation class ProjectsServlet
 */
@WebServlet(description = "Manage User query, creation, modification and deletion", urlPatterns = {
		"/UserServlet" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String errorMessage;
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
		UserAPI usersAPI;
		User user = null;
		int intCode = 0, intCompany;
		String strCode, strName, strCompany, strPassword, strBool;
		boolean isManager;
 
		// Getting parameter information
		strAction = request.getParameter("action");
		strCode = request.getParameter("code");
		strName = request.getParameter("name");
		strCompany = request.getParameter("company");
		strPassword = request.getParameter("pass");
		strBool = request.getParameter("bool");
		
		if(strBool == null)
		{
			isManager = false;
		}
		else
		{
			isManager = true;
		}
		
		if ((strCode == null) || (strCode.equals("")))
			intCode = 0;
		else
			intCode = Integer.parseInt(strCode);

		if (strCompany != null)
			intCompany = Integer.parseInt(strCompany);
		else
			intCompany = 0;			
		
		user = new User(intCode, strName, intCompany, strPassword, isManager);
		
		usersAPI = new UserAPI();
		
		// Process information
		strResul = "ok";
		switch (strAction) {
		case "add":
			usersAPI.createUser(user);
			System.out.println("add: " + user.toString());
			break;
		case "save":
			usersAPI.updateUser(user);
			System.out.println("save:" + user.toString());			
			break;
		case "delete":
			if (validateDelete(user.getId())) {
				usersAPI.deleteUser(user);
				System.out.println("delete: " + user.toString());
			}	else {
				strResul = "error";
			}
			break;
		}
		
		
		session = request.getSession(true);
		session.setAttribute("resul", strResul);
		if (strResul.equals("error")) {
			session.setAttribute("error", errorMessage);
		}

		
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
	
	private boolean validateDelete(int idUser) {
		boolean validate = true;
		API api = new API();
		ArrayList<Record> records;
		
		try {
			records = api.getAllRegistersByUser(idUser);
			if (!records.isEmpty()) {
				validate = false;
				errorMessage = "User can't be deleted because it has record associated.";
			}
			return validate;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
