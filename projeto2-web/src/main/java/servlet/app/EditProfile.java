package servlet.app;


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


@WebServlet("/app/EditProfile")

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

public class EditProfile extends HttpServlet {

    @EJB
    private UsersInterface userEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        doPost(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");


        User oldUser = (User) request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        Part file = request.getPart("file");

        User user = userEJB.changeData(oldUser,username,password,email,name,country,file);

        if(user != null){
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userSession", "loggedin");
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        }
        else {
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        }

    }

}