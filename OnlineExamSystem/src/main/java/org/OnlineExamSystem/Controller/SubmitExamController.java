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

@WebServlet("/SubmitExamController")
public class SubmitExamController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out =
                response.getWriter();

        int totalQuestions = 0;
        int correctAnswers = 0;

        try {

            Connection conn =
                    dbConnection.getConnection();

            // GET EXAM ID

            int examId =
                    Integer.parseInt(
                            request.getParameter("examId"));

            // SESSION CHECK

            HttpSession session =
                    request.getSession(false);

            if (session == null) {

                response.sendRedirect(
                        "studentlogin.html");

                return;
            }

            Integer studentId =
                    (Integer) session.getAttribute(
                            "studentId");

            String studentName =
                    (String) session.getAttribute(
                            "studentName");

            if (studentId == null) {

                response.sendRedirect(
                        "studentlogin.html");

                return;
            }

            // GET QUESTIONS

            PreparedStatement ps =
                    conn.prepareStatement(
                            "select * from questions where exam_id=?");

            ps.setInt(1, examId);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                totalQuestions++;

                int questionId =
                        rs.getInt("question_id");

                String correctAnswer =
                        rs.getString("correct_answer");

                String studentAnswer =
                        request.getParameter(
                                "q" + questionId);

                if (studentAnswer != null &&
                        studentAnswer.equals(correctAnswer)) {

                    correctAnswers++;
                }
            }

            // CALCULATE MARKS

            int obtainedMarks =
                    correctAnswers * 5;

            int totalMarks =
                    totalQuestions * 5;

            double percentage = 0;

            if (totalMarks > 0) {

                percentage =
                        ((double) obtainedMarks
                                / totalMarks) * 100;
            }

            // PASS / FAIL

            String status = "FAIL";

            if (percentage >= 40) {

                status = "PASS";
            }

            // SAVE RESULT

            PreparedStatement insertStmt =
                    conn.prepareStatement(

                            "insert into result(student_id,exam_id,total_marks,obtained_marks,percentage,status) values(?,?,?,?,?,?)"

                    );

            insertStmt.setInt(1, studentId);

            insertStmt.setInt(2, examId);

            insertStmt.setInt(3, totalMarks);

            insertStmt.setInt(4, obtainedMarks);

            insertStmt.setDouble(5, percentage);

            insertStmt.setString(6, status);

            insertStmt.executeUpdate();

            // RESULT PAGE

            out.println("""
<!DOCTYPE html>
<html lang='en'>

<head>

<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>

<title>Exam Result</title>

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

/* MAIN */

.container-box{
padding:40px;
}

/* RESULT CARD */

.result-card{
background:white;
padding:40px;
border-radius:20px;
box-shadow:0 10px 25px rgba(0,0,0,0.05);
border:1px solid #f1f5f9;
max-width:850px;
margin:auto;
}

.result-title{
text-align:center;
margin-bottom:30px;
}

.result-title i{
font-size:70px;
color:#2563eb;
margin-bottom:15px;
}

.result-title h1{
font-size:38px;
font-weight:700;
color:#2563eb;
}

.result-table{
margin-top:20px;
}

.result-table table{
width:100%;
}

.result-table th{
background:#eff6ff;
padding:16px;
text-align:left;
width:50%;
font-size:17px;
}

.result-table td{
padding:16px;
font-size:17px;
font-weight:600;
}

.pass{
color:#10b981;
font-weight:700;
font-size:22px;
}

.fail{
color:#ef4444;
font-weight:700;
font-size:22px;
}

.btn-box{
margin-top:35px;
display:flex;
justify-content:center;
gap:20px;
}

.btn-custom{
padding:12px 25px;
border:none;
border-radius:12px;
font-size:16px;
font-weight:600;
transition:0.3s;
text-decoration:none;
color:white;
}

.btn-result{
background:#10b981;
}

.btn-dashboard{
background:#2563eb;
}

.btn-custom:hover{
transform:translateY(-3px);
opacity:0.9;
}

footer{
margin-top:40px;
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

<!-- CONTENT -->

<div class='container-box'>

<div class='result-card'>

<div class='result-title'>

<i class='fa-solid fa-circle-check'></i>

<h1>Exam Submitted Successfully</h1>

<p>Your exam has been evaluated successfully.</p>

</div>

<div class='result-table'>

<table class='table table-bordered'>

<tr>
<th>Total Questions</th>
<td>""" + totalQuestions + """
		
		</td>
</tr>

<tr>
<th>Correct Answers</th>
<td>""" + correctAnswers + """
		
		</td>
</tr>

<tr>
<th>Total Marks</th>
<td>""" + totalMarks + """
		
		</td>
</tr>

<tr>
<th>Obtained Marks</th>
<td>""" + obtainedMarks + """
		
		</td>
</tr>

<tr>
<th>Percentage</th>
<td>""" + String.format("%.2f", percentage) + """
		
		%</td>
</tr>

<tr>
<th>Status</th>
<td>
""");

            if (status.equals("PASS")) {

                out.println("<span class='pass'>PASS</span>");

            } else {

                out.println("<span class='fail'>FAIL</span>");
            }

            out.println("""
</td>
</tr>

</table>

</div>

<div class='btn-box'>

<a href='ResultController' class='btn-custom btn-result'>
<i class='fa-solid fa-chart-column'></i>
View Results
</a>

<a href='DashboardController' class='btn-custom btn-dashboard'>
<i class='fa-solid fa-house'></i>
Dashboard
</a>

</div>

</div>

<footer>

© 2026 Online Exam System | Designed with ❤️

</footer>

</div>

</body>
</html>
""");

        } catch (Exception e) {

            out.println("""
<!DOCTYPE html>
<html>
<head>

<title>Error</title>

<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>

</head>

<body style='background:#f8fafc;'>

<div class='container mt-5'>

<div class='alert alert-danger text-center p-4'>

<h2>Something Went Wrong</h2>

<p>
""" + e.getMessage() + """
</p>

</div>

</div>

</body>
</html>
""");
        }
    }
}