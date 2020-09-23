package servlet.app;

import data.User;
import data.Item;
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
import java.nio.file.Files;


@WebServlet("/app/RedirectItem")
public class RedirectItem extends HttpServlet{

    @EJB
    private UsersInterface userEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        User user = (User) request.getSession().getAttribute("user");
        List<Item> items;

        String view = request.getParameter("view");

        if(view!=null){
            items = (List<Item>) request.getSession().getAttribute("items");
            for (Item item:items){
                if(item.getItemId() == id){
                    request.getSession().setAttribute("item",item);
                    response.sendRedirect(request.getContextPath() + "/ViewItem.jsp");
                }
            }
        }
        else {
            items = user.getItems();
            for (Item item:items){
                if(item.getItemId() == id){
                    request.getSession().setAttribute("item",item);
                    response.sendRedirect(request.getContextPath() + "/EditItem.jsp");
                }
            }
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }



}
