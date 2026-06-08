package org.OnlineExamSystem.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.OnlineExamSystem.repositiory.dbConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ResultController")
public class StudentResultController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		// SESSION CHECK

		HttpSession session =
				request.getSession(false);

		if (session == null) {
			response.sendRedirect("studentlogin.html");
			return;
		}

		String studentName =
				(String) session.getAttribute("studentName");

		Integer studentId =
				(Integer) session.getAttribute("studentId");

		if (studentId == null) {
			response.sendRedirect("studentlogin.html");
			return;
		}

		out.println("""
<!DOCTYPE html>
<html lang='en'>

<head>

<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>

<title>My Results</title>

<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>

<link rel='stylesheet'
href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css'>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:'Poppins',sans-serif;
}

body{
background:#f8fafc;
overflow-x:hidden;
color:#111827;
}

/* TOPBAR */

.topbar{
height:75px;
background:white;
display:flex;
justify-content:space-between;
align-items:center;
padding:0 35px;
box-shadow:0 4px 18px rgba(0,0,0,0.06);
border-bottom:1px solid #e5e7eb;
}

.brand{
display:flex;
align-items:center;
gap:12px;
font-size:28px;
font-weight:700;
color:#2563eb;
}

.student-user{
font-size:16px;
font-weight:600;
color:#374151;
}

/* WRAPPER */

.wrapper{
display:flex;
min-height:calc(100vh - 75px);
}

/* SIDEBAR */

.sidebar{
width:280px;
background:white;
padding:30px 20px;
border-right:1px solid #e5e7eb;
}

.profile{
text-align:center;
margin-bottom:35px;
}

.profile i{
font-size:75px;
color:#2563eb;
margin-bottom:15px;
}

.profile h4{
font-size:24px;
font-weight:700;
margin-bottom:5px;
}

.profile p{
font-size:14px;
color:#6b7280;
}

.menu a{
display:flex;
align-items:center;
gap:14px;
padding:14px 18px;
margin-bottom:12px;
border-radius:14px;
text-decoration:none;
color:#374151;
font-size:16px;
font-weight:500;
transition:0.3s;
}

.menu a:hover,
.menu a.active{
background:#eff6ff;
color:#2563eb;
transform:translateX(5px);
}

/* CONTENT */

.content{
flex:1;
padding:35px;
}

.page-title{
font-size:38px;
font-weight:700;
margin-bottom:10px;
}

.page-subtitle{
font-size:17px;
color:#6b7280;
margin-bottom:30px;
}

/* RESULT CARD */

.result-card{
background:white;
padding:30px;
border-radius:22px;
box-shadow:0 10px 30px rgba(0,0,0,0.06);
border:1px solid #f1f5f9;
}

/* TABLE */

table{
width:100%;
border-collapse:collapse;
}

table th{
background:#eff6ff;
padding:16px;
text-align:center;
font-weight:700;
color:#2563eb;
}

table td{
padding:16px;
text-align:center;
vertical-align:middle;
border-bottom:1px solid #e5e7eb;
}

.result-row:hover{
background:#f8fafc;
transition:0.3s;
}

/* STATUS */

.pass-badge{
background:#dcfce7;
color:#166534;
padding:8px 14px;
border-radius:20px;
font-weight:700;
font-size:14px;
}

.fail-badge{
background:#fee2e2;
color:#991b1b;
padding:8px 14px;
border-radius:20px;
font-weight:700;
font-size:14px;
}

/* FOOTER */

footer{
margin-top:30px;
text-align:center;
padding:20px;
font-size:15px;
color:#6b7280;
}

</style>

</head>

<body>

<!-- TOPBAR -->

<div class='topbar'>

<div class='brand'>
<i class='fa-solid fa-graduation-cap'></i>
Online Exam System
</div>

<div class='student-user'>
<i class='fa-solid fa-circle-user'></i>
Welcome, """ + studentName + """
</div>

</div>

<div class='wrapper'>

<!-- SIDEBAR -->

<div class='sidebar'>

<div class='profile'>

<i class='fa-solid fa-user-graduate'></i>

<h4>""" + studentName + """
		
		</h4>

<p>Student Panel</p>

</div>

<div class='menu'>

<a href='DashboardController'>
<i class='fa-solid fa-house'></i>
Dashboard
</a>

<a href='ExamListController'>
<i class='fa-solid fa-book'></i>
Exams
</a>

<a href='ResultController' class='active'>
<i class='fa-solid fa-chart-column'></i>
Results
</a>

<a href='ProfileController'>
<i class='fa-solid fa-user'></i>
Profile
</a>

<a href='LogoutController'>
<i class='fa-solid fa-right-from-bracket'></i>
Logout
</a>

</div>

</div>

<!-- CONTENT -->

<div class='content'>

<h1 class='page-title'>My Results</h1>

<p class='page-subtitle'>
View all your exam performance and scores.
</p>

<div class='result-card'>

<table class='table table-hover'>

<tr>

<th>Result ID</th>
<th>Exam ID</th>
<th>Total Marks</th>
<th>Obtained Marks</th>
<th>Percentage</th>
<th>Status</th>

</tr>
""");

		try {

			Connection conn =
					dbConnection.getConnection();

			PreparedStatement ps =
					conn.prepareStatement(
					"select * from result where student_id=?");

			ps.setInt(1, studentId);

			ResultSet rs =
					ps.executeQuery();

			boolean found = false;

			while (rs.next()) {

				found = true;

				double percentage =
						rs.getDouble("percentage");

				String status =
						rs.getString("status");

				out.println("<tr class='result-row'>");

				out.println("<td>");
				out.println(rs.getInt("result_id"));
				out.println("</td>");

				out.println("<td>");
				out.println(rs.getInt("exam_id"));
				out.println("</td>");

				out.println("<td>");
				out.println(rs.getInt("total_marks"));
				out.println("</td>");

				out.println("<td>");
				out.println(rs.getInt("obtained_marks"));
				out.println("</td>");

				out.println("<td>");
				out.println(String.format("%.2f", percentage) + "%");
				out.println("</td>");

				out.println("<td>");

				if (status.equalsIgnoreCase("PASS")) {

					out.println("<span class='pass-badge'>");
					out.println("PASS");
					out.println("</span>");

				} else {

					out.println("<span class='fail-badge'>");
					out.println("FAIL");
					out.println("</span>");
				}

				out.println("</td>");

				out.println("</tr>");
			}

			if (found == false) {

				out.println("""
<tr>
<td colspan='6'
style='color:red;
font-weight:bold;
text-align:center;
padding:20px;'>
No Results Found
</td>
</tr>
""");
			}

		} catch (Exception e) {

			out.println("<tr>");
			out.println("<td colspan='6' class='text-danger text-center'>");
			out.println(e.getMessage());
			out.println("</td>");
			out.println("</tr>");
		}

		out.println("""
</table>

</div>

<footer>

© 2026 Online Exam System | Designed with ❤️

</footer>

</div>

</div>

</body>
</html>
""");
	}
}