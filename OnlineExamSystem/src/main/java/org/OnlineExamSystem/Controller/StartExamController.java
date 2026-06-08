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

@WebServlet("/StartExamController")
public class StartExamController extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		// SESSION CHECK
		HttpSession session =
				request.getSession(false);

		if(session == null)
		{
			response.sendRedirect("studentlogin.html");
			return;
		}

		// GET EXAM ID
		String examParam =
				request.getParameter("examId");

		if(examParam == null)
		{
			out.println("<h2>Invalid Exam ID</h2>");
			return;
		}

		int examId =
				Integer.parseInt(examParam);

		try {

			Connection conn =
					dbConnection.getConnection();

			String sql =
					"select * from exam where exam_id=?";

			PreparedStatement ps =
					conn.prepareStatement(sql);

			ps.setInt(1, examId);

			ResultSet rs =
					ps.executeQuery();

			if(rs.next()) {

				out.println("<!DOCTYPE html>");

				out.println("<html>");

				out.println("<head>");

				out.println("<meta charset='UTF-8'>");

				out.println("<title>Start Exam</title>");

				out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");

				out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css'>");

				out.println("<style>");

				out.println("body{");
				out.println("background:#f4f7fc;");
				out.println("font-family:Arial;");
				out.println("}");

				out.println(".exam-box{");
				out.println("max-width:800px;");
				out.println("margin:auto;");
				out.println("margin-top:50px;");
				out.println("}");

				out.println(".card{");
				out.println("border:none;");
				out.println("border-radius:15px;");
				out.println("}");

				out.println(".title{");
				out.println("color:#0d6efd;");
				out.println("font-weight:bold;");
				out.println("}");

				out.println(".info-box{");
				out.println("background:#eef4ff;");
				out.println("padding:15px;");
				out.println("border-radius:10px;");
				out.println("margin-bottom:20px;");
				out.println("}");

				out.println("ul li{");
				out.println("margin-bottom:10px;");
				out.println("}");

				out.println("</style>");

				out.println("</head>");

				out.println("<body>");

				out.println("<div class='container exam-box'>");

				out.println("<div class='card shadow p-5'>");

				out.println("<h1 class='title mb-4'>");

				out.println("<i class='fa-solid fa-book-open'></i> ");

				out.println(rs.getString("exam_name"));

				out.println("</h1>");

				out.println("<div class='info-box'>");

				out.println("<h5>");

				out.println("<b>Total Questions :</b> ");

				out.println(rs.getInt("total_questions"));

				out.println("</h5>");

				out.println("<h5>");

				out.println("<b>Total Marks :</b> ");

				out.println(rs.getInt("total_marks"));

				out.println("</h5>");

				out.println("<h5>");

				out.println("<b>Duration :</b> ");

				out.println(rs.getInt("exam_duration"));

				out.println(" Minutes");

				out.println("</h5>");

				out.println("</div>");

				out.println("<h4 class='mb-3'>Instructions</h4>");

				out.println("<ul>");

				out.println("<li>Read all questions carefully.</li>");

				out.println("<li>Do not refresh the page during exam.</li>");

				out.println("<li>Each question carries equal marks.</li>");

				out.println("<li>Click submit button after completing exam.</li>");

				out.println("<li>Your result will display immediately.</li>");

				out.println("</ul>");

				out.println("<div class='mt-4'>");

				out.println("<a href='LoadQuestionsController?examId="
						+ examId + "'>");

				out.println("<button class='btn btn-primary btn-lg'>");

				out.println("<i class='fa-solid fa-play'></i> Start Exam");

				out.println("</button>");

				out.println("</a>");

				out.println("</div>");

				out.println("</div>");

				out.println("</div>");

				out.println("</body>");

				out.println("</html>");

			}
			else {

				out.println("<h2>Exam Not Found</h2>");

			}

		}
		catch(Exception e) {

			out.println("<h3>Error : "
					+ e.getMessage()
					+ "</h3>");

		}

	}
}