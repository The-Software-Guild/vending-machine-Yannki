import controller.VendingMachineController;
import dao.VendingMachineDao;
import dao.VendingMachineDaoImpl;
import main.java.ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

public class AppVendingMachine {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VendingMachineView myUI = new VendingMachineView(myIo, myDao);
        VendingMachineController controller = new VendingMachineController(myUI, myDao);
        controller.run();
    }
}
