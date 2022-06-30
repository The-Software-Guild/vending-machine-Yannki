package ui;
import dao.VendingMachineDao;
import dao.VendingMachineDaoImpl;
import dto.Drink;
import dto.Item;
import dto.Snack;
import main.java.ui.UserIO;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

//TODO: improve asking for funds
//TODO: create a basic vending machine (get funds, choose item, remove item from vending machine, change)

public class VendingMachineView {
     private UserIO io;
     private VendingMachineDao dao;

     public VendingMachineView(UserIO io, VendingMachineDao dao) {
         this.io = io;
         this.dao = dao;
     }

     public void showInventory(){
          Map<Item, Integer> inventory = dao.getAllItems();
          Map<Integer, Item> products = dao.getProducts();
          System.out.println("==============Drinks===============");
          products.entrySet().stream()
                   .filter(x -> x.getValue() instanceof Drink)
                   .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()))
                   .forEach((key, value) -> {io.print(key + ": " + value.display() + " Amount: " + inventory.get(value));});
          System.out.println("==============Snacks===============");
          products.entrySet().stream()
                  .filter(x -> x.getValue() instanceof Snack)
                  .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()))
                  .forEach((key, value) -> {io.print(key + ": " + value.display() + " Amount: " + inventory.get(value));});
     }
     public BigDecimal askForFunds () {
          return dao.addMoney(new BigDecimal(io.readString("Please add funds to continue")));
     }

     public String askForSelection() {
          io.print(dao.getFunds().toString());
          return io.readString("Please select Item you want by id or type 'exit' to leave");
     }


     public void displayTransaction(int id) {
          Item item = dao.getItem(id);
          BigDecimal change = dao.change(item.getCost());
          dao.removeItem(item);
          io.print( "Item cost: " + item.getCost() + " Balance: " + change);
          io.readString("Enter to continue");
     }

     public void displayExitMessage() {
          BigDecimal change = dao.change(new BigDecimal("0"));
          io.print("Change: " + change + ". Thank you!");
     }


}
