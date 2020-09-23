package ejb;

import data.Item;
import data.User;

import javax.ejb.Local;
import javax.servlet.http.Part;
import java.util.List;
import java.util.logging.Logger;

@Local
public interface UsersInterface {

    boolean register(String username, String password, String email, String name, String country);

    User login(String email, String password);

    User changeData(User user , String username, String password, String email, String name, String country, Part file);

    User addItem(User user, String item_name, String country, String category, Part file,int price);

    boolean ckeckImageVericity(User user);

    boolean deleteUser(User user);
}
