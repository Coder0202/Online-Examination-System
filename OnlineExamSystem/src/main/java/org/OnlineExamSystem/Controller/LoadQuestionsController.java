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

@WebServlet("/LoadQuestionsController")
public class LoadQuestionsController extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out =
				response.getWriter();

		int examId =
		Integer.parseInt(
		request.getParameter("examId"));

		try {

			Connection conn =
			dbConnection.getConnection();

			String sql =
			"select * from questions where exam_id=?";

			PreparedStatement ps =
			conn.prepareStatement(sql);

			ps.setInt(1, examId);

			ResultSet rs =
			ps.executeQuery();

			out.println("<html>");
			out.println("<head>");

			out.println("<title>Online Exam</title>");

			out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");

			out.println("</head>");

			out.println("<body style='background:#f4f7fc'>");

			out.println("<div class='container mt-5'>");

			out.println("<div class='card p-4 shadow'>");

			out.println("<h1 class='text-primary mb-4'>");
			out.println("Online Exam");
			out.println("</h1>");

			out.println("<form action='SubmitExamController' method='post'>");

			out.println("<input type='hidden' name='examId' value='"+examId+"'>");

			int count = 1;

			while(rs.next()) {

				int questionId =
				rs.getInt("question_id");

				out.println("<div class='mb-4'>");

				out.println("<h5>");

				out.println("Q"+count++ +". ");

				out.println(rs.getString("question_text"));

				out.println("</h5>");

				String option1 =
				rs.getString("option1");

				String option2 =
				rs.getString("option2");

				String option3 =
				rs.getString("option3");

				String option4 =
				rs.getString("option4");

				out.println("<div class='form-check'>");
				out.println("<input class='form-check-input' type='radio' name='q"+questionId+"' value='"+option1+"' required>");
				out.println(option1);
				out.println("</div>");

				out.println("<div class='form-check'>");
				out.println("<input class='form-check-input' type='radio' name='q"+questionId+"' value='"+option2+"' required>");
				out.println(option2);
				out.println("</div>");

				out.println("<div class='form-check'>");
				out.println("<input class='form-check-input' type='radio' name='q"+questionId+"' value='"+option3+"' required>");
				out.println(option3);
				out.println("</div>");

				out.println("<div class='form-check'>");
				out.println("<input class='form-check-input' type='radio' name='q"+questionId+"' value='"+option4+"' required>");
				out.println(option4);
				out.println("</div>");

				out.println("<hr>");

			}

			out.println("<button type='submit' class='btn btn-success btn-lg'>");

			out.println("Submit Exam");

			out.println("</button>");

			out.println("</form>");

			out.println("</div>");
			out.println("</div>");

			out.println("</body>");
			out.println("</html>");

		}
		catch(Exception e) {

			e.printStackTrace();

		}

	}
}