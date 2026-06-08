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

@WebServlet("/DashboardController")
public class StudentDashBoard extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        // SESSION CHECK
        if (session == null || session.getAttribute("studentId") == null) {

            response.sendRedirect("studentlogin.html");
            return;
        }

        // GET SESSION DATA
        String studentName =
                (String) session.getAttribute("studentName");

        Integer studentId =
                (Integer) session.getAttribute("studentId");

        // DASHBOARD VALUES
        int totalExams = 0;
        int attemptedExams = 0;
        double averageResult = 0;

        try {

            Connection conn =
                    dbConnection.getConnection();

            // TOTAL EXAMS
            PreparedStatement totalStmt =
                    conn.prepareStatement(
                            "select count(*) from exam");

            ResultSet totalRs =
                    totalStmt.executeQuery();

            if (totalRs.next()) {

                totalExams =
                        totalRs.getInt(1);
            }

            // ATTEMPTED EXAMS
            PreparedStatement attemptStmt =
                    conn.prepareStatement(
                            "select count(*) from result where student_id=?");

            attemptStmt.setInt(1, studentId);

            ResultSet attemptRs =
                    attemptStmt.executeQuery();

            if (attemptRs.next()) {

                attemptedExams =
                        attemptRs.getInt(1);
            }

            // AVERAGE RESULT
            PreparedStatement avgStmt =
                    conn.prepareStatement(
                            "select avg(percentage) from result where student_id=?");

            avgStmt.setInt(1, studentId);

            ResultSet avgRs =
                    avgStmt.executeQuery();

            if (avgRs.next()) {

                averageResult =
                        avgRs.getDouble(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        PrintWriter out =
                response.getWriter();

        out.println("""
<!DOCTYPE html>
<html lang='en'>

<head>

<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>

<title>Student Dashboard</title>

<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>

<link rel='stylesheet'
href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:'Poppins',sans-serif;
}

body{
background:#f8fafc;
color:#111827;
}

.topbar{
height:75px;
background:white;
display:flex;
justify-content:space-between;
align-items:center;
padding:0 30px;
box-shadow:0 4px 15px rgba(0,0,0,0.06);
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
font-size:17px;
font-weight:600;
}

.wrapper{
display:flex;
min-height:calc(100vh - 75px);
}

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
font-size:70px;
color:#2563eb;
margin-bottom:10px;
}

.profile h4{
font-size:24px;
font-weight:700;
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

.content{
flex:1;
padding:35px;
}

.content h1{
font-size:38px;
font-weight:700;
margin-bottom:10px;
}

.content p{
color:#6b7280;
font-size:17px;
margin-bottom:30px;
}

.dashboard-card{
background:white;
padding:25px;
border-radius:20px;
box-shadow:0 10px 25px rgba(0,0,0,0.05);
border:1px solid #f1f5f9;
text-align:center;
transition:0.3s;
}

.dashboard-card:hover{
transform:translateY(-5px);
}

.dashboard-card i{
font-size:45px;
margin-bottom:15px;
}

.card1 i{
color:#2563eb;
}

.card2 i{
color:#10b981;
}

.card3 i{
color:#f59e0b;
}

.dashboard-card h2{
font-size:35px;
font-weight:700;
}

.dashboard-card p{
margin-top:10px;
font-size:16px;
color:#6b7280;
}

.exam-table{
margin-top:30px;
background:white;
padding:25px;
border-radius:20px;
box-shadow:0 10px 25px rgba(0,0,0,0.05);
border:1px solid #f1f5f9;
}

.exam-table h3{
margin-bottom:20px;
font-weight:700;
color:#2563eb;
}

table{
width:100%;
}

table th{
background:#eff6ff;
padding:14px;
text-align:center;
}

table td{
padding:14px;
text-align:center;
border-bottom:1px solid #e5e7eb;
}

.note-box{
margin-top:30px;
background:#fff7ed;
padding:25px;
border-radius:18px;
border-left:6px solid #f59e0b;
}

.note-box h5{
font-weight:700;
margin-bottom:15px;
}

footer{
margin-top:30px;
text-align:center;
padding:20px;
color:#6b7280;
font-size:15px;
}

</style>

</head>

<body>

<!-- TOPBAR -->

<div class='topbar'>

<div class='brand'>
<i class='fa-solid fa-laptop-file'></i>
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

<a href='DashboardController' class='active'>
<i class='fa-solid fa-house'></i>
Dashboard
</a>

<a href='ExamListController'>
<i class='fa-solid fa-book'></i>
Exams
</a>

<a href='ResultController'>
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

<h1>Student Dashboard</h1>

<p>Welcome to Online Examination System</p>

<div class='row g-4'>

<!-- TOTAL EXAMS -->

<div class='col-md-4'>

<div class='dashboard-card card1'>

<i class='fa-solid fa-book-open'></i>

<h2>""" + totalExams + """
</h2>

<p>Total Exams</p>

</div>

</div>

<!-- ATTEMPTED EXAMS -->

<div class='col-md-4'>

<div class='dashboard-card card2'>

<i class='fa-solid fa-clipboard-check'></i>

<h2>""" + attemptedExams + """
</h2>

<p>Attempted Exams</p>

</div>

</div>

<!-- AVERAGE RESULT -->

<div class='col-md-4'>

<div class='dashboard-card card3'>

<i class='fa-solid fa-trophy'></i>

<h2>""" + String.format("%.0f", averageResult) + """
		
		%</h2>

<p>Average Result</p>

</div>

</div>

</div>

<!-- EXAM TABLE -->

<div class='exam-table'>

<h3>
<i class='fa-solid fa-file-lines'></i>
Available Exams
</h3>

<table class='table table-hover'>

<tr>

<th>ID</th>
<th>Exam Name</th>
<th>Total Marks</th>
<th>Duration</th>


</tr>
""");

        try {

            Connection conn =
                    dbConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            "select * from exam");

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                int examId =
                        rs.getInt("exam_id");

                out.println("<tr>");

                out.println("<td>"
                        + examId +
                        "</td>");

                out.println("<td>"
                        + rs.getString("exam_name")
                        + "</td>");

                out.println("<td>"
                        + rs.getInt("total_marks")
                        + "</td>");

                out.println("<td>"
                        + rs.getInt("exam_duration")
                        + " Min</td>");

                out.println("<td>");

               

       

                out.println("</button>");

                out.println("</a>");

                out.println("</td>");

                out.println("</tr>");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        out.println("""
</table>

</div>

<!-- NOTE BOX -->

<div class='note-box'>

<h5>
<i class='fa-solid fa-circle-info'></i>
Important Instructions
</h5>

<ul>

<li>Read all questions carefully.</li>

<li>Do not refresh page during exam.</li>

<li>Click submit after completing exam.</li>

<li>Result will display immediately.</li>

</ul>

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