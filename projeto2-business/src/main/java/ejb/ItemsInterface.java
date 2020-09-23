package ejb;

import data.Item;
import data.User;

import javax.ejb.Local;
import javax.servlet.http.Part;
import java.util.List;
import java.util.logging.Logger;

@Local
public interface ItemsInterface {

    List<Item> getAllItems(String partial,String type);

    List<Item> getItemsCategory(String category, String type, String partial);

    List<Item> getItemsPriceRange(int aux1,int aux2, String type, String partial);

    List<Item> getItemsCounty(String country, String type,String partial);

    List<Item> getItemsbyOrder(String order);

    List<Item> getItemsAfterDate(String date, String partial,String type);

    User chagedata(User user,Item item, String name, String country, String category, int price, Part file);

    User deleteItem(Item item,User user);
}
