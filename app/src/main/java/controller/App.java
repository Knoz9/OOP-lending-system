package controller;

import model.MemberList;
import view.View;

/**
 Main class that runs the application.
*/
public class App {

  /**
 Creates new controller object and then calls, 
  hard coded members and runs the menu.
  */
  public static void main(String[] args) {
    View view = new View();
    MemberList model = new MemberList();

    Controller controller = new Controller(view, model);
    controller.loadMembers();
    controller.menu();
  }   
}
