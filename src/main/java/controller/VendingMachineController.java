package controller;
import dao.VendingMachineDao;
import dto.Item;
import main.java.ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

import java.math.BigDecimal;

public class VendingMachineController {

    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineDao dao;
    private VendingMachineView ui;

    public VendingMachineController(VendingMachineView ui, VendingMachineDao dao){
        this.ui = ui;
        this.dao =dao;
    }

    public void run(){
        boolean exit = false;
        int decision;
        do {
            ui.showInventory();
            decision = askForSelectionAndFunds();

            if (decision == 0) {
                ui.displayExitMessage();
                exit = true;
            }else {
                giveItemAndChange(decision);
            }

        } while(!exit);
    }

    public int askForSelectionAndFunds(){
        BigDecimal funds = ui.askForFunds();
        dao.addMoney(funds);
        String decision;
        while (true) {
            decision = ui.askForSelection();
            if (decision.equalsIgnoreCase("exit")){
                return 0;
            }
            try{
                return Integer.parseInt(decision);
            } catch (NumberFormatException e){
                io.print("You did not enter 'exit' or id of a product");
            }
        }
    }

    public void giveItemAndChange(int id){
        ui.displayTransaction(id);
    };

}
