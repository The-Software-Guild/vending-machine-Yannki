package controller;
import dao.VendingMachineDao;
import dao.VendingMachineDaoPersistenceException;
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

    public void run() throws VendingMachineDaoPersistenceException {
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
                ui.inputErrorMessage();
            }
        }
    }

    public void giveItemAndChange(int id) throws VendingMachineDaoPersistenceException {
        ui.displayTransaction(id);
    };

}
