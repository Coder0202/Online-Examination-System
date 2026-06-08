package org.OnlineExamSystem.Controller;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutController")

public class LogoutController
extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {

        // GET SESSION

        HttpSession session =
                request.getSession(false);

        // DESTROY SESSION

        if(session != null)
        {
            session.invalidate();
        }

        // REDIRECT HOME PAGE

        response.sendRedirect(
                "Home.html");
    }
}