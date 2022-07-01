package controller;
import dao.VendingMachineDao;
import dao.VendingMachineDaoPersistenceException;
import ui.UserIO;
import service.ChangeServiceLayer;
import service.InsufficientFundsException;
import service.NoItemInventoryException;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

import java.math.BigDecimal;

public class VendingMachineController {

    private UserIO io = new UserIOConsoleImpl();
    private ChangeServiceLayer service;
    private VendingMachineView ui;

    public VendingMachineController(VendingMachineView ui, ChangeServiceLayer service){
        this.ui = ui;
        this.service = service;
    }

    public void run() {
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
        service.addMoney(funds);
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

    public void giveItemAndChange(int id) {
        ui.displayTransaction(id);
    };

}
