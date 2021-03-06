package ui;
import dao.VendingMachineDao;
import dao.VendingMachineDaoPersistenceException;
import dto.Coin;
import dto.Drink;
import dto.Item;
import dto.Snack;
import service.ChangeServiceLayer;
import service.InsufficientFundsException;
import service.NoItemInventoryException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class VendingMachineView {
     private UserIO io;
     private VendingMachineDao dao;
     private ChangeServiceLayer service;

     public VendingMachineView(UserIO io, VendingMachineDao dao, ChangeServiceLayer service) {
         this.io = io;
         this.dao = dao;
         this.service = service;
     }

     public void showInventory(){
          Map<Integer, Item> inventory = dao.getInventory();
          System.out.println("==============Drinks===============");
          inventory.entrySet().stream()
                   .filter(x -> x.getValue() instanceof Drink)
                   .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()))
                   .forEach((key, value) -> {io.print(key + ": " + value.display());});
          System.out.println("==============Snacks===============");
          inventory.entrySet().stream()
                  .filter(x -> x.getValue() instanceof Snack)
                  .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()))
                  .forEach((key, value) -> {io.print(key + ": " + value.display());});
     }
     public BigDecimal askForFunds () {
          return service.addMoney(io.readBigDecimal("Please add funds to continue"));
     }

     public String askForSelection() {
          io.print(dao.getFunds().toString());
          return io.readString("Please select Item you want by id or type 'exit' to leave");
     }


     public void displayTransaction(int id) {
          try {
               Item item = service.removeItem(id);
               BigDecimal change = service.transaction(item.getCost());
               BigDecimal quarters = Coin.changeQuarters(change);
               change = change.subtract(quarters.multiply(Coin.QUARTER.getValue()));
               BigDecimal nickles = Coin.changeNickles(change);
               change = change.subtract(quarters.multiply(Coin.NICKLE.getValue()));
               BigDecimal dimes = Coin.changeDimes(change);
               change = change.subtract(quarters.multiply(Coin.DIME.getValue()));
               BigDecimal pennies = Coin.changePennies(change);
               change = change.subtract(quarters.multiply(Coin.PENNY.getValue()));

               io.print( "Item cost: " + item.getCost() + " Change: " +
                       quarters + " quarters, " + nickles + " nickles, " + dimes + " dimes, " + pennies + " pennies.");
               io.readString("Enter to continue");
          } catch (VendingMachineDaoPersistenceException e) {
               io.print("Inventory could not be loaded");
               displayExitMessage();
          } catch (InsufficientFundsException e) {
               io.print("Insufficient funds for the chosen item");
               displayExitMessage();
          } catch (NoItemInventoryException e) {
               io.print("Item is not available");
               displayExitMessage();
          }

     }

     public void displayExitMessage() {
          try {
               BigDecimal change = service.transaction(new BigDecimal("0"));
               io.print("Change: " + change + " Thank you!");
          } catch (InsufficientFundsException e){
               io.print("Something went wrong");
          }

     }

     public void inputErrorMessage(){
          io.print("You did not enter 'exit' or id of a product");
     }


}
