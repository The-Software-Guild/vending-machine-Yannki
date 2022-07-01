import controller.VendingMachineController;
import dao.*;
import ui.UserIO;
import service.ChangeServiceLayer;
import service.ChangeServiceLayerImpl;
import service.InsufficientFundsException;
import service.NoItemInventoryException;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

public class AppVendingMachine {
    public static void main(String[] args) throws VendingMachineDaoPersistenceException, NoItemInventoryException, InsufficientFundsException {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineDaoFileImpl();
        ChangeServiceLayer myService = new ChangeServiceLayerImpl(myDao, myAuditDao);
        VendingMachineView myUI = new VendingMachineView(myIo, myDao, myService);
        VendingMachineController controller = new VendingMachineController(myUI, myService);
        controller.run();
    }
}
