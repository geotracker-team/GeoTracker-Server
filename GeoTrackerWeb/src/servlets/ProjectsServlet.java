package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import api.API;
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
		API api = new API();
		ArrayList<Project> projects;
		Project project = null;
		int intCode;
		String strName;
		int intCompany;

		// Getting parameter information
		strAction = request.getParameter("action");
		intCode = Integer.parseInt(request.getParameter("code"));
		strName = request.getParameter("name");
		intCompany = Integer.parseInt(request.getParameter("code"));
		project = new Project(intCode, strName, intCompany);

		// Process information
		strResul = "ok";
		switch (strAction) {
		case "load":
			System.out.println("load");
			break;
		case "add":
//			apiProject.createProject(project);
			System.out.println("add");
			break;
		case "save":
//			apiProject.updateProject(project);
			System.out.println("save");			
			break;
		case "delete":
//			apiProject.deleteProject(project);
			System.out.println("delete");
			break;
		}
		
		// projects = api.getAllProjects();

		// Return result
		
		session = request.getSession(true);
		session.setAttribute("models.project", project);
		session.setAttribute("resul", strResul);
		session.setAttribute(errmessage, Incorrect user);
		// session.setAttribute("projects", projects);

	}

}
