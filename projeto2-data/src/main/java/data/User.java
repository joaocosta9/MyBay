package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;
    private String password;
    private String email;
    private String name;
    private String country;
    private String image_path;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Item> Items;

    public User(String username, String password, String email, String name, String country,String image_path) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.country = country;
        this.image_path = image_path;
    }

    public User(){

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setItems(List<Item> items) {
        Items = items;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void addItem(Item item){
        this.Items.add(item);
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public List<Item> getItems() {
        return Items;
    }

    public String getImage_path() {
        return image_path;
    }
}
