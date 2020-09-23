package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    private String name;
    private String category;
    private String originCoiuntry;
    private String image_path;
    private int price;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Item(String name, String category, String originCoiuntry, User user,int price, Date date) {
        this.name = name;
        this.category = category;
        this.originCoiuntry = originCoiuntry;
        this.user = user;
        this.price = price;
        this.date = date;
    }

    public Item(){

    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setOriginCoiuntry(String originCoiuntry) {
        this.originCoiuntry = originCoiuntry;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getItemId() {
        return this.itemId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getOriginCoiuntry() {
        return originCoiuntry;
    }

    public String getImage_path() {
        return image_path;
    }

    public User getUser() {
        return user;
    }

    public int getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

