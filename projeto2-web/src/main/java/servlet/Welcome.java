package servlet;

import data.User;
import ejb.UsersInterface;

import javax.ejb.EJB;
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


@WebServlet("/Welcome")
public class Welcome extends HttpServlet {

    @EJB
    private UsersInterface userEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String country =  request.getParameter("country");


        boolean register = userEJB.register(username,password,email,name,country);

        if(register){
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
        }

        else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Email already exists');");
            out.println("location='Welcome.jsp';");
            out.println("</script>");
        }

    }

}
