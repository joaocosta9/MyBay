package servlet;

import data.User;
import ejb.UsersInterface;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Random;


@WebServlet("/Logout")
public class Logout extends HttpServlet {

    @EJB
    private UsersInterface userEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession(false);
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }

        response.sendRedirect(request.getContextPath() + "/Welcome.jsp");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

}
