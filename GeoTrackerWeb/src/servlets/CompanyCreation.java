package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.API;
import models.*;

/**
 * Servlet implementation class CompanyCreation
 */
@WebServlet("/GeoTrackerWeb")
public class CompanyCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyCreation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		API api = new API();
		try {
			Company com = api.getCompanyById(1);
			Project pro = api.getProjectById(1);
			User user = api.getUserById(1);
			Assigned assigned = api.getAssignedById(1);
			Register reg = api.getRegisterById(1);
			ExtraField extra = api.getExtrarById(1);
			ArrayList<Company> cl = api.getAllCompanies();
			ArrayList<Project> pl = api.getAllProjects(1);
			ArrayList<User> ul = api.getAllUsers(1);
			ArrayList<Assigned> al = api.getAllAssignationsByProject(1);
			ArrayList<Assigned> al2 = api.getAllAssignationsByUser(1);
			ArrayList<Register> rl = api.getAllRegistersByProject(1);
			ArrayList<Register> rl2 = api.getAllRegistersByUser(1);
			ArrayList<ExtraField> el = api.getAllExtByRegister(1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR retrieving");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
