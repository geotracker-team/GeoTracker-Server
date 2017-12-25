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
import api.ProjectsAPI;
import models.Project;

/**
 * Servlet implementation class ProjectsServlet
 */
@WebServlet(description = "Manage Project query, creation, modification and deletion", urlPatterns = {
		"/ProjectsServlet" })
public class ProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectsServlet() {
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
		ProjectsAPI projectsAPI;
		Project project = null;
		int intCode = 0, intCompany;
		String strCode, strName, strCompany;
 
		// Getting parameter information
		strAction = request.getParameter("action");
		strCode = request.getParameter("code");
		strName = request.getParameter("name");
		strCompany = request.getParameter("company");
		if ((strCode == null) || (strCode.equals("")))
			intCode = 0;
		else
			intCode = Integer.parseInt(strCode);

		if (strCompany != null)
			intCompany = Integer.parseInt(strCompany);
		else
			intCompany = 0;			
		
		project = new Project(intCode, strName, intCompany);
		
		projectsAPI = new ProjectsAPI();
		
		// Process information
		strResul = "ok";
		switch (strAction) {
		case "add":
			projectsAPI.createProject(project);
			System.out.println("Projects: add: " + project.getName());
			break;
		case "save":
			projectsAPI.updateProject(project);
			System.out.println("Projects: save:" + project.getName());			
			break;
		case "delete":
			projectsAPI.deleteProject(project);
			System.out.println("Projects: delete: " + project.getName());
			break;
		}
		
		// projects = api.getAllProjects();

		// Return result
		
		session = request.getSession(true);
		session.setAttribute("resul", strResul);

		
		//	Redirect
		try {
			
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/projects.jsp");
			rd.forward(request,  response);
			
		} catch(Exception e)	{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
