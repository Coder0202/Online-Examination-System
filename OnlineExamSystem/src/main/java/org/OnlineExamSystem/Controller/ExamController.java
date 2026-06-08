package org.OnlineExamSystem.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.OnlineExamSystem.model.ExamModel;
import org.OnlineExamSystem.repositiory.ExamRepository;
import org.OnlineExamSystem.repositiory.ExamRepositioryImpl;

@WebServlet("/exam")
public class ExamController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ExamRepository repo =
            new ExamRepositioryImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("admin") == null) {

            response.sendRedirect("AdminLogin");
            return;
        }

        String adminEmail =
                session.getAttribute("admin").toString();

        // DELETE

        String deleteId =
                request.getParameter("deleteId");

        if (deleteId != null &&
                !deleteId.isEmpty()) {

            try {

                repo.isDeleteExam(
                        Integer.parseInt(deleteId));

            } catch (Exception e) {

                e.printStackTrace();
            }

            response.sendRedirect("exam");
            return;
        }

        // EDIT

        ExamModel editExam = null;

        String editId =
                request.getParameter("editId");

        if (editId != null &&
                !editId.isEmpty()) {

            try {

                editExam =
                        repo.getExamById(
                                Integer.parseInt(editId));

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        String search =
                request.getParameter("search");

        if (search == null) {
            search = "";
        }

        List<ExamModel> examList =
                repo.getAllExams();

        response.setContentType(
                "text/html;charset=UTF-8");

        PrintWriter out =
                response.getWriter();

        out.print("""
<!DOCTYPE html>
<html>
<head>
<title>Exam Management</title>

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

.admin-user{
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
transition:.3s;
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

/* CARD */

.card-box{
background:white;
padding:30px;
border-radius:22px;
box-shadow:0 10px 30px rgba(0,0,0,0.06);
border:1px solid #f1f5f9;
}

/* BUTTONS */

.btn-theme{
background:#2563eb;
border:none;
padding:10px 20px;
border-radius:10px;
color:white;
font-weight:600;
transition:.3s;
}

.btn-theme:hover{
background:#1d4ed8;
transform:translateY(-2px);
}

.btn-warning{
border-radius:8px;
}

.btn-danger{
border-radius:8px;
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

.table-hover tbody tr:hover{
background:#f8fafc;
}

/* SEARCH */

.form-control{
border-radius:10px;
padding:10px;
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

<div class='sidebar'>

<div class='menu'>

<a href='admindashboard'>
<i class='fa-solid fa-house'></i>
Dashboard
</a>

<a href='subject'>
<i class='fa-solid fa-book'></i>
Subjects
</a>

<a href='exam' class='active'>
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
<div class='content'>

<div class='card-box'>
""");

        // ===================
        // ADD / UPDATE FORM
        // ===================

        if (editExam != null) {

            out.print("""

<h3>Update Exam</h3>

<form method='post' action='exam'>

<input type='hidden'
name='action'
value='update'>

<input type='hidden'
name='examId'
value='""" + editExam.getExamId() + "'>" +

                    "<input type='text' class='form-control mb-3' name='examName' value='" +
                    editExam.getExamName() + "' required>" +

                    "<input type='number' class='form-control mb-3' name='subjectId' value='" +
                    editExam.getSubjectId() + "' required>" +

                    "<input type='number' class='form-control mb-3' name='totalQuestions' value='" +
                    editExam.getTotalQuestions() + "' required>" +

                    "<input type='number' class='form-control mb-3' name='totalMarks' value='" +
                    editExam.getTotalMarks() + "' required>" +

                    "<input type='number' class='form-control mb-3' name='examDuration' value='" +
                    editExam.getExamDuration() + "' required>" +

                    "<button class='btn-theme'>Update Exam</button>" +

                    "</form><hr>");
        }

        else {

            out.print("""

<h3>Add Exam</h3>

<form method='post' action='exam'>

<input type='hidden'
name='action'
value='add'>

<input type='text'
name='examName'
class='form-control mb-3'
placeholder='Exam Name'
required>

<input type='number'
name='subjectId'
class='form-control mb-3'
placeholder='Subject ID'
required>

<input type='number'
name='totalQuestions'
class='form-control mb-3'
placeholder='Total Questions'
required>

<input type='number'
name='totalMarks'
class='form-control mb-3'
placeholder='Total Marks'
required>

<input type='number'
name='examDuration'
class='form-control mb-3'
placeholder='Duration'
required>

<button class='btn-theme'>
Add Exam
</button>

</form>

<hr>
""");
        }

        out.print("""

<form method='get' action='exam' class='mb-3'>

<div class='input-group'>

<input type='text'
name='search'
class='form-control'
placeholder='Search Exam'
value='""" + search + "'>" +

                "<button class='btn btn-primary'>Search</button>" +

                "</div></form>");

        out.print("""

<table class='table table-bordered table-hover'>

<tr>

<th>ID</th>
<th>Exam Name</th>
<th>Subject ID</th>
<th>Questions</th>
<th>Marks</th>
<th>Duration</th>
<th>Edit</th>
<th>Delete</th>

</tr>
""");

        for (ExamModel e : examList) {

            if (search.isEmpty() ||
                    e.getExamName()
                            .toLowerCase()
                            .contains(search.toLowerCase())) {

                out.print(
                        "<tr>" +
                        "<td>" + e.getExamId() + "</td>" +
                        "<td>" + e.getExamName() + "</td>" +
                        "<td>" + e.getSubjectId() + "</td>" +
                        "<td>" + e.getTotalQuestions() + "</td>" +
                        "<td>" + e.getTotalMarks() + "</td>" +
                        "<td>" + e.getExamDuration() + " Min</td>" +
                        "<td><a class='btn btn-warning btn-sm' href='exam?editId=" + e.getExamId() + "'>Edit</a></td>" +
                        "<td><a class='btn btn-danger btn-sm' onclick='return confirm(\"Delete Exam?\")' href='exam?deleteId=" + e.getExamId() + "'>Delete</a></td>" +
                        "</tr>"
                );
            }
        }

        out.print("""

</table>

</div>

</div>

</div>

</body>

</html>
""");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");

        if (action == null) {

            response.sendRedirect("exam");
            return;
        }

        try {

            ExamModel model =
                    new ExamModel();

            if ("add".equals(action)) {

                model.setExamName(
                        request.getParameter("examName"));

                model.setSubjectId(
                        Integer.parseInt(
                                request.getParameter("subjectId")));

                model.setTotalQuestions(
                        Integer.parseInt(
                                request.getParameter("totalQuestions")));

                model.setTotalMarks(
                        Integer.parseInt(
                                request.getParameter("totalMarks")));

                model.setExamDuration(
                        Integer.parseInt(
                                request.getParameter("examDuration")));

                repo.isAddExam(model);
            }

            else if ("update".equals(action)) {

                model.setExamId(
                        Integer.parseInt(
                                request.getParameter("examId")));

                model.setExamName(
                        request.getParameter("examName"));

                model.setSubjectId(
                        Integer.parseInt(
                                request.getParameter("subjectId")));

                model.setTotalQuestions(
                        Integer.parseInt(
                                request.getParameter("totalQuestions")));

                model.setTotalMarks(
                        Integer.parseInt(
                                request.getParameter("totalMarks")));

                model.setExamDuration(
                        Integer.parseInt(
                                request.getParameter("examDuration")));

                repo.isUpdateExam(model);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        response.sendRedirect("exam");
    }
}