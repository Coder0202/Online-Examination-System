package org.OnlineExamSystem.Controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/studentlogin")
public class StudentLoginController extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out =
				response.getWriter();

		String email =
				request.getParameter("email");

		String password =
				request.getParameter("password");

		try {

			Class.forName(
			"com.mysql.cj.jdbc.Driver");

			Connection conn =
			DriverManager.getConnection(

			"jdbc:mysql://localhost:3306/onlineexamsystem",
			"root",
			"Avi@1234"

			);

			PreparedStatement stmt =
			conn.prepareStatement(

			"select * from students where email=? and password=?"

			);

			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs =
			stmt.executeQuery();

			// LOGIN SUCCESS

			if(rs.next())
			{
				// CREATE SESSION

				HttpSession session =
				request.getSession();

				// STORE DATA IN SESSION

				session.setAttribute(
				"studentId",
				rs.getInt("student_id"));

				session.setAttribute(
				"studentName",
				rs.getString("student_name"));

				session.setAttribute(
				"studentEmail",
				rs.getString("email"));

				// REDIRECT TO DASHBOARD

				response.sendRedirect(
				"DashboardController");
			}
			else
			{
				out.println("<h2>Login Failed</h2>");
			}

		}
		catch(Exception e)
		{
			out.println(e);
		}
	}
}