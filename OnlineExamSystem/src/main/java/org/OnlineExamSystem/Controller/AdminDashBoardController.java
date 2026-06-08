package org.OnlineExamSystem.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.AdminModel;
import org.OnlineExamSystem.service.AdminService;
import org.OnlineExamSystem.service.AdminServiceImpl;

@WebServlet("/admindashboard")
public class AdminDashBoardController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("AdminLogin");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String adminEmail = session.getAttribute("admin").toString();

        AdminService service = new AdminServiceImpl();

        Optional<List<AdminModel>> ex = service.getAllExam();

        int totalExam = 0;
        if (ex.isPresent()) {
            totalExam = ex.get().size();
        }

        AdminModel m1 = new AdminModel();
        String AdminName=service.getAdminName(m1);
        int totalStudent = service.isTotalStudent(m1);
        int totalSubject = service.isTotalSubject(m1);
        double passPercentage = service.getPassPercentage(m1);
        out.print("""
<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Admin Dashboard</title>

<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>
<link rel='stylesheet'
href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'/>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:'Poppins',sans-serif;
}

body{
background:#ffffff;
color:#111827;
}

/* Navbar */

.topbar{
height:75px;
background:#ffffff;
display:flex;
justify-content:space-between;
align-items:center;
padding:0 35px;
box-shadow:0 4px 15px rgba(0,0,0,0.08);
}
.brand{
display:flex;
align-items:center;
gap:12px;
font-size:28px;
font-weight:700;
color:#2563eb;
}
.admin-user{
font-size:17px;
font-weight:600;
}

/* Layout */

.wrapper{
display:flex;
min-height:calc(100vh - 75px);
}

/* Sidebar */

.sidebar{
width:280px;
background:#ffffff;
padding:30px 20px;
box-shadow:4px 0 15px rgba(0,0,0,0.05);
}

.profile{
text-align:center;
margin-bottom:35px;
}

.profile i{
font-size:70px;
color:#2563eb;
margin-bottom:12px;
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

/* Main */

.content{
flex:1;
padding:40px;
}

.content h1{
font-size:42px;
font-weight:700;
margin-bottom:10px;
}

.content p{
font-size:17px;
color:#6b7280;
margin-bottom:35px;
}

/* Stats */

.stats{
display:grid;
grid-template-columns:repeat(auto-fit,minmax(240px,1fr));
gap:25px;
margin-bottom:35px;
}

.card-box{
background:#ffffff;
padding:25px;
border-radius:22px;
box-shadow:0 12px 30px rgba(0,0,0,0.08);
transition:0.3s;
}

.card-box:hover{
transform:translateY(-8px);
box-shadow:0 18px 40px rgba(0,0,0,0.12);
}

.icon{
width:65px;
height:65px;
border-radius:18px;
display:flex;
align-items:center;
justify-content:center;
font-size:28px;
color:white;
margin-bottom:18px;
}

.blue{background:linear-gradient(45deg,#2563eb,#3b82f6);}
.green{background:linear-gradient(45deg,#10b981,#34d399);}
.orange{background:linear-gradient(45deg,#f97316,#fb923c);}
.red{background:linear-gradient(45deg,#ef4444,#f87171);}

.card-box h2{
font-size:34px;
font-weight:700;
margin-bottom:6px;
}

.card-box span{
color:#6b7280;
font-size:15px;
}

/* Table */

.table-box{
background:#ffffff;
padding:30px;
border-radius:25px;
box-shadow:0 12px 30px rgba(0,0,0,0.08);
}

.table-box h3{
font-size:28px;
font-weight:700;
color:#2563eb;
margin-bottom:20px;
}

table th{
background:#eff6ff;
padding:14px;
text-align:center;
}

table td{
padding:14px;
text-align:center;
vertical-align:middle;
}

/* Footer */

footer{
margin-top:35px;
text-align:center;
color:#6b7280;
font-size:15px;
}

</style>
</head>

<body>

<!-- Topbar -->

<div class='topbar'>

<div class='brand'>
<i  class='fa-solid fa-graduation-cap'></i>
Online Exam System
</div>

<div class='admin-user'>
<i class='fa-solid fa-circle-user'></i>
Welcome, """ + adminEmail + """
</div>

</div>

<div class='wrapper'>

<!-- Sidebar -->

<div class='sidebar'>

<div class='profile'>
<i class='fa-solid fa-user-shield'></i>
<h4>"""+AdminName+"""
		
		</h4>
<p>Everything, Made Simple</p>
</div>

<div class='menu'>

<a href='admindashboard' class='active'>
<i class='fa-solid fa-house'></i> Dashboard
</a>

<a href='subject'>
<i class='fa-solid fa-book'></i> Subjects
</a>

<a href='exam'>
<i class='fa-solid fa-file-lines'></i> Exams
</a>

<a href='questions'>
<i class='fa-solid fa-circle-question'></i> Questions
</a>

<a href='students'>
<i class='fa-solid fa-users'></i> Students
</a>

<a href='results'>
<i class='fa-solid fa-chart-column'></i> Results
</a>

<a href='Home.html'>
<i class='fa-solid fa-right-from-bracket'></i> Logout
</a>

</div>
</div>

<!-- Content -->

<div class='content'>

<h1>Welcome
		
				""" + AdminName + """
		
		</h1>
<p>Manage exams, students, subjects and results efficiently.</p>

<div class='stats'>

<div class='card-box'>
<div class='icon blue'><i class='fa-solid fa-book'></i></div>
<h2>""" + totalSubject + """
		
		</h2>
<span>Total Subjects</span>
</div>

<div class='card-box'>
<div class='icon green'><i class='fa-solid fa-file-circle-check'></i></div>
<h2>""" + totalExam + """
		
		</h2>
<span>Total Exams</span>
</div>

<div class='card-box'>
<div class='icon orange'><i class='fa-solid fa-users'></i></div>
<h2>""" + totalStudent + """
		
		</h2>
<span>Total Students</span>
</div>

<div class='card-box'>
<div class='icon red'><i class='fa-solid fa-trophy'></i></div>
<h2>""" + passPercentage + """
		
		%</h2>
<span>Pass Percentage</span>
</div>

</div>

<div class='table-box'>

<h3><i class='fa-solid fa-clock-rotate-left'></i> Recent Exams</h3>

<table class='table table-hover'>

<tr>
<th>ID</th>
<th>Exam Name</th>
<th>Total Questions</th>
<th>Total Marks</th>
<th>Duration</th>
</tr>
""");

        if (ex.isPresent() && !ex.get().isEmpty()) {

            List<AdminModel> list = ex.get();

            for (AdminModel model : list) {

                out.print("<tr>"
                        + "<td>" + model.getExam_id() + "</td>"
                        + "<td>" + model.getExam_name() + "</td>"
                        + "<td>" + model.getTotal_questions() + "</td>"
                        + "<td>" + model.getTotal_marks() + "</td>"
                        + "<td>" + model.getExam_duration() + " Min</td>"
                        + "</tr>");
            }

        } else {

            out.print("""
<tr>
<td colspan='5' style='color:red;font-weight:bold;'>
No Exams Available
</td>
</tr>
""");
        }

        out.print("""
</table>

</div>

<footer>
© 2026 Online Exam System | Designed with ❤️❤️❤️
</footer>

</div>
</div>

</body>
</html>
""");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}