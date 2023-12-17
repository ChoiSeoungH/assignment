package dao;

import vo.Item;

import java.util.ArrayList;

public class ItemDAO {
  private final ArrayList<Item> itemList;

  public ItemDAO() {
    itemList = new ArrayList<>();
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

}
