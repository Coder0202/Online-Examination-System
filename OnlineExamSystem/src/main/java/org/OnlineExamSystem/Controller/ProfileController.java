package org.OnlineExamSystem.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.OnlineExamSystem.repositiory.dbConnection;

@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {

            // SESSION CHECK

            HttpSession session = request.getSession(false);

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

            Connection conn =
                    dbConnection.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(
                            "select * from students where student_id=?");

            stmt.setInt(1, studentId);

            ResultSet rs =
                    stmt.executeQuery();

            out.println("""
<!DOCTYPE html>
<html lang='en'>

<head>

<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>

<title>Student Profile</title>

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
color:#111827;
overflow-x:hidden;
}

/* TOPBAR */

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
}

.profile p{
font-size:14px;
color:#6b7280;
margin-top:5px;
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

/* PROFILE CARD */

.profile-card{
background:white;
padding:35px;
border-radius:22px;
box-shadow:0 10px 25px rgba(0,0,0,0.05);
border:1px solid #f1f5f9;
}

.profile-header{
display:flex;
align-items:center;
gap:25px;
margin-bottom:35px;
padding-bottom:25px;
border-bottom:1px solid #e5e7eb;
}

.profile-header i{
font-size:90px;
color:#2563eb;
}

.profile-header h2{
font-size:32px;
font-weight:700;
margin-bottom:5px;
}

.profile-header span{
color:#6b7280;
font-size:16px;
}

/* INFO BOX */

.info-grid{
display:grid;
grid-template-columns:repeat(auto-fit,minmax(280px,1fr));
gap:25px;
}

.info-box{
background:#f8fafc;
padding:25px;
border-radius:18px;
border:1px solid #e5e7eb;
transition:0.3s;
}

.info-box:hover{
transform:translateY(-5px);
box-shadow:0 10px 20px rgba(0,0,0,0.05);
}

.info-box i{
font-size:28px;
color:#2563eb;
margin-bottom:15px;
}

.info-box h5{
font-size:16px;
color:#6b7280;
margin-bottom:8px;
}

.info-box h3{
font-size:24px;
font-weight:700;
color:#111827;
}

/* FOOTER */

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
<i class='fa-solid fa-graduation-cap'></i>
Online Exam System
</div>

<div class='student-user'>
<i class='fa-solid fa-circle-user'></i>
Welcome,
""");

            out.println(studentName);

            out.println("""
</div>

</div>

<div class='wrapper'>

<!-- SIDEBAR -->

<div class='sidebar'>

<div class='profile'>

<i class='fa-solid fa-user-graduate'></i>

<h4>
""");

            out.println(studentName);

            out.println("""
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

<a href='ResultController'>
<i class='fa-solid fa-chart-column'></i>
Results
</a>

<a href='ProfileController' class='active'>
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

<h1>Student Profile</h1>

<p>Manage and view your profile details.</p>

<div class='profile-card'>
""");

            if (rs.next()) {

                out.println("""
<div class='profile-header'>

<i class='fa-solid fa-user-circle'></i>

<div>
<h2>
""");

                out.println(rs.getString("student_name"));

                out.println("""
</h2>

<span>Online Examination Student</span>

</div>

</div>

<div class='info-grid'>

<div class='info-box'>
<i class='fa-solid fa-id-card'></i>
<h5>Student ID</h5>
<h3>
""");

                out.println(rs.getInt("student_id"));

                out.println("""
</h3>
</div>

<div class='info-box'>
<i class='fa-solid fa-user'></i>
<h5>Full Name</h5>
<h3>
""");

                out.println(rs.getString("student_name"));

                out.println("""
</h3>
</div>

<div class='info-box'>
<i class='fa-solid fa-envelope'></i>
<h5>Email Address</h5>
<h3>
""");

                out.println(rs.getString("email"));

                out.println("""
</h3>
</div>

<div class='info-box'>
<i class='fa-solid fa-book-open'></i>
<h5>Course</h5>
<h3>
""");

                out.println(rs.getString("course"));

                out.println("""
</h3>
</div>

<div class='info-box'>
<i class='fa-solid fa-phone'></i>
<h5>Mobile Number</h5>
<h3>
""");

                out.println(rs.getString("mobile"));

                out.println("""
</h3>
</div>

</div>
""");
            }

            out.println("""
</div>

<footer>
© 2026 Online Exam System | Designed with ❤️
</footer>

</div>

</div>

</body>
</html>
""");

        } catch (Exception e) {

            out.println("<h2 style='color:red;text-align:center;margin-top:50px;'>");
            out.println(e.getMessage());
            out.println("</h2>");
        }
    }
}