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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.servlet.http.Part;

@Stateless
public class Items implements ItemsInterface{

    @PersistenceContext(name = "Items")
    EntityManager em;

    Logger log = Logger.getLogger("Items");
    private String UploadDirItems = "/Users/miguelfernandes/Documents/IS/projeto2/ImagesItems";

    public Items(){
        super();
    }

    public void logs(Logger logger, String msg, boolean error) {
        if (error)
            logger.severe(msg);
        else
            logger.warning(msg);
    }

    public List<Item> getAllItems(String partial,String type){
        Query q;
        if(!partial.equals("")){
            System.out.println(partial);
            q = em.createQuery("from Item i where i.name like :partial").setParameter("partial",partial +"%");}
        else
            q = em.createQuery("select item from Item item");

        this.logs(log,"Get all Items available",false);
        if(type == null){
            return q.getResultList();
        }
        return  comparationist( type,q.getResultList());
    }

    public List<Item> getItemsCategory(String category, String type, String partial){
        Query q;

        if(!partial.equals("")){
            q = em.createQuery("from Item i where i.category like :category and i.name like :partial").setParameter("category",category)
                    .setParameter("partial",partial+"%");
        }
        else{
            q = em.createQuery("from Item i where i.category =:category").setParameter("category",category);
            System.out.println(q.getResultList().size());
        }


        this.logs(log,"get all item in one category",false);
        if(type == null){
            return q.getResultList();
        }
        return  comparationist( type,q.getResultList());
    }

    public List<Item> getItemsPriceRange(int aux1,int aux2, String type, String partial){
        Query q;

        if(!partial.equals(""))
            q = em.createQuery("from Item i where i.name like : partial and i.price between :aux1 and :aux2").setParameter("aux1",aux1)
                    .setParameter("aux2",aux2).setParameter("partial",partial+"%");
        else
            q = em.createQuery("from Item i where i.price between :aux1 and :aux2").setParameter("aux1",aux1).setParameter("aux2",aux2);

        this.logs(log,"get item in a price range",false);
        if(type == null){
            return q.getResultList();
        }
        return  comparationist(type,q.getResultList());
    }

    public List<Item> getItemsCounty(String country, String partial,String type){
        Query q;


        if(!partial.equals("")){

            q = em.createQuery("from Item i where i.originCoiuntry like :country and i.name = :partial")
                    .setParameter("country",country).setParameter("partial",partial+"%");

        }
        else{
            q = em.createQuery("from Item i where i.originCoiuntry = :country").setParameter("country",country);
            System.out.println(q.getResultList().size());
        }
        this.logs(log,"get item in a country range",false);
        if(type == null){
            return q.getResultList();
        }
        return  comparationist(type,q.getResultList());
    }

    public List<Item> getItemsAfterDate(String date, String type,String partial){
        date = date + " 00:00:00.000";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date df = null;
        try {
            df = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Query q = null;
        if(!partial.equals("")){
            q = em.createQuery("from Item i where i.date >= :df and i.name = :partial").setParameter("df", df).setParameter("partial",partial+"%");
        }else{
            q = em.createQuery("from Item i where i.date >= :df").setParameter("df",df);
        }
        this.logs(log,"get item in a  date",false);
        if(type == null || type.equals("")){
            return q.getResultList();
        }
        return  comparationist(type,q.getResultList());
    }

    public List<Item> comparationist( String type, List<Item> p){
        System.out.println("Entrei");
        Query q = null;
        List<Item> finalitems = new ArrayList<Item>();
        if(type.equals("ascendingName")){
            q = em.createQuery("from Item i order by i.name ASC");
        }else if(type.equals("descendingName")){
            q = em.createQuery("from Item i order by i.name DESC");
        }else if(type.equals("ascendingPrice")){
            q = em.createQuery("from Item i order by i.price ASC");
        }else if(type.equals("descendingPrice")){
            q = em.createQuery("from Item i order by i.price DESC");
        }else if(type.equals("ascendingDate")){
            q = em.createQuery("from Item i order by i.date ASC");
        }else if(type.equals("descendingDate")){
            q = em.createQuery("from Item i order by i.date DESC");
        }
        List<Item> items1  = q.getResultList();
        //System.out.println("leeeets goooo" +  items1.get(0).getOriginCoiuntry() + p.get(0).getOriginCoiuntry());
        for(int i = 0; i< items1.size(); i++){
            for(int k = 0; k< p.size(); k++){
                if (items1.get(i).getName().equals(p.get(k).getName())) {
                    finalitems.add(items1.get(i));
                }
            }
        }
        return finalitems;
    }

    public List<Item> getItemsbyOrder(String order){
        System.out.println("entrei item");
        Query q = null;
        if(order.equals("Name")) {
            q = em.createQuery("from Item i order by i.name ASC");
        }else if(order.equals("ascendingPrice")){
            q = em.createQuery("from Item i order by i.price ASC");
        }else if(order.equals("descendingPrice")){
            q = em.createQuery("from Item i order by i.price DESC");
        }
        return  q.getResultList();
    }

    public User deleteItem(Item item,User user){
        Item i = em.find(Item.class,item.getItemId());
        User u = em.find(User.class,user.getUserId());

        if(i!=null){
            u.getItems().remove(i);
            em.merge(u);
            this.logs(log,"Item removed",false);
            return u;
        }

        this.logs(log,"Item not removed",false);
        return null;

    }

    public User chagedata(User user,Item item, String name, String country, String category, int price, Part file){

        Query q;
        Item u = em.find(Item.class, item.getItemId());
        User u1 = em.find(User.class, user.getUserId());
        boolean changed = false;


        if(!u.getName().equals(name)){

            u.setName(name);
            this.logs(log, "Item" + u.getItemId() + " name changed", false);
            changed = true;

        }

        if(price != u.getPrice()){

            u.setPrice(price);
            this.logs(log, "Item" + u.getItemId() + " price changed", false);
            changed = true;

        }

        if (country != null && !u.getOriginCoiuntry().equals(country)){

            u.setOriginCoiuntry(country);
            this.logs(log, "Item " + u.getItemId() + " country changed", false);
            changed = true;

        }

        if (category != null && !u.getCategory().equals(category)){

            u.setCategory(category);
            this.logs(log, "Item " + u.getItemId() + " category changed", false);
            changed = true;

        }

        if(!file.getSubmittedFileName().equals("")) {
            this.logs(log,file.getSubmittedFileName(),false);
            String[] aux_file = file.getSubmittedFileName().split("\\.");
            String file_type = aux_file[1];
            String directory = this.UploadDirItems + File.separator + Integer.toString(u.getItemId()) + "." + file_type;
            try {

                file.write(directory);
            } catch (FileAlreadyExistsException exist) {
                Random r = new Random();
                directory = this.UploadDirItems + File.separator + Integer.toString(u.getItemId()) + Integer.toString(r.nextInt()) + "." + file_type;
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
            em.merge(u);
            return u1;
        }
        else {
            return null;
        }
    }

    public boolean check_item(Item item1, Item item2){

        String aux = item1.getName();
        if(!item2.getName().equals(aux))
            return false;

        aux = item1.getCategory();
        if(!item2.getCategory().equals(aux))
            return false;

        aux = item1.getOriginCoiuntry();
        if(!item2.getOriginCoiuntry().equals(aux))
            return false;

        int aux2 = item1.getPrice();
        if(item2.getPrice() != aux2)
            return false;


        return true;
    }


}
