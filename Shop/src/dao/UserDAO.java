package dao;

import Utils.InputManger;
import vo.Cart;
import vo.User;

import java.util.ArrayList;
import java.util.Arrays;

public class UserDAO {
  private final ArrayList<Cart> cartList;
  private final ArrayList<User> userList;
  public String login;

  public UserDAO() {
    userList = new ArrayList<>();
    cartList = new ArrayList<>();
  }

  public ArrayList<Cart> getCartList() {
    return cartList;
  }

  public ArrayList<User> getUserList() {
    return userList;
  }

  public void addUserFromData(String userData) {
    String[] temp = userData.split("\n");
    for (String s : temp) {
      String[] info = s.split("/");
      userList.add(new User(info[0], info[1], info[2]));
    }
//    printUser(); //저장 test
  }

  public void addCartFromData(String cartData) {
    String[] temp = cartData.split("\n");
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

  public void joinUser() {
    String id = InputManger.getValue("id >> ");
    if (isExistId(id)!=-1) {
      System.out.println("이미 존재하는 아이디입니다.");
      return;
    }
    String pw = InputManger.getValue("pw >> ");
    String name = InputManger.getValue("name >> ");
    userList.add(new User(id, pw, name));
    System.out.println(userList.get(userList.size()-1));
    System.out.println("회원가입 완료");
  }

  private int isExistId(String id) {
    int idx=0;
    for (User u : userList) {
      if (u.getId().equals(id)) {
        return idx;
      }
      idx+=1;
    }
    return -1;
  }

  public void quitUser() {
    String id = InputManger.getValue("id >> ");
    int delIdx=isExistId(id);
    if (delIdx==-1) {
      System.out.println("아이디가 존재하지 않습니다.");
      return;
    }
    String pw = InputManger.getValue("pw >> ");
    if (!userList.get(delIdx).getPw().equals(pw)) {
      System.out.println("비밀번호가 일치 하지 않습니다");
      return;
    }
    System.out.println(userList.get(delIdx));
    userList.remove(delIdx);
    System.out.println("회원탈퇴 완료");
  }

  public void printUser(){
    for (User u : userList) {
      System.out.println(u);
    }
  }

  public String login() {
    String id = InputManger.getValue("id >> ");
    int delIdx=isExistId(id);
    if (delIdx==-1) {
      System.out.println("아이디가 존재하지 않습니다.");
      return null;
    }
    String pw = InputManger.getValue("pw >> ");
    if (!userList.get(delIdx).getPw().equals(pw)) {
      System.out.println("비밀번호가 일치 하지 않습니다");
      return null;
    }
    System.out.println("로그인 완료");
    return id;
  }
}
