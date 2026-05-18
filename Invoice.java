public class Invoice {
    private String invno;
    private String customer;
    private int amount;

    public Invoice(String invoiceNumber, String customerName, int amount, int payment) {
        this.invno = invoiceNumber;
        this.customer = customerName;
        this.amount = amount;
    }

    public String getInvoiceNumber() {
        return invno;
    }

    public String getCustomerName() {
        return customer;
    }

    public int getAmount() {
        return amount;
    }
}
