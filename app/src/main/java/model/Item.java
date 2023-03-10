package model;

/**
Class that is a blueprint of how items are designed.
*/
public class Item {
  private String name;
  private String category;
  private String description;
  private int cost;
  private int date;

  /**
  Constructor for the item object.
  */  
  protected Item(String name, String category, String description, int cost, int date) { 
    this.name = name;
    this.category = category;
    this.description = description;
    this.cost = cost;
    this.date = date;
  
  }

  /**
  Sets the item name.
  */  
  protected void setName(String name) {
    this.name = name;
  }

  /**
  Gets the item name.
  */  
  public String getName() {
    return this.name;
  }

  /**
  Gets the date.
  */
  public int getDate() {
    return this.date;
  }

  /**
  Sets the category of an item.
  */
  protected void setCategory(String category) {
    this.category = category;
  }

  /**
  Returns the category of an item.
  */
  public String getCategory() {
    return this.category;
  }

  /**
  Sets the description of an item.
  */
  protected void setDescription(String description) {
    this.description = description;
  }

  /**
  Gets the description of an item.
  */
  public String getDescription() {
    return this.description;
  }

  /**
  Sets the cost of an item.
  */
  protected void setCost(int cost) {
    this.cost = cost;
  }

  /**
  Returns the cost of an item.
  */
  public int getCost() {
    return this.cost;
  }
}