package servlet;

import data.Item;
import data.User;
import ejb.ItemsInterface;
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


@WebServlet("/Login")
public class Login extends HttpServlet {

    @EJB
    private UsersInterface userEJB;

    @EJB
    private ItemsInterface itemEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User login = userEJB.login(email, password);

        if (login != null) {
            List<Item> items = itemEJB.getAllItems("",null);
            request.getSession().setAttribute("items",items);
            request.getSession().setAttribute("user", login);
            request.getSession().setAttribute("userSession", "loggedin");
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('User or password incorrect');");
            out.println("location='Login.jsp';");
            out.println("</script>");
        }
    }

}
