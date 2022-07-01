import controller.VendingMachineController;
import dao.VendingMachineDao;
import dao.VendingMachineDaoImpl;
import dao.VendingMachineDaoPersistenceException;
import main.java.ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

public class AppVendingMachine {
    public static void main(String[] args) throws VendingMachineDaoPersistenceException {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VendingMachineView myUI = new VendingMachineView(myIo, myDao);
        VendingMachineController controller = new VendingMachineController(myUI, myDao);
        controller.run();
    }
}
