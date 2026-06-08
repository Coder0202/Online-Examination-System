package org.OnlineExamSystem.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

@WebServlet("/questions")

@MultipartConfig(
fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 10
)

public class QuestionController extends HttpServlet {

    private static final long serialVersionUID = 1L;

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

        response.setContentType("text/html");

        PrintWriter out =
                response.getWriter();

        out.print("""

<!DOCTYPE html>
<html lang='en'>

<head>

<meta charset='UTF-8'>

<meta name='viewport'
content='width=device-width, initial-scale=1.0'>

<title>Questions</title>

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

/* FORM BOX */

.table-box{
background:#ffffff;
padding:30px;
border-radius:25px;
box-shadow:0 12px 30px rgba(0,0,0,0.08);
}

.form-control,
.form-select{
height:50px;
border-radius:12px;
}

textarea.form-control{
height:120px !important;
resize:none;
}

.btn-custom{
padding:12px 22px;
border:none;
border-radius:12px;
font-weight:600;
}

.btn-primary{
background:linear-gradient(45deg,#2563eb,#3b82f6);
border:none;
}

.btn-success{
background:linear-gradient(45deg,#10b981,#059669);
border:none;
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

Welcome,
""" + adminEmail + """

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

<a href='questions' class='active'>

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

<h1>Add Questions</h1>

<p>
Manage subject wise exam questions with CSV upload support.
</p>

<div class='table-box'>

<!-- BUTTON -->

<div class='d-flex justify-content-end mb-4'>

<button class='btn btn-success btn-custom'
data-bs-toggle='modal'
data-bs-target='#csvModal'>

<i class='fa-solid fa-file-csv'></i>

Upload CSV

</button>

</div>

<!-- FORM -->

<form action='questions'
method='post'>

<div class='row'>

<!-- SUBJECT -->

<div class='col-md-6 mb-4'>

<label class='form-label fw-bold'>

Select Subject

</label>

<select name='subject_id'
class='form-select'
required>

<option value=''>-- Select Subject --</option>

<option value='1'>Java</option>

<option value='2'>Python</option>

<option value='3'>C Programming</option>

<option value='4'>Database</option>

</select>

</div>

<!-- EXAM -->

<div class='col-md-6 mb-4'>

<label class='form-label fw-bold'>

Select Exam

</label>

<select name='exam_id'
class='form-select'
required>

<option value=''>-- Select Exam --</option>

<option value='1'>Java MCQ Test</option>

<option value='2'>Python Quiz</option>

<option value='3'>DBMS Test</option>

</select>

</div>

</div>

<!-- QUESTION -->

<div class='mb-4'>

<label class='form-label fw-bold'>

Question

</label>

<textarea
name='question_text'
class='form-control'
placeholder='Enter Question'
required></textarea>

</div>

<!-- OPTIONS -->

<div class='row'>

<div class='col-md-6 mb-4'>

<label class='form-label fw-bold'>

Option 1

</label>

<input type='text'
name='option1'
class='form-control'
placeholder='Enter Option 1'
required>

</div>

<div class='col-md-6 mb-4'>

<label class='form-label fw-bold'>

Option 2

</label>

<input type='text'
name='option2'
class='form-control'
placeholder='Enter Option 2'
required>

</div>

<div class='col-md-6 mb-4'>

<label class='form-label fw-bold'>

Option 3

</label>

<input type='text'
name='option3'
class='form-control'
placeholder='Enter Option 3'
required>

</div>

<div class='col-md-6 mb-4'>

<label class='form-label fw-bold'>

Option 4

</label>

<input type='text'
name='option4'
class='form-control'
placeholder='Enter Option 4'
required>

</div>

</div>

<!-- CORRECT ANSWER -->

<div class='mb-4'>

<label class='form-label fw-bold'>

Correct Answer

</label>

<select name='correct_answer'
class='form-select'
required>

<option value=''>-- Select Correct Answer --</option>

<option value='option1'>Option 1</option>

<option value='option2'>Option 2</option>

<option value='option3'>Option 3</option>

<option value='option4'>Option 4</option>

</select>

</div>

<!-- BUTTON -->

<button type='submit'
class='btn btn-primary btn-custom'>

<i class='fa-solid fa-plus'></i>

Add Question

</button>

</form>

</div>

<footer>

© 2026 Online Exam System | Designed with ❤️

</footer>

</div>

</div>

<!-- CSV MODAL -->

<div class="modal fade" id="csvModal">

<div class="modal-dialog">

<div class="modal-content">

<form method="post"
enctype="multipart/form-data">

<div class="modal-header">

<h5 class="modal-title">

Upload CSV File

</h5>

<button type="button"
class="btn-close"
data-bs-dismiss="modal">
</button>

</div>

<div class="modal-body">

<input type="file"
name="csvfile"
class="form-control"
accept=".csv"
required>

<br>

<small>

CSV Format:<br>

exam_id,question_text,
option1,option2,
option3,option4,
correct_answer

</small>

</div>

<div class="modal-footer">

<button type="submit"
class="btn btn-success">

Upload

</button>

</div>

</form>

</div>

</div>

</div>

<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js'></script>

</body>

</html>

""");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart =
                request.getPart("csvfile");

        if (filePart != null &&
                filePart.getSize() > 0) {

            BufferedReader br =
                    new BufferedReader(

                    new InputStreamReader(
                            filePart.getInputStream(),
                            StandardCharsets.UTF_8
                    )
            );

            String line;

            while ((line = br.readLine()) != null) {

                String data[] =
                        line.split(",");

                int examId =
                        Integer.parseInt(data[0]);

                String question =
                        data[1];

                String answer =
                        data[6];

                System.out.println(
                        examId + " " +
                                question + " " +
                                answer
                );
            }

        } else {

            String examId =
                    request.getParameter("exam_id");

            String question =
                    request.getParameter("question_text");

            String option1 =
                    request.getParameter("option1");

            String option2 =
                    request.getParameter("option2");

            String option3 =
                    request.getParameter("option3");

            String option4 =
                    request.getParameter("option4");

            String correctAnswer =
                    request.getParameter("correct_answer");

            System.out.println(examId);
            System.out.println(question);
            System.out.println(option1);
            System.out.println(option2);
            System.out.println(option3);
            System.out.println(option4);
            System.out.println(correctAnswer);
        }

        response.sendRedirect("questions");
    }
}