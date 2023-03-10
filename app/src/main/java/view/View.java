package view;

import java.util.List;
import java.util.Scanner;
import model.Member;

/**
Class that handles output.
*/  
public class View {

  private static final int one = 1;
  private static final int two = 2;
  private static final int three = 3;
  private static final int four = 4;
  private static final int five = 5;
  private static final int six = 6;
  private static final int seven = 7;
  private static final int eight = 8;
  private static final int nine = 9;
  private static final int ten = 10;
  private static final int eleven = 11;
  private static final int zero = 0;

  /**
  Options for the submenues, if you want to edit item
  it shows four options, these handle that to not 
  interfere with main menu options.
  */
  private static final int sub1 = 1;
  private static final int sub2 = 2;
  private static final int sub3 = 3;
  private static final int sub4 = 4;

  /**
  Makes all the enums required to make the gui and controller work with eachother (without making bad dependencies).
  */
  public enum Action { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, ZERO, SUB1, SUB2, SUB3, SUB4 
  }

  Scanner scan = new Scanner(System.in, "UTF-8");

  /**
  Displays deleted member message.
  */
  public void deletedaMember() {
    System.out.print("Member got succesfully deleted! \n");
  }

  /**
  Shows options for a member to change info about themselves.
  */  
  public void editMemberview(String name) {
    System.out.print("Member chosen: " 
                    + name 
                    +
                    "\nChoose Action: (1)Edit Name (2)Edit Email " 
                    +
                    "(3)Edit Number: ");
  }

  /**
  Gives option to the user to edit an item.
  */  
  public void editItemview(String name) {
    System.out.print("Item chosen: " 
                      + name
                      +
                    "\nChoose Action: (1)Edit Name (2)Edit Category (3)Edit Description (4)Edit Cost: ");
  }

  /**
  Takes a member and shows the item belonging to that member.
  */  
  public String showOwnedItem(model.Member member) {
    String items = "";
    for (int i = 0; i < member.getItems().size(); i++) {
      items += "\n Name: " 
        + member.getItem(i).getName() 
        + ", Description: "
        + member.getItem(i).getDescription() 
        + ", Category: "
        + member.getItem(i).getCategory()
        + ", Cost/day: "
        + member.getItem(i).getCost()
        + ", Creation date: "
        + member.getItem(i).getDate();
    } 
    return items;
  }

  /**
  Shows the borrowed items of a member.
  */  
  public String showBorrowedItem(model.Member member) {
    String items = "";
    for (int i = 0; i < member.returnContracts().size(); i++) {
      model.Contract contract = member.returnContracts().get(i);
      if (contract.getOwner().getName().equals(member.getName())) {
        items += "\n  Name: " 
          + contract.getItemName()
          + ", lent to: "
          + contract.getBorrowerName()
          + ", Starting Day: "
          + contract.getStartDate()
          + ", End Day: "
          + contract.getEndDate();
      } 
    }
    return items;
  }

  /**
  Show a members information.
  */  
  public void showMember(model.Member member) {

    System.out.println("\nName: "
        + member.getName()
         + ", Email: " + member.getEmail() 
         + ", Phone number: " + member.getPhone()
         + ", Credits: " + member.getCredits()
         + ", Owned items: " + member.getOwnedCount()
         + ", ID: " + member.getId()
         + ", Creation date: " + member.getDate()
         + showOwnedItem(member)
         + "\n  Items in contract (Both Active and Inactive):"
         + showBorrowedItem(member));

  }

  /**
  Error message for deleting item in contract with insufficient credits.
  */  
  public void errorDeleteItem() {
    System.out.println("ERROR: Contract was found on item, borrower can't be payed back.");
  }

  /**
  Prints deleted item inside of a contract.
  */  
  public void deletedItemContract() {
    System.out.println("Contract Found! Item was deleted and borrower was refunded!");
  }

  /**
  Prints deleted item.
  */  
  public void deletedItem() {
    System.out.println("Sucessfully deleted item!");
  }

  /**
  Input starting day message.
  */  
  public void putStartingDay() {
    System.out.print("Please input starting day: ");
  }

  /**
  Input ending day message.
  */  
  public void putEndDay() {
    System.out.print("Please input ending day: ");
  }

  /**
  Shows insufficient credits message.
  */  
  public void insufficientCredits() {
    System.out.println("ERROR! Not enough credits!");
  }

  /**
  Shows item already lent message.
  */  
  public void itemAlreadyLent() {
    System.out.println("Item Already Lent!");
  }

  /**
  Shows invalid input message.
  */  
  public void invalidInputview() {
    System.out.println("ERROR! Invalid Input");
  }

  /**
  Shows invalid date message.
  */  
  public void invalidDateInput() {
    System.out.println("ERROR! Invalid Date");
  }

  /**
  Warning message that a member will be deleted.
  */  
  public void deleteMemberview() {
    System.out.println("Warning!! You will delete the member you select!!");
  }

  /**
  Warning message that an item will be deleted.
  */  
  public void deleteItemview() {
    System.out.println("Warning!! You will delete the item you select!!");
  }

  /**
  Asks for member name message.
  */  
  public void askForName() {
    System.out.print("Please input member Name: ");
  }

  /**
  Asks for item name message.
  */  
  public String askForItemName() {
    System.out.print("Please input Item Name: ");
    return askForInput();
  }

  /**
  Asks for input.
  */  
  public String askForInput() {
    String input = scan.nextLine();
    return input;
  }

  public void closeInput() {
    scan.close();
  }

  /**
  Asks for description of an item message.
  */  
  public String askForDescription() {
    System.out.print("Please input Description: ");
    return askForInput();
  }

  /**
  Ask for category of an item message.
  */  
  public String askForCategory() {
    System.out.print("Please input Category: ");
    return askForInput();
  }

  /**
  Ask for item cost message.
  */  
  public String askForCost() {
    System.out.print("Please input Cost: ");
    return askForInput();
  }

  /**
  Ask for email message.
  */  
  public String askForEmail() {
    System.out.print("Please input Email: ");
    return askForInput();
  }

  /**
  Ask for phone number message.
  */  
  public String askForNumber() {
    System.out.print("Please input Phone number: ");
    return askForInput();
  }

  /**
  Email already exists message.
  */  
  public void emailAlreadyExistMessage() {
    System.out.println("Email already exists! Enter a new one!");
  }

  /**
  Phone number already exists message.
  */  
  public void phoneAlreadyExistMessage() {
    System.out.println("Phone number already exists!");
  }

  /**
  Edit item message.
  */  
  public void editItemMessage() {
    System.out.println("Select a member to select an item to edit!");
  }

  /**
  Select member message.
  */  
  public void selectMemberMessage() {
    System.out.print("Select member: ");
  }

  /**
  Select borrower message.
  */  
  public void selectBorrower() {
    System.out.println("Who should borrow the item?");
  }

  /**
  Shows the available options for the user to select.
  */  
  public void menuDisplay() {
    System.out.println("Enter 0 to exit application\n1. Create member \n2. Delete member"
                      + "\n3. Edit member\n4. Show member "
                      + "\n5. List all members in a simple way\n6. List all memvers in a verbose way"
                      + "\n7. Create item \n8. Delete item \n9. Change item info \n10. Create contract" 
                      + "\n11. Advance to next day  ");
  }

  /**
  Lists all members.
  */  
  public void listAllMembsSimple(List<Member> mem) {
    System.out.println("-------------------------------------------------------------------------");
    for (int i = 0; i < mem.size(); i++) {
      System.out.println(i + " | Name: " + mem.get(i).getName() 
          + " | Email: " + mem.get(i).getEmail() 
          + " | Credits: " + mem.get(i).getCredits() 
          + " | Owned items: " + mem.get(i).getOwnedCount() 
          + " | Day of creation: " + mem.get(i).getDate());
    }
  }

  /**
  Lists all items of a member.
  */  
  public void listAllItems(model.Member member) {
    System.out.println("-------------------------------------------------------------------------");
    for (int i = 0; i < member.getItems().size(); i++) {
      System.out.println(i + " | Name: " + member.getItem(i).getName() 
          + " | Category: " + member.getItem(i).getCategory() 
          + " | Description: " + member.getItem(i).getDescription() 
          + " | Cost per day: " + member.getItem(i).getCost());
    }
    System.out.print("-------------------------------------------------------------------------\n"
                    + "Please Select an Item From The List: ");
  }

  /**
  Prints out the current day.
  */  
  public void printDate(int date) {
    System.out.println("Day: " + date);
  }

  /**
  Gets action for main menu.
  */
  public Action getAction() {
    int inp = Integer.parseInt(askForInput());
    switch (inp) {
      case one:
        return Action.ONE;
      case two:
        return Action.TWO;
      case three:
        return Action.THREE;
      case four:
        return Action.FOUR;
      case five:
        return Action.FIVE;
      case six:
        return Action.SIX;
      case seven:
        return Action.SEVEN;
      case eight:
        return Action.EIGHT;
      case nine:
        return Action.NINE;
      case ten:
        return Action.TEN;
      case eleven:
        return Action.ELEVEN;
      case zero:
        return Action.ZERO;
      default:
        invalidInputview();
        return null;
    }
  }

  /**
  Gets action for submenues.
  */
  public Action getSubAction() {
    int inp = Integer.parseInt(askForInput());
    switch (inp) {
      case sub1:
        return Action.SUB1;
      case sub2:
        return Action.SUB2;
      case sub3:
        return Action.SUB3;
      case sub4:
        return Action.SUB4; 
      default:
        invalidInputview();
        return null;
    }
  }
}
