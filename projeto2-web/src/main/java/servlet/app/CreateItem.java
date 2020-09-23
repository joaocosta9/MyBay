package servlet.app;


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


@WebServlet("/app/CreateItem")

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

public class CreateItem extends HttpServlet {

    @EJB
    private UsersInterface userEJB;

    @EJB
    private ItemsInterface itemEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        doPost(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");


        User user = (User) request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String category = request.getParameter("category");
        int price = Integer.parseInt(request.getParameter("price"));
        Part file = request.getPart("file");

        User update_user = userEJB.addItem(user,name,country,category,file,price);

        if(update_user != null){
            List<Item> items = itemEJB.getAllItems("",null);
            request.getSession().setAttribute("items",items);
            request.getSession().setAttribute("user", update_user);
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        }
        else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('User or password incorrect');");
            out.println("location='HomePage.jsp';");
            out.println("</script>");
        }

    }

}