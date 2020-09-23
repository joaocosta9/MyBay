package servlet.app;

import data.User;
import data.Item;
import ejb.Items;
import ejb.ItemsInterface;
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
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet("/app/Search")

public class Search extends HttpServlet {

    @EJB
    private ItemsInterface itemEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String category = request.getParameter("category");
        String country = request.getParameter("country");
        String search = request.getParameter("search");
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        String order = request.getParameter("order");
        String min_date = request.getParameter("mindate");



        System.out.println(min);
        System.out.println(max);

        int min_aux = 0;
        int max_aux = 0;


        if(category != null){
            System.out.println("check1");
            List<Item> items = itemEJB.getItemsCategory(category,order,search);
            request.getSession().setAttribute("items",items);
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        }

        else if (country != null){
            System.out.println("check2");
            List<Item> items = itemEJB.getItemsCounty(country,search, order);
            request.getSession().setAttribute("items",items);
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        }else if(!min.equals("") && !max.equals("")){
            System.out.println("check4");
            min_aux = Integer.parseInt(min);
            max_aux = Integer.parseInt(max);
            List<Item> items = itemEJB.getItemsPriceRange(min_aux,max_aux, order, search);
            request.getSession().setAttribute("items", items);
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        }else if(!min_date.equals("")){
            System.out.println(min_date);
            List<Item> items = itemEJB.getItemsAfterDate(min_date,order,search);
            request.getSession().setAttribute("items", items);
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        }else if(search != null) {
            System.out.println("check3");
            List<Item> items = itemEJB.getAllItems(search, order);
            request.getSession().setAttribute("items", items);
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");

        }else if(order != null){
            System.out.println("check 4");
            List<Item> items = itemEJB.getItemsbyOrder(order);
            request.getSession().setAttribute("items", items);
            response.sendRedirect(request.getContextPath() + "/HomePage.jsp");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);

    }


}
