package model;

import java.util.ArrayList;

/**
Class that is a blueprint of how a member is designed.
*/
public final class Member {
  private String name;
  private String email;
  private String phoneNumber;
  private String memberId;
  private int date;
  private int ownedCount;
  private int credits;
  private ArrayList<Item> itemList = new ArrayList<>();
  private ContractList contractList = new ContractList();

  /**
  Constructor for the member object.
  */
  protected Member(String name, String email, String phoneNumber, String memberId, int date) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.memberId = memberId;
    this.credits = 0;
    this.ownedCount = 0;
    this.date = date;
    
  }

  protected void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public int getDate() {
    return this.date;
  }
  
  protected void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  protected void setPhone(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhone() {
    return this.phoneNumber;
  }

  protected void setCredits(int credits) {
    this.credits = credits;
  }

  protected void addCredits(int credit) {
    this.credits += credit;
  }

  protected void deductCredits(int credit) {
    this.credits -= credit;
  }

  public int getCredits() {
    return this.credits;
  }

  public String getId() {
    return this.memberId;
  }

  protected void setId(String id) {
    this.memberId = id;
  }

  /**
  Adds an item to a member and awards 100 credits,
  and number of owned items is increased by one.
  */
  public void addItem(String name, String category, String description, int cost, int date) { 
    createItem(name, category, description, cost, date);
    ownedCount += 1;
    credits += 100;
  }

  protected void addItem(Item item) {
    itemList.add(item);
  }

  public Item getItem(int i) {
    return itemList.get(i);
  }

  public ArrayList<Item> getItems() {
    return new ArrayList<Item>(itemList);
  }
  
  public int getOwnedCount() {
    return ownedCount;
  }

  protected void deleteItem(Item item) { 
    itemList.remove(item);
    ownedCount -= 1;
  }

  public boolean createContract(Member owner, Member borrower, Item item, int startDate, int endDate) {
    return contractList.createContract(owner, borrower, item, startDate, endDate);
  }

  protected void createItem(String itemName, String category, String itemDescription, int cost, int date) {
    Item item = new Item(itemName, category, itemDescription, cost, date);
    addItem(item);
  }
  
  public Contract getContract(int contract) {
    return contractList.getContract(contract);
  }

  public void addContract(Member owner, Member borrower, Item item, int startDate, int endDate) { 
    contractList.addContract(owner, borrower, item, startDate, endDate);
  }

  public ArrayList<Contract> returnContracts() {
    return contractList.getContracts();
  }

  protected void removeContract(Contract contract) { 
    contractList.removeContract(contract);
  }

  /**
  This edits the email from a specific member, it checks where that member is in the list to do so.
  */
  public boolean editItemName(Item item, String name) {
    for (int i = 0; i < getItems().size(); i++) {
      if (getItem(i).equals(item)) {
        itemList.get(i).setName(name);
        return true;
      }
    }
    return false;
  }

  /**
  Same as above, just for Description.
  */
  public boolean editItemDescription(Item item, String desc) {
    for (int i = 0; i < getItems().size(); i++) {
      if (getItem(i).equals(item)) {
        itemList.get(i).setDescription(desc);
        return true;
      }
    }
    return false;
  }

  /**
  Same as above, just for Cost.
  */
  public boolean editItemCost(Item item, int cost) {
    for (int i = 0; i < getItems().size(); i++) {
      if (getItem(i).equals(item)) {
        itemList.get(i).setCost(cost);
        return true;
      }
    }
    return false;
  }

  /**
  Same as above, just for Category.
  */
  public boolean editItemCategory(Item item, String cat) {
    for (int i = 0; i < getItems().size(); i++) {
      if (getItem(i).equals(item)) {
        itemList.get(i).setCategory(cat);
        return true;
      }
    }
    return false;
  }
}