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


@WebServlet("/app/EditItem")

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

public class EditItem extends HttpServlet {

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

        User oldUser = (User) request.getSession().getAttribute("user");
        Item item = (Item) request.getSession().getAttribute("item");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String country = request.getParameter("country");
        int price = Integer.parseInt(request.getParameter("price"));
        Part file = request.getPart("file");
        String delete = request.getParameter("delete");

        User new_user;
        boolean check = false;

        for (Item item1: oldUser.getItems()){

            if(item.getItemId() == item1.getItemId())
                check = true;

        }

        if (!check){
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
            return;
        }

        if(delete != null){
            new_user = itemEJB.deleteItem(item,oldUser);
            List<Item> items = itemEJB.getAllItems("",null);
            request.getSession().setAttribute("user", new_user);
            request.getSession().setAttribute("items",items);
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
            return;
        }

        new_user = itemEJB.chagedata(oldUser,item,name,country,category,price,file);

        if(new_user != null){
            List<Item> items = itemEJB.getAllItems("",null);
            request.getSession().setAttribute("items",items);
            request.getSession().setAttribute("user", new_user);
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        }
        else {
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        }

    }

}