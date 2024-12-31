package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			/*
			 * Reading values from request
			 */
			String ename = request.getParameter("empName");
			String jobTitile = request.getParameter("jobTitle");
			int sal = Integer.parseInt(request.getParameter("salary"));

			/*
			 * Below variables are DB Variables
			 */
			String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=public";
	        String username = "postgres";
	        String password = "postgres";
	        try {
				Class.forName("org.postgresql.Driver");
			
	        Connection connection = DriverManager.getConnection(url, username, password);
	        
	        PreparedStatement r = connection.prepareStatement("insert into employee_table(ename,sal,job) values(?,?,?)");
	        
	        r.setString(1, ename);
	        r.setInt(2, sal);
	        r.setString(3, jobTitile);
	        
	         r.execute();
	         response.getWriter().print(ename +" Details are saved..");
	       
	        
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().print("Exception occur please check the logs");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().print("Exception occur please check the logs");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(e.getLocalizedMessage());
		}
	}

}
