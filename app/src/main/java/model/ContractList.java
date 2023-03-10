package model;

import java.util.ArrayList;

/**
Class that creates a list to store all contracts.
*/
public class ContractList {
  private ArrayList<Contract> contractList = new ArrayList<>();

  protected ArrayList<Contract> getContracts() {
    return new ArrayList<Contract>(contractList);
  }

  /**
  Revoke is essentially a reversed contract i.e you pay back the borrower, but the contract then gets quickly deleted.
  */
  protected void removeContract(Contract contract) {
    contractList.remove(contract);
  }
  
  /**
  Adds a contract.
  */
  protected void addContract(Member owner, Member borrower, Item item, int date, int enddate) {
    Contract contract = new Contract(owner, borrower, item, date, enddate);
    contractList.add(contract);
    
  }

  /**
  Creates a contract with a start and end date,
  it also checks if the borrower has enough credits.
  If any stage fails it returns false, else true.
  */
  protected boolean createContract(Member owner, Member borrower, Item item, int date, int enddate) {
    int totalcost;
    if (enddate - date < 1) {
      totalcost = item.getCost();
    } else {
      totalcost = item.getCost() * (enddate - date + 1); // +1 because we want the total days (i.e 1-3 is three days)
    }
    if (borrower.getCredits() >= totalcost) {
      Contract contract = new Contract(owner, borrower, item, date, enddate);
      borrower.deductCredits(totalcost);
      owner.addCredits(totalcost);
      contractList.add(contract);
      return true;
    }
    return false;
  }

  protected Contract getContract(int pos) {
    return contractList.get(pos);
  }
}
