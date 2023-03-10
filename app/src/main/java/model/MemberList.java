package model;

import java.util.ArrayList;
import java.util.Random;

/**
Class to handle a list of members.
*/
public class MemberList implements Persistence {
  private static final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String lowerCase = upperCase.toLowerCase();
  private static final String alphas = upperCase + lowerCase;
  private static final String digits = "0123456789";
  private ArrayList<Member> memberList = new ArrayList<>();
  private Random random = new Random();

  /**
  Generates a unique alpha numeric id for each member.
  Line 34 also checks if an id already exists and 
  creates new one if that is the case.
  */
  private String generateId() {
    
    while (true) {
      boolean isUnique = true;
      String id = "";
      for (int i = 0; i < 3; i++) {
        int rand = random.nextInt(alphas.length() - 1);
        String temp = Character.toString(alphas.charAt(rand));
        rand = random.nextInt(digits.length() - 1);
        String temp2 = Character.toString(digits.charAt(rand));
        id += temp + temp2;
      }
      for (int i = 0; i < memberList.size(); i++) {
        if (memberList.get(i).getId().equals(id)) {
          isUnique = false;
        }
      }
      if (isUnique) {
        return id;
      }
    }
  }

  /**
  Checks if that email exists. Is used in Memlist to make sure no duplicates.
  Is also used in controller to give the user nicer GUI (but doesn't rely on controller).
  */
  public boolean emailChecker(String email) {
    for (int i = 0; i < memberList.size(); i++) {
      if (email.equals(memberList.get(i).getEmail())) {
        return false;
      }
    }
    return true;
  }

  /**
  Same as above, just for phone.
  */
  public boolean phoneChecker(String phone) {
    for (int i = 0; i < memberList.size(); i++) {
      if (phone.equals(memberList.get(i).getPhone())) {
        return false;
      }
    }
    return true;
  }

  /**
  Creates a member and adds it to the list.
  */
  public void createMember(String name, String email, String phoneNumber, int date) {
    if (emailChecker(email) && phoneChecker(phoneNumber)) {
      Member member = new Member(name, email, phoneNumber, generateId(), date);
      addMember(member);
    }
    
  }

  public void deleteMember(Member member) {
    memberList.remove(member);
  }

  /**
  Adds a member to the list.
  */
  private void addMember(Member member) {
    memberList.add(member);
  }

  public ArrayList<Member> getMembers() {
    return new ArrayList<Member>(memberList);
  }

  public int getSize() {
    return memberList.size();
  }

  /**
  Edits a member (based on the member object) so it needs to go through the list to find the correct member.
  */
  public boolean editMemberName(Member member, String name) {
    for (int i = 0; i < getMembers().size(); i++) {
      if (memberList.get(i).equals(member)) {
        memberList.get(i).setName(name);
        return true;
      }
    }
    return false;
  }

  /**
  Same as above, just for email. Also has the duplicate checker.
  */
  public boolean editMemberEmail(Member member, String email) {
    for (int i = 0; i < getMembers().size(); i++) {
      if (memberList.get(i).equals(member) && emailChecker(email)) {
        memberList.get(i).setEmail(email);
        return true;
      }
    }
    return false;
  }

  /**
  Same as above, just for phone. Also has the duplicate checker.
  */
  public boolean editMemberPhone(Member member, String phone) {
    for (int i = 0; i < getMembers().size(); i++) {
      if (memberList.get(i).equals(member) && phoneChecker(phone)) {
        memberList.get(i).setPhone(phone);
        return true;
      }
    }
    return false;
  }

  /**
  Creates hard coded members. Implementins persistence interface.
  */
  public void codedMembers() {
    createMember("Tom", "tom@gmail.com", "0771", 0);
    memberList.get(0).setId("a1b2c3");
    memberList.get(0).addItem("Glock-18", "Weapon", "Semi auto, with 12 clip mag", 50, 0);
    memberList.get(0).addItem("Toy car", "Toy", "Fun toy car for children", 10, 0);

    createMember("Alan", "Alan@gmail.com", "0896", 0);
    memberList.get(1).setId("P0W2J1");
    

    createMember("Nigel", "Nigel@gmail.com", "0445", 0);
    memberList.get(2).setId("B4a2X1");
    memberList.get(2).addItem("Star Wars", "Movie", "Best movie of all time, to enjoy with anyone", 5, 0);

    memberList.get(0).addContract(memberList.get(0), memberList.get(2), memberList.get(0).getItem(1), 5, 7);

    memberList.get(2).setCredits(100);
    memberList.get(0).setCredits(500);  
    memberList.get(1).setCredits(100);
  }

  /**
  Checks and deletes the item from the database (correctly, by checking contract and dates).
  */
  public boolean deleteItem(int date, Member member, Item item) {
    for (int i = 0; i < member.returnContracts().size(); i++) { 
      // Goes through all contracts
      if (member.returnContracts().get(i).getStartDate() >= date) {
        Contract contract = member.returnContracts().get(i);
        // Checks if the item is in a future contract
        if (item.getName().equals(
              contract.getItem().getName())) { 
          // Checks if the item is the one trying to be deleted
          for (int x = 0; x < getMembers().size(); x++) {
            if (memberList.get(x).getName().equals(contract.getBorrowerName())) {
              memberList.get(x).addCredits(contract.getCost() 
                  * (contract.getEndDate() - contract.getStartDate() + 1));
              member.deductCredits(contract.getCost() 
                  * (contract.getEndDate() - contract.getStartDate() + 1));
              member.deleteItem(item);
              member.removeContract(contract);
              return true;
            }
          }
        }
      }
    }
    member.deleteItem(item);
    return false;
  }

  /**
  It creates a new contract (correctly, by checking if its already in a contract and if dates collide).
  */
  public boolean initiateContract(Item item, int startDate, int endDate) {
    for (int i = 0; i < getSize(); i++) { 
      // Goes through all contract tied to the members.
      for (int y = 0; y < memberList.get(i).returnContracts().size(); y++) { 
        // Checks if any of the contracts have the same item as the item we are trying to lend.
        if (memberList.get(i).getContract(y).getItem().getName().equals(item.getName())) { 
          // If so, it checks if the day period clashes with the day period in the contract,
          // using two for loops to iterate through all possibilities.
          for (int x = startDate - 1; x < endDate; x++) { 
            for (int z = memberList.get(i).getContract(y).getStartDate() - 1; 
                 z < memberList.get(i).getContract(y).getEndDate(); z++) {
              if (x == z) {
                return false;
              }
            }
          }
        }
      }
    }
    return true;
  }
}
