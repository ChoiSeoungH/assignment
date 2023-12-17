package dao;

import vo.Cart;
import vo.User;

import java.util.ArrayList;

public class UserDAO {
  private final ArrayList<Cart> cartList;
  private final ArrayList<User> userList;

  public UserDAO() {
    userList = new ArrayList<>();
    cartList = new ArrayList<>();
  }

  public void addUserFromData(String userData) {
    String[] temp = userData.split("/n");
    for (String s : temp) {
      String[] info = s.split("/");
      userList.add(new User(info[0], info[1], info[2]));
    }
  }

  public void addCartFromData(String cartData) {
    String[] temp = cartData.split("/n");
    for (String s : temp) {
      String[] info = s.split("/");
      cartList.add(new Cart(info[0], info[1]));
    }
  }

  public String getDataFromUserList() {
    String data = "";
    for (User u : userList) {
      data+= u.getId()+"/"+u.getPw()+"/"+u.getName()+"\n" ;
    }
    return data;
  }

  public String getDataFromCartList() {
    String data = "";
    for (Cart c : cartList) {
      data+= c.getUserId()+"/"+c.getItemName()+"\n";
    }
    return data;
  }
}
