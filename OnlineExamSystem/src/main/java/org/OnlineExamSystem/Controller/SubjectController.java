package org.OnlineExamSystem.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.AdminModel;
import org.OnlineExamSystem.service.subjectService;
import org.OnlineExamSystem.service.subjectServiceimpl;

@WebServlet("/subject")
public class SubjectController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("AdminLogin");
            return;
        }

        String adminEmail = session.getAttribute("admin").toString();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        subjectService service = new subjectServiceimpl();

        String msg = "";

        // ================= ADD SUBJECT =================

        String btn = request.getParameter("s");

        if (btn != null) {

            String subjectName = request.getParameter("subject_name");

            AdminModel model = new AdminModel();
            model.setSubject_name(subjectName);

            boolean result = service.addSubject(model);

            if (result) {
                msg = "<div class='alert alert-success'>Subject Added Successfully</div>";
            } else {
                msg = "<div class='alert alert-danger'>Subject Not Added</div>";
            }
        }

        // ================= DELETE SUBJECT =================

        String deleteId = request.getParameter("deleteid");

        if (deleteId != null) {

            int sid = Integer.parseInt(deleteId);

            boolean result = service.deleteSubject(sid);

            if (result) {
                msg = "<div class='alert alert-success'>Subject Deleted Successfully</div>";
            } else {
                msg = "<div class='alert alert-danger'>Subject Not Deleted</div>";
            }
        }

        // ================= UPDATE SUBJECT =================

        String updateBtn = request.getParameter("updatebtn");

        if (updateBtn != null) {

            int sid = Integer.parseInt(request.getParameter("subject_id"));

            String subjectName = request.getParameter("subject_name");

            AdminModel model = new AdminModel();

            model.setSubject_id(sid);
            model.setSubject_name(subjectName);

            boolean result = service.updateSubject(model);

            if (result) {
                msg = "<div class='alert alert-success'>Subject Updated Successfully</div>";
            } else {
                msg = "<div class='alert alert-danger'>Subject Not Updated</div>";
            }
        }

        Optional<List<AdminModel>> o = service.viewSubject();

        out.print("""
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Subjects</title>

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

.table-box{
background:#ffffff;
padding:25px;
border-radius:25px;
box-shadow:0 12px 30px rgba(0,0,0,0.08);
}

table th{
background:#eff6ff;
text-align:center;
padding:14px;
}

table td{
text-align:center;
padding:14px;
vertical-align:middle;
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

<a href='subject' class='active'>
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

<a href='results'>
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

<h1>Subjects</h1>
<p>Manage all exam subjects here.</p>

<div class='table-box'>
""");

        out.print(msg);

        out.print("""
<table class='table table-hover'>

<tr>
<th>Sr.No</th>
<th>ID</th>
<th>Subject Name</th>
<th>Delete</th>
<th>Update</th>
</tr>
""");

        if (o.isPresent()) {

            int count = 1;

            for (AdminModel m : o.get()) {

                out.print("<tr>");

                out.print("<td>" + count++ + "</td>");

                out.print("<td>" + m.getSubject_id() + "</td>");

                out.print("<td>" + m.getSubject_name() + "</td>");

                // DELETE BUTTON

                out.print("<td>");

                out.print("<a href='subject?deleteid="
                        + m.getSubject_id()
                        + "' class='btn btn-danger btn-sm'>Delete</a>");

                out.print("</td>");

                // UPDATE BUTTON

                out.print("<td>");

                out.print("<form action='subject' method='post' class='d-flex gap-2'>");

                out.print("<input type='hidden' name='subject_id' value='"
                        + m.getSubject_id() + "'>");

                out.print("<input type='text' name='subject_name' value='"
                        + m.getSubject_name()
                        + "' class='form-control form-control-sm'>");

                out.print("<button type='submit' name='updatebtn' value='update' class='btn btn-warning btn-sm'>Update</button>");

                out.print("</form>");

                out.print("</td>");

                out.print("</tr>");
            }

        } else {

            out.print("<tr><td colspan='5'>No Subject Found</td></tr>");
        }

        out.print("""
</table>

<div class='mt-4 d-flex justify-content-end'>

<form action='subject' method='post' class='d-flex gap-2'>

<input type='text'
name='subject_name'
placeholder='Enter Subject Name'
required
class='form-control'
style='width:250px;'>

<button type='submit'
name='s'
value='Add'
class='btn btn-primary'>

<i class='fa-solid fa-plus'></i>
Add Subject

</button>

</form>

</div>

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}