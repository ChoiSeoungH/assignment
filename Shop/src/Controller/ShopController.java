package Controller;

import Utils.FileManager;
import Utils.InputManger;
import dao.ItemDAO;
import dao.UserDAO;
import vo.Cart;

public class ShopController {

  ItemDAO idao;
  UserDAO udao;
  String log;

  void init() {
    idao = new ItemDAO();
    udao = new UserDAO();
    FileManager.loadFile(idao, udao);
  }

  public void run() {
    init();
    while (true) {
      printMainMenu();
      int menu = Utils.InputManger.getValue("메뉴 입력 >> ", 0, 4, 100);
      switch (menu) {
        case 0: //종료
          System.out.println("종료");
          return;
        case 1: //가입
          udao.joinUser();
          break;
        case 2: //탈퇴
          udao.quitUser();
          break;
        case 3: //로그인
          log = udao.login();
          if (log!=null) {
            printMemberMenu();
          }
          break;
        case 4: //로그아웃
          if (!isLogin()) continue;
          break;
        case 100: // 관리자
          printAdminMenu();
          break;
      }//eos

    }//eow
  }

  private boolean isLogin() {
    if (log==null) {
      System.out.println("로그인 후 이용");
      return false;
    }
    log = null;
    System.out.println("로그아웃 완료");
    return true;
  }

  private void printMainMenu() {
    System.out.println("[1.가입] [2.탈퇴] [3.로그인] [4.로그아웃]" + "\n[100.관리자] [0.종료] ");
  }

  private void printAdminMenu() {
    System.out.println("[1.아이템관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [0.뒤로가기] ");
    int menu = InputManger.getValue("메뉴 입력 >> ", 0, 4);
    switch (menu) {
      case 0:
        System.out.println("뒤로가기");
        return;
      case 1: //아이템
        break;
      case 2: //카테고리
        break;
      case 3: //장바구니
        break;
      case 4: //유저관리
        break;
    }
  }

  private void printMemberMenu() {
    outer : while (true) {
      System.out.println(log+"님 로그인중");
      System.out.println("[1.쇼핑] [2.장바구니목록] [0.뒤로가기]");
      int menu = InputManger.getValue("메뉴 입력 >> ", 0, 4);
      switch (menu) {
        case 0:
          System.out.println("뒤로가기");
          return;
        case 1://쇼핑
          if (printCartMenu()) break;
          break;
        case 2://장바구니
          udao.printMyCart(log);
          break;
      }
    }
  }

  private boolean printCartMenu() {
    System.out.println("[1.내 장바구니] [2.삭제] [3.구입] [0.뒤로가기]");
    int menu = InputManger.getValue("메뉴 입력 >> ", 0, 4);
    switch (menu) {
      case 0:
        System.out.println("뒤로가기");
        return true;
      case 1: //내장바구니
        break;
      case 2: //삭제
        break;
      case 3: //구입
        break;
    }
    return false;
  }

}
