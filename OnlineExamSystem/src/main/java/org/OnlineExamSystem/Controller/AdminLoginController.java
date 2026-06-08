package org.OnlineExamSystem.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

import org.OnlineExamSystem.model.AdminLoginModel;
import org.OnlineExamSystem.service.AdminService;
import org.OnlineExamSystem.service.AdminServiceImpl;

@WebServlet("/AdminLogin")
public class AdminLoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// ================= SHOW LOGIN PAGE =================
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("""
<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Admin Login</title>

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
display:flex;
flex-direction:column;
background:#f8fafc;
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
margin-left:10px;
transition:0.3s;
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
padding:50px 30px;
}

.login-wrapper{
width:100%;
max-width:1250px;
display:flex;
background:white;
border-radius:30px;
overflow:hidden;
box-shadow:0 20px 50px rgba(0,0,0,0.08);
}

/* LEFT SIDE */

.left-side{
flex:1;
padding:70px 60px;
background:linear-gradient(135deg,#2563eb,#3b82f6);
color:white;
display:flex;
flex-direction:column;
justify-content:center;
}

.left-side h1{
font-size:55px;
font-weight:700;
line-height:1.3;
margin-bottom:20px;
}

.left-side p{
font-size:18px;
line-height:1.9;
margin-bottom:30px;
}

.feature{
margin-bottom:15px;
font-size:17px;
}

.feature i{
margin-right:10px;
color:#facc15;
}

/* RIGHT SIDE */

.right-side{
flex:1;
padding:70px 60px;
display:flex;
justify-content:center;
align-items:center;
}

.login-box{
width:100%;
max-width:450px;
}

.login-box h2{
text-align:center;
margin-bottom:35px;
font-size:38px;
font-weight:700;
color:#111827;
}

.login-box h2 i{
color:#2563eb;
}

.input-group{
position:relative;
margin-bottom:22px;
}

.input-group i{
position:absolute;
top:17px;
left:15px;
color:#6b7280;
}

.form-control{
height:55px;
padding-left:45px;
border:1px solid #d1d5db;
border-radius:14px;
background:#f9fafb;
}

.form-control:focus{
border:1px solid #2563eb;
box-shadow:none;
background:white;
}

.login-btn{
width:100%;
padding:15px;
border:none;
border-radius:50px;
background:linear-gradient(45deg,#2563eb,#3b82f6);
color:white;
font-size:18px;
font-weight:600;
transition:0.3s;
}

.login-btn:hover{
transform:translateY(-4px);
}

.bottom-text{
text-align:center;
margin-top:18px;
}

.bottom-text a{
color:#2563eb;
text-decoration:none;
font-weight:600;
}

/* ERROR */

.error-box{
background:#fee2e2;
color:#dc2626;
padding:12px;
border-radius:10px;
margin-bottom:18px;
text-align:center;
font-weight:600;
}

/* FOOTER */

.footer{
text-align:center;
padding:25px;
background:white;
border-top:1px solid #e5e7eb;
}

/* RESPONSIVE */

@media(max-width:992px){

.login-wrapper{
flex-direction:column;
}

.left-side,
.right-side{
padding:40px 25px;
}

.left-side h1{
font-size:38px;
}

}

</style>
</head>

<body>

<!-- NAVBAR -->

<nav class='navbar'>
<div class='container-fluid d-flex justify-content-between align-items-center'>

<a class='navbar-brand d-flex align-items-center' href='#'>
<i class='fa-solid fa-graduation-cap logo'></i>
<span class='logo-text'>Online Exam System</span>
</a>

<div>
<a href='Home.html'><button class='nav-btn'>Home</button></a>
<a href='registration'><button class='nav-btn'>Register</button></a>
</div>

</div>
</nav>

<!-- MAIN -->

<div class='main'>

<div class='login-wrapper'>

<!-- LEFT -->

<div class='left-side'>

<h1>Admin Control Dashboard</h1>

<p>
Welcome to the administrator portal of Online Exam System.
Manage exams, students, questions and results securely.
</p>

<div class='feature'>
<i class='fa-solid fa-circle-check'></i>
Manage Student Records
</div>

<div class='feature'>
<i class='fa-solid fa-circle-check'></i>
Create & Schedule Exams
</div>

<div class='feature'>
<i class='fa-solid fa-circle-check'></i>
View Results Instantly
</div>

</div>

<!-- RIGHT -->

<div class='right-side'>

<div class='login-box'>

<h2>
<i class='fa-solid fa-user-shield'></i>
Admin Login
</h2>
""");

		// show error message if login failed
		String error = request.getParameter("error");
		if (error != null) {
			out.print("<div class='error-box'>Invalid Login Credentials</div>");
		}

		out.print("""
<form action='AdminLogin' method='post'>

<div class='input-group'>
<i class='fa-solid fa-envelope'></i>
<input type='email'
name='email'
class='form-control'
placeholder='Enter Admin Email'
required>
</div>

<div class='input-group'>
<i class='fa-solid fa-lock'></i>
<input type='password'
name='password'
class='form-control'
placeholder='Enter Password'
required>
</div>

<button type='submit' class='login-btn'>
<i class='fa-solid fa-right-to-bracket'></i>
 Login Now
</button>

<div class='bottom-text'>
Back to <a href='Home.html'>Home</a>
</div>

</form>

</div>

</div>

</div>

</div>

<footer class='footer'>
© 2026 Online Exam System | Designed with ❤️
</footer>

</body>
</html>
""");
	}

	// ================= LOGIN PROCESS =================
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		AdminLoginModel model = new AdminLoginModel();
		model.setEmail(email);
		model.setPassword(password);

		AdminService service = new AdminServiceImpl();

		boolean result = service.isLoginAdmin(model);

		if (result) {

			HttpSession session = request.getSession();
			session.setAttribute("admin", email);

			response.sendRedirect("admindashboard");

		} else {

			response.sendRedirect("AdminLogin?error=1");
		}
	}
}