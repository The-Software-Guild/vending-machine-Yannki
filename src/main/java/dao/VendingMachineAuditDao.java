package dao;

public interface VendingMachineAuditDao {

    public void writeAuditEntry(String entry) throws  VendingMachineDaoPersistenceException;

}
