package org.OnlineExamSystem.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.resultModel;
import org.OnlineExamSystem.service.ResultService;
import org.OnlineExamSystem.service.ResultServiceImpl;

@WebServlet("/results")
public class ResultController extends HttpServlet {

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

        // DATABASE FETCH

        ResultService service = new ResultServiceImpl();

        Optional<List<resultModel>> results = service.getAllResults();

        out.print("""
<!DOCTYPE html>
<html lang='en'>

<head>

<meta charset='UTF-8'>

<meta name='viewport'
content='width=device-width, initial-scale=1.0'>

<title>Results</title>

<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'
rel='stylesheet'>

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
overflow-x:hidden;
}

/* TOPBAR */

.topbar{
height:75px;
background:#ffffff;
display:flex;
justify-content:space-between;
align-items:center;
padding:0 30px;
box-shadow:0 4px 15px rgba(0,0,0,0.08);
}

.brand{
display:flex;
align-items:center;
gap:12px;
font-size:30px;
font-weight:700;
color:#2563eb;
}

.admin-user{
font-size:17px;
font-weight:600;
}

/* LAYOUT */

.wrapper{
display:flex;
min-height:calc(100vh - 75px);
}

/* SIDEBAR */

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

/* CONTENT */

.content{
flex:1;
padding:40px;
}

.content h1{
font-size:45px;
font-weight:700;
margin-bottom:10px;
}

.content p{
color:#6b7280;
font-size:18px;
margin-bottom:30px;
}

/* CARD */

.table-box{
background:#ffffff;
padding:25px;
border-radius:25px;
box-shadow:0 12px 30px rgba(0,0,0,0.08);
}

.table-box h3{
font-size:28px;
font-weight:700;
margin-bottom:20px;
color:#2563eb;
}

/* TABLE */

table th{
background:#eff6ff;
text-align:center;
padding:14px;
font-size:15px;
}

table td{
text-align:center;
padding:14px;
vertical-align:middle;
}

/* STATUS */

.pass{
color:#10b981;
font-weight:700;
}

.fail{
color:#ef4444;
font-weight:700;
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

<div class='admin-user'>
<i class='fa-solid fa-circle-user'></i>
Welcome, """ + adminEmail + """
</div>

</div>

<div class='wrapper'>

<!-- SIDEBAR -->

<div class='sidebar'>

<div class='profile'>

<i class='fa-solid fa-user-shield'></i>

<h4>Admin Panel</h4>

<p>Manage Everything Easily</p>

</div>

<div class='menu'>

<a href='admindashboard'>

<i class='fa-solid fa-house'></i>

Dashboard

</a>

<a href='subject'>

<i class='fa-solid fa-book'></i>

Subjects

</a>

<a href='exam'>

<i class='fa-solid fa-file-lines'></i>

Exams

</a>

<a href='questions'>

<i class='fa-solid fa-circle-question'></i>

Questions

</a>

<a href='students'>

<i class='fa-solid fa-users'></i>

Students

</a>

<a href='results' class='active'>

<i class='fa-solid fa-chart-column'></i>

Results

</a>

<a href='Home.html'>

<i class='fa-solid fa-right-from-bracket'></i>

Logout

</a>

</div>
</div>

<!-- CONTENT -->

<div class='content'>

<h1>Results</h1>

<p>
Manage all student exam results efficiently.
</p>

<div class='table-box'>

<h3>

<i class='fa-solid fa-chart-column'></i>

Student Results

</h3>

<table class='table table-hover'>

<tr>

<th>ID</th>



<th>Exam Name</th>

<th>Total Marks</th>

<th>Obtained</th>

<th>Percentage</th>

<th>Status</th>

</tr>
""");

        // DATABASE DATA SHOW

        if (results.isPresent() && !results.get().isEmpty()) {

            int count = 1;

            for (resultModel r : results.get()) {

                double percentage =
                        ((double) r.getObtained_marks()
                                / r.getTotal_marks()) * 100;

                String status =
                        percentage >= 40 ? "Pass" : "Fail";

                String statusClass =
                        percentage >= 40 ? "pass" : "fail";

                out.print("<tr>");

                out.print("<td>" + count++ + "</td>");

                out.print("<td>"
                        + r.getExam_name()
                        + "</td>");

                out.print("<td>"
                        + r.getTotal_marks()
                        + "</td>");

                out.print("<td>"
                        + r.getObtained_marks()
                        + "</td>");

                out.print("<td>"
                        + String.format("%.2f", percentage)
                        + "%</td>");

                out.print("<td class='"
                        + statusClass
                        + "'>"
                        + status
                        + "</td>");

                out.print("</tr>");
            }

        } else {

            out.print("""
<tr>

<td colspan='7'
style='color:red;font-weight:bold;'>

No Results Found

</td>

</tr>
""");
        }

        out.print("""
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

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}