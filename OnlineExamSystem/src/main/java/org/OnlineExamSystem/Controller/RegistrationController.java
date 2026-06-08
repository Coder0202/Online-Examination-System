package org.OnlineExamSystem.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

import org.OnlineExamSystem.model.RegisterModel;
import org.OnlineExamSystem.service.RegiService;
import org.OnlineExamSystem.service.RegiServiceImpl;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        showPage(response, "");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String student_name = request.getParameter("student_name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String course = request.getParameter("course");
            String mobile = request.getParameter("mobile");

            RegisterModel model = new RegisterModel();

            model.setStudent_name(student_name);
            model.setEmail(email);
            model.setPassword(password);
            model.setCourse(course);
            model.setMobile(mobile);

            RegiService service = new RegiServiceImpl();

            boolean result = service.isRegisterUser(model);

            if (result) {
                showPage(response,
                        "<div class='msg success'>🎉 Registration Successful! Welcome "
                                + student_name + "</div>");
            } else {
                showPage(response,
                        "<div class='msg error'>❌ Registration Failed!</div>");
            }

        } catch (Exception e) {

            e.printStackTrace();

            showPage(response,
                    "<div class='msg error'>⚠ Something Went Wrong!</div>");
        }
    }

    private void showPage(HttpServletResponse response, String message)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("""
<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Student Registration</title>

<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>
<link rel='stylesheet'
href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:Poppins,sans-serif;
}

body{
min-height:100vh;
background:#f8fafc;
display:flex;
flex-direction:column;
}

/* ================= NAVBAR ================= */

.navbar{
padding:18px 50px;
background:#ffffff;
box-shadow:0 4px 15px rgba(0,0,0,0.08);
}

.logo{
font-size:35px;
color:#2563eb;
}

.logo-text{
font-size:28px;
font-weight:700;
margin-left:10px;
color:#111827;
}

.nav-btn{
border:none;
padding:10px 24px;
border-radius:30px;
background:linear-gradient(45deg,#2563eb,#3b82f6);
color:white;
font-weight:600;
transition:0.3s;
margin-left:10px;
}

.nav-btn:hover{
transform:translateY(-3px);
}

/* ================= MAIN ================= */

.main{
flex:1;
display:flex;
justify-content:center;
align-items:center;
padding:30px;
}

.main-box{
width:100%;
max-width:1100px;
display:flex;
gap:50px;
flex-wrap:wrap;
align-items:center;
}

.left{
flex:1;
}

.left h1{
font-size:60px;
font-weight:700;
line-height:1.2;
color:#111827;
}

.left span{
color:#2563eb;
}

.left p{
margin-top:20px;
font-size:18px;
line-height:1.8;
color:#6b7280;
}

.right{
flex:1;
display:flex;
justify-content:center;
}

.form-box{
width:100%;
max-width:450px;
padding:40px;
background:white;
border-radius:25px;
box-shadow:0 15px 35px rgba(0,0,0,0.08);
}

.form-box h2{
text-align:center;
margin-bottom:25px;
font-weight:700;
color:#111827;
}

.form-box h2 i{
color:#2563eb;
}

.input-group{
position:relative;
margin-bottom:18px;
}

.input-group i{
position:absolute;
top:17px;
left:15px;
color:#6b7280;
}

.form-control{
height:50px;
padding-left:45px;
border:1px solid #d1d5db;
border-radius:12px;
background:#f9fafb;
}

.form-control:focus{
border:1px solid #2563eb;
box-shadow:none;
background:white;
}

.submit-btn{
width:100%;
padding:14px;
border:none;
border-radius:50px;
background:linear-gradient(45deg,#2563eb,#3b82f6);
color:white;
font-size:18px;
font-weight:600;
}

.msg{
padding:14px;
border-radius:12px;
text-align:center;
margin-bottom:20px;
font-weight:bold;
}

.success{
background:#dcfce7;
color:#15803d;
border:1px solid #22c55e;
}

.error{
background:#fee2e2;
color:#b91c1c;
border:1px solid #ef4444;
}

.bottom{
text-align:center;
margin-top:18px;
color:#6b7280;
}

.bottom a{
color:#2563eb;
text-decoration:none;
font-weight:600;
}

.footer{
text-align:center;
padding:25px;
background:white;
border-top:1px solid #e5e7eb;
}

@media(max-width:900px){

.main-box{
flex-direction:column;
text-align:center;
}

.left h1{
font-size:42px;
}

.navbar{
padding:15px 20px;
}

.logo-text{
font-size:20px;
}

}

</style>
</head>

<body>

<!-- ================= NAVBAR ================= -->

<nav class='navbar'>

<div class='container-fluid d-flex justify-content-between align-items-center'>

<a class='navbar-brand d-flex align-items-center' href='#'>

<i class='fa-solid fa-graduation-cap logo'></i>

<span class='logo-text'>
Online Exam System
</span>

</a>

<div>

<a href='Home.html'>
<button class='nav-btn'>Home</button>
</a>

<a href='LoginRegisteration.html'>
<button class='nav-btn'>Login</button>
</a>

</div>

</div>

</nav>

<!-- ================= MAIN ================= -->

<div class='main'>

<div class='main-box'>

<div class='left'>

<h1>
Student <br>
<span>Registration</span>
</h1>

<p>
Create your account to attend exams,
track results and manage your learning journey.
</p>

</div>

<div class='right'>

<div class='form-box'>

<h2>
<i class='fa-solid fa-user-plus'></i>
Register
</h2>
""" + message + """

<form action='registration' method='post' id='registerForm'>

<div class='input-group'>
<i class='fa-solid fa-user'></i>
<input type='text' name='student_name'
class='form-control'
placeholder='Enter Student Name' required>
</div>

<div class='input-group'>
<i class='fa-solid fa-envelope'></i>
<input type='email' name='email'
class='form-control'
placeholder='Enter Email Address' required>
</div>

<div class='input-group'>
<i class='fa-solid fa-lock'></i>
<input type='password' name='password'
class='form-control'
placeholder='Enter Password' required>
</div>

<div class='input-group'>
<i class='fa-solid fa-book'></i>
<input type='text' name='course'
class='form-control'
placeholder='Enter Course Name' required>
</div>

<div class='input-group'>
<i class='fa-solid fa-phone'></i>
<input type='text' name='mobile'
class='form-control'
placeholder='Enter Mobile Number' required>
</div>

<button type='submit' class='submit-btn'>
<i class='fa-solid fa-paper-plane'></i>
Create Account
</button>

<div class='bottom'>
Already have account?
<a href='LoginRegisteration.html'> Login</a>
</div>

</form>

</div>
</div>
</div>
</div>

<footer class='footer'>
© 2026 Online Exam System | Designed with ❤️
</footer>

<script src='js/validation.js'></script>

</body>
</html>
""");
    }
}