package ejb;

import data.User;
import data.Item;
import ejb.MD5;

//import org.graalvm.compiler.lir.LIRInstruction;

import javax.ejb.Stateless;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.servlet.http.Part;

@Stateless
public class Users implements UsersInterface {
    @PersistenceContext(name = "Users")

    EntityManager em;
    MD5 encryptor = new MD5();
    private Logger log = Logger.getLogger("Users");

    private String UploadDirFaces = "/Users/miguelfernandes/Documents/IS/projeto2/ImagesFaces";
    private String UploadDirItems = "/Users/miguelfernandes/Documents/IS/projeto2/ImagesItems";


    public Users(){
        super();
    }

    public void logs(Logger logger, String msg, boolean error) {
        if (error)
            logger.severe(msg);
        else
            logger.warning(msg);
    }

    public boolean register(String username, String password, String email, String name, String country){

        String image_path = "/Users/miguelfernandes/Documents/IS/projeto2/Images/100.png";

        Query q = em.createQuery("from User u where u.email = :email").setParameter("email",email);
        List<User> Users = q.getResultList();

        if (!Users.isEmpty()) {
            this.logs(log, "User |email| already registered", true);
            return false;
        }

        q = em.createQuery("from User u where u.name = :username").setParameter("username",username);
        Users = q.getResultList();

        if (!Users.isEmpty()) {
            this.logs(log, "User |name| already registered", true);
            return false;
        }

        //Convert password
        String securePassword = encryptor.getSecurePassword(password);

        em.merge(new User(username,securePassword,email,name,country,image_path));
        this.logs(log, "User is register with success", false);
        return true;
    }

    public User login(String email, String password){

        Query q = em.createQuery("from User u where u.email = :email").setParameter("email",email);
        List<User> Users = q.getResultList();

        if (Users.isEmpty()) {
            this.logs(log, "User does not exist", true);
            return null;
        }

        User user = Users.get(0);

        String securePassword = encryptor.getSecurePassword(password);

        if(user.getPassword().equals(securePassword)) {
            this.logs(log, "User " + user.getUserId() + " successfully logged in", false);
            return user;
        }
        else {
            this.logs(log, "User " + user.getUserId() + " inserted the wrong password", true);
            return null;
        }

    }

    public User changeData(User user ,String username, String password, String email, String name, String country,Part file){

        Query q;
        List<User> Users;
        User u = em.find(User.class, user.getUserId());
        String securePassword = encryptor.getSecurePassword(password);
        boolean changed = false;

        if(!this.check_user(u,user))
            return null;

        if(!u.getName().equals(name)){

            u.setName(name);
            this.logs(log, "User's " + u.getUserId() + " name changed", false);
            changed = true;

        }

        if (!u.getUsername().equals(username)){

            q = em.createQuery("from User u where u.name = :username").setParameter("username",username);
            Users = q.getResultList();

            if (!Users.isEmpty()) {
                this.logs(log, "User" + user.getUserId() + " new name already registered", true);
                return null;
            }

            u.setUsername(username);
            this.logs(log, "User's " + user.getUserId() + " username changed", false);
            changed = true;
        }

        if (!u.getEmail().equals(email)){

            q = em.createQuery("from User u where u.email =:email").setParameter("email",email);
            Users = q.getResultList();

            if (!Users.isEmpty()) {
                this.logs(log, "User" + user.getUserId() + " new email already registered", true);
                return null;
            }

            u.setEmail(email);
            this.logs(log, "User's " + user.getUserId() + " email changed", false);
            changed = true;
        }


        if (country != null && !u.getCountry().equals(country)){

            u.setCountry(country);
            this.logs(log, "User's " + user.getUserId() + " country changed", false);
            changed = true;

        }


        if (!password.equals("1234") && !u.getPassword().equals(securePassword)){

            u.setPassword(securePassword);
            this.logs(log, "User's " + user.getUserId() + " password changed", false);
            changed = true;

        }


        if(!file.getSubmittedFileName().equals("")) {
            this.logs(log,file.getSubmittedFileName(),false);
            String[] aux_file = file.getSubmittedFileName().split("\\.");
            String file_type = aux_file[1];
            String directory = this.UploadDirFaces + File.separator + Integer.toString(u.getUserId()) + "." + file_type;

            try {

                file.write(directory);
            } catch (FileAlreadyExistsException exist) {
                Random r = new Random();
                directory = this.UploadDirFaces + File.separator + Integer.toString(u.getUserId()) + Integer.toString(r.nextInt()) + "." + file_type;
                try {
                    file.write(directory);
                } catch (IOException e) {
                    System.out.println(e);
                }
            } catch (IOException e) {
                System.out.println(e);
            }

            u.setImage_path(directory);
            changed = true;
        }

        if(changed){
            return u;
        }
        else {
            return null;
        }
    }

    public User addItem(User user, String item_name, String country, String category, Part file,int price){

        Query q = em.createQuery("from Item i where i.category = :category").setParameter("category","lazer");
        User u = em.find(User.class, user.getUserId());
        List<Item> Itmes = q.getResultList();
        Date date = new Date();

        if(!this.check_user(u,user))
            return null;

        if(!file.getSubmittedFileName().equals("")){
            u.addItem(new Item(item_name,category,country,u,price, date));
            Item item = u.getItems().get(u.getItems().size()-1);
            String[] aux_file = file.getSubmittedFileName().split("\\.");
            String file_type = aux_file[1];
            String directory = this.UploadDirItems + File.separator + Integer.toString(item.getItemId()) + "." + file_type;
            try {
                file.write(directory);
            } catch (FileAlreadyExistsException exist) {
                Random r = new Random();
                directory = this.UploadDirItems + File.separator + Integer.toString(item.getItemId()) + Integer.toString(r.nextInt()) + "." + file_type;
                try {
                    file.write(directory);
                } catch (IOException e) {
                    System.out.println(e);
                }
            } catch (IOException e) {
                System.out.println(e);
            }

            item.setImage_path(directory);

            this.logs(log, "User " + u.getUserId() + " started created item: " + item.getItemId(), false);
            return u;
        }

        return null;
    }

    public boolean deleteUser(User user){
        User u = em.find(User.class,user.getUserId());

        if(!check_user(u,user))
            return false;

        if(u!=null){
            em.remove(u);
            this.logs(log,"User removed",false);
            return true;
        }

        this.logs(log,"User not removed",false);
        return false;

    }

    public boolean check_user(User user1, User user2){
        String aux = user1.getName();
        if(!user2.getName().equals(aux))
            return false;

        aux = user1.getUsername();
        if(!user2.getUsername().equals(aux))
            return false;

        aux = user1.getEmail();
        if(!user2.getEmail().equals(aux))
            return false;

        aux = user1.getCountry();
        if(!user2.getCountry().equals(aux))
            return false;

        aux = user1.getPassword();
        if(!user2.getPassword().equals(aux))
            return false;

        return true;
    }

    public boolean ckeckImageVericity(User user){

        User u = em.find(User.class,user.getUserId());

        if(u.getImage_path().equals(user.getImage_path()))
            return true;

        return false;
    }

}
