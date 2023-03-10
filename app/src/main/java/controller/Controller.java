package controller;


import model.Item;
import model.Member;
import model.MemberList;
import view.View;

/**
 Controller that communicates with view and model.
*/
public class Controller {
  private View view;
  private MemberList mem;
  int date = 0;

  protected Controller(View view, MemberList mem) {
    this.view = view;
    this.mem = mem;
  }

  /**
   Asks for member name.
  */
  private String askForName() {
    view.askForName();
    String name = view.askForInput();
    return name;
  }
  
  /**
   Asks for email and checks with model for duplicates.
  */
  private String askForEmail() {
    String email = view.askForEmail();
    if (mem.emailChecker(email)) {
      return email;
    } else {
      view.emailAlreadyExistMessage();
      return email = askForEmail();
    } 
  }

  /**
   Asks for phone number and checks with model for duplicates.
  */
  private String askForPhone() {
    String phone = view.askForNumber();
    if (mem.phoneChecker(phone)) {
      return phone;
    } else {
      view.phoneAlreadyExistMessage();
      return phone = askForPhone();
    } 
  }

  /**
   Creates members by asking for different inputs.
  */
  private void createMember() {
    mem.createMember(askForName(), askForEmail(), askForPhone(), date);
  } 

  /**
   Hard coded code to create members and items.
  */
  protected void loadMembers() {
    mem.codedMembers();
  }

  /**
   Hard coded code to create members and items.
  */
  private Member selectUser() {
    view.listAllMembsSimple(mem.getMembers());
    view.selectMemberMessage();
    int input = Integer.parseInt(view.askForInput());
    Member member = mem.getMembers().get(input);
    return member;
  }

  /**
   Prints out the date by calling a method from view.
  */
  private void showDate() {
    view.printDate(date);
  }

  /**
   Prints out the members by calling a method from view.
  */
  private void showMember(Member member) {
    view.showMember(member);
  }

  /**
   Edits a user by getting the member name, then a few options is shown to specify what to edit.
  */
  private void editUser() {
    Member member = selectUser();
    view.editMemberview(member.getName());
    view.View.Action input = view.getSubAction();
    if (input == View.Action.SUB1) {
      mem.editMemberName(member, askForName());
    } else if (input == View.Action.SUB2) {
      mem.editMemberEmail(member, askForEmail());
    } else if (input == View.Action.SUB3) {
      mem.editMemberPhone(member, askForPhone());
    } else {
      view.invalidInputview();
      editUser();
    }
  }

  /**
   Lists all members.
  */
  private void showMembs() {
    view.listAllMembsSimple(mem.getMembers());
  }

  /**
   Lists the members in a verboseway.
  */
  private void showMembersVerbose() {
    for (int i = 0; i < mem.getSize(); i++) {
      showMember(mem.getMembers().get(i));
    }
  }

  /**
   Delete message is shown, then the deleter is deleted by index.
  */
  private void deleteUser() {
    mem.deleteMember(selectUser());
    view.deletedaMember();
  }

  /**
   A member is taken as argument to add an item to that member.
  */
  private void addItem(model.Member member) {
    member.addItem(view.askForItemName(), view.askForCategory(), 
        view.askForDescription(), Integer.parseInt(view.askForCost()), date);
  }

  /**
   Delete item message is shown, then the item is deleted my selecting the member and then the item to delete.
   This also checks if the item is in a future contract. If it is, it tries to revoke it. 
   If it can revoke (The owner has enough credits to give back) the item gets deleted and revokes contract
   If the owner cannot pay back, it wont be deleted.
   If its not in a contract, it gets deleted normally
  */
  private void deleteItem() {
    view.deleteItemview();
    view.listAllMembsSimple(mem.getMembers());
    int input = Integer.parseInt(view.askForInput());
    Member member = mem.getMembers().get(input);
    view.listAllItems(mem.getMembers().get(input));
    int input2 = Integer.parseInt(view.askForInput());
    Item item = mem.getMembers().get(input).getItem(input2);
    if (mem.deleteItem(date, member, item)) {
      view.deletedItemContract();
    } else {
      view.deletedItem();
    }
  }

  /**
   Selects an item by first specifying which user.
  */
  private Item selectItem(Member member) {
    view.listAllItems(member);
    int pos = Integer.parseInt(view.askForInput());
    Item item = member.getItem(pos);
    return item;
  }

  /**
   Edit message is shown, then the item is edited by selecting a user and the item to be edited.
  */
  private void editItem() {
    Member member = selectUser();
    Item item = selectItem(member);
    view.editItemview(item.getName());
    view.View.Action input = view.getSubAction();
    if (input == View.Action.SUB1) {
      member.editItemName(item, view.askForItemName());
    } else if (input == View.Action.SUB2) {
      member.editItemCategory(item, view.askForCategory());
    } else if (input == View.Action.SUB3) {
      member.editItemDescription(item, view.askForDescription());
    } else if (input == View.Action.SUB4) {
      member.editItemCost(item, Integer.parseInt(view.askForCost()));
    } else {
      view.invalidInputview();
      editItem();
    }
  }

  /**
   Starts a contract. An owner and borrower is selected then an item and then the period of time. 
   If the date is invalid it returns false.
  */
  private boolean initiateContract(Member borrower, Member owner) {
    view.selectBorrower();
    view.listAllItems(owner);
    int input = Integer.parseInt(view.askForInput());
    view.putStartingDay();
    int startDay = Integer.parseInt(view.askForInput());
    view.putEndDay();
    int endDay = Integer.parseInt(view.askForInput());
    Item item = owner.getItem(input);

    if (startDay < date || endDay < date) {
      view.invalidDateInput();
      return false;
    }
    System.out.println(startDay);
    // Goes through all members in memberlist.
    if (mem.initiateContract(item, startDay, endDay)) {
      if (owner.getName().equals(borrower.getName())) { 
        // if the owner is borrowing his own item, skip deducting balance and return true!
        owner.addContract(owner, borrower, item, startDay, endDay);
        return true;
      }
      if (borrower.createContract(owner, borrower, item, startDay, endDay) == false) {
        view.insufficientCredits();
        return false;
      }
      // We create a duplicate contract for the owner to have (copy)
      owner.addContract(owner, borrower, item, startDay, endDay); 
      return true;
    }
    view.itemAlreadyLent();
    return false;
  }

  /**
   A menu that is shown when the program is run. 
   Shows different options for the user to use.
  */
  protected void menu() {
    boolean program = true;
    while (program) {
      
      showDate();
      view.menuDisplay();

      try {
        view.View.Action input = view.getAction(); 
        if (input == View.Action.ONE)  {
          createMember(); 
            
        } else if (input == View.Action.TWO) {
          deleteUser();
            
        } else if (input == View.Action.THREE) {
          editUser();
            
        } else if (input == View.Action.FOUR) {
          showMember(selectUser());
            
        } else if (input == View.Action.FIVE) {
          showMembs();
        
        } else if (input == View.Action.SIX) {
          showMembersVerbose();
            
        } else if (input == View.Action.SEVEN) {
          addItem((selectUser()));
            
        } else if (input == View.Action.EIGHT) {
          deleteItem();
            
        } else if (input == View.Action.NINE) {
          editItem();
            
        } else if (input == View.Action.TEN) {
          view.selectBorrower();
          initiateContract(selectUser(), selectUser());
            
        } else if (input == View.Action.ELEVEN) {
          date++;
            
        } else if (input == View.Action.ZERO) {
          view.closeInput();
          program = false;
          break;

        } else {
          program = false;
          break;
        }   
           
      } catch (Exception e) {
        view.invalidInputview();
        program = false;
      } 
    }
  }
}