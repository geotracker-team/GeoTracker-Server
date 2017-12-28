package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import api.API;
import models.Assigned;
import models.Project;

/**
 * Servlet implementation class AssignedServlet
 */
@WebServlet("/AssignedServlet")
public class AssignedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doExecute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doExecute(request, response);
	}

	private void doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session;
		String strAction;
		String strResul;
		String strCode, strProject, strUser;
		int intCode, intProject, intUser;
		API api;
		Assigned assigned;
		
		// Getting parameter information
		strAction = request.getParameter("action");
		strCode = request.getParameter("code");
		if ((strCode == null) || (strCode.equals("")))
			intCode = 0;
		else
			intCode = Integer.parseInt(strCode);
		strProject = request.getParameter("project");
		if ((strProject == null) || (strProject.equals("")))
			intProject = 0;
		else
			intProject = Integer.parseInt(strProject);		
		strUser = request.getParameter("user");
		if ((strUser == null) || (strUser.equals("")))
			intUser = 0;
		else
			intUser = Integer.parseInt(strUser);
		
		api = new API();
		assigned = new Assigned();
		assigned.setId(intCode);
		assigned.setIdProject(intProject);
		assigned.setIdUser(intUser);
		
		// Process information
		strResul = "ok";
		switch (strAction) {
		case "add":
			api.createAssignation(assigned);
			System.out.println("Assigned: add: " + assigned.getIdProject() + " - " + assigned.getIdUser());
			break;
		case "delete":
			api.deleteAssignation(assigned);
			System.out.println("Assigned: delete: " + assigned.getIdProject() + " - " + assigned.getIdUser());
			break;
		}				

		// Return result		
		session = request.getSession(true);
		session.setAttribute("resul", strResul);
		
		//	Redirect
		try {
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/assigned.jsp?id=" + assigned.getIdProject());
			rd.forward(request,  response);
			
		} catch(Exception e)	{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
				
	}
}
