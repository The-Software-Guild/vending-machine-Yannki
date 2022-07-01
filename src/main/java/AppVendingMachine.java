import controller.VendingMachineController;
import dao.VendingMachineDao;
import dao.VendingMachineDaoImpl;
import dao.VendingMachineDaoPersistenceException;
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
        ChangeServiceLayer myService = new ChangeServiceLayerImpl(myDao);
        VendingMachineView myUI = new VendingMachineView(myIo, myDao, myService);
        VendingMachineController controller = new VendingMachineController(myUI, myService);
        controller.run();
    }
}
