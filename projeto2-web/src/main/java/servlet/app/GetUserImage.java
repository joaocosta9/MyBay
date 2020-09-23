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
import java.nio.file.Files;


@WebServlet("/app/GetUserImage")
public class GetUserImage extends HttpServlet{

    @EJB
    private UsersInterface userEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User oldUser = (User) request.getSession().getAttribute("user");

        if(!userEJB.ckeckImageVericity(oldUser)){

            request.getSession(false);
            if (request.getSession() != null) {
                request.getSession().invalidate();
            }

            PrintWriter out = response.getWriter();

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Wrong Information');");
            out.println("location='Welcome.jsp';");
            out.println("</script>");


        } else {
            String image_path = oldUser.getImage_path();

            String[] aux = image_path.split("\\.");
            String type = aux[1];

            File image = new File(image_path);
            byte[] fileContent = Files.readAllBytes(image.toPath());

            response.setContentType("image" + File.separator + type);
            response.setContentLength(fileContent.length);

            response.getOutputStream().write(fileContent);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }



}
