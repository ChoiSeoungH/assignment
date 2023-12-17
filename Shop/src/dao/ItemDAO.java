package dao;

import Utils.InputManger;
import vo.Item;

import java.util.ArrayList;

public class ItemDAO {
  private final ArrayList<Item> itemList;

  public ItemDAO() {
    itemList = new ArrayList<>();
  }

  public ArrayList<Item> getItemList() {
    return itemList;
  }

  public void addItemFromData(String itemData) {
    String[] temp = itemData.split("\n");
    for (String s : temp) {
      String[] info = s.split("/");
      itemList.add(new Item(info[0], Integer.parseInt(info[1]), info[2]));
    }
  }

  public String getDataFromList() {
    String data = "";
    for (Item i : itemList) {
        data+= i.getName()+"/"+i.getPrice()+"/"+i.getCategory()+"\n" ;
    }
    return data;
  }

  public void printItem() {
    for (Item i : itemList) {
      System.out.println(i);
    }
  }

  public void buyItem(String log, UserDAO udao) {
    printItem();
    String item = InputManger.getValue("구매할 상품 >> ");
    if (!hasItem(item)) {
      System.out.println("상품명을 확인해주세요");
      return;
    }
    udao.addItemtoMyCart(log,item);
    System.out.println("구매완료");

  }

  private boolean hasItem(String item) {
    for (Item i : itemList) {
      if (i.getName().equals(item)) {
        return true;
      }
    }
    return false;
  }
}
