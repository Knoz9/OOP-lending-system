package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/**
Contract class that is a blueprint of how the
contracts are designed.
*/

public class Contract {
  private Member owner;
  private Member borrower;
  private Item item;
  private int date;
  private int endDate;

  /**
  Constructor for the contract objects.
  */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Contract has a copy of the members")
  public Contract(Member owner, Member borrower, Item item, int date, int enddate) {
    this.owner = owner;
    this.borrower = borrower;
    this.item = item;
    this.date = date;
    this.endDate = enddate;
    
  }

  /**
  Returns the owners.
  */
  public Member getOwner() {
    return new Member(owner.getName(), owner.getEmail(), owner.getPhone(), owner.getId(), owner.getDate());
  }

  /**
  Returns the owners name.
  */
  public String getOwnerName() {
    return owner.getName();
  }

  /**
  Returns the borrower.
  */
  public Member getBorrower() {
    return new Member(borrower.getName(), borrower.getEmail(),
       borrower.getPhone(), borrower.getId(), borrower.getDate());
  }

  /**
  Returns the borrowers name.
  */
  public String getBorrowerName() {
    return borrower.getName();
  }

  /**
  Returns the item name.
  */
  public String getItemName() {
    return item.getName();
  }

  /**
  Returns the starting date of a contract.
  */
  public int getStartDate() {
    return date;
  }

  /**
  Returns the ending date of a contract.
  */
  public int getEndDate() {
    return endDate;
  }

  /**
  Returns the item.
  */
  public Item getItem() {
    return new Item(item.getName(), item.getCategory(), item.getDescription(), item.getCost(), item.getDate());
  }

  /**
  Returns the item cost.
  */
  public int getCost() {
    return item.getCost();
  }
}
