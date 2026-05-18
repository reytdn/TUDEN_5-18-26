import java.util.*;

public class InvoiceApp {

    public static void main(String[] args) {
        Scanner INPUT = new Scanner(System.in);
        InvoiceDatabase DATABASE = new InvoiceDatabase();


        while (true) {
            System.out.println("");
            System.out.println("--- Invoice Menu ---");
            System.out.println("1. Add Invoice");
            System.out.println("2. Display Invoices with Balance");
            System.out.println("3. Pay Invoice");
            System.out.println("4. Delete Invoice");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int Choice = INPUT.nextInt();
            INPUT.nextLine();

            if (Choice == 1){
                System.out.print("Enter Invoice Number: ");
                String invo = INPUT.nextLine();

                System.out.print("Enter Customer Name: ");
                String customer = INPUT.nextLine();

                System.out.print("Enter Invoice Amount: ");
                int amount = INPUT.nextInt();
                INPUT.nextLine();

                if(DATABASE.Add_Invoice(invo, customer, amount, 0)){
                    System.out.println("Invoice added successfully.");
                } else {
                    System.out.println("Failed to add invoice.");
                }


            } else if (Choice == 2){
                if (DATABASE.hasRecord()) {
                    DATABASE.Display_Invoice();
                } else if (!DATABASE.hasRecord()) {
                    System.out.println("No recorded invoices."); 
                }
                

            } else if (Choice == 3){
                System.out.print("Enter Invoice Number to pay: ");
                String invno = INPUT.nextLine();

                System.out.print("Enter payment amount: ");
                int payment = INPUT.nextInt();
                INPUT.nextLine();
                if(DATABASE.Pay_Invoice(payment, invno)){
                    System.out.println("Payment recorded.");
                } else {
                    System.out.println("Failed to process payment.");
                }

            } else if (Choice == 4){
                if (DATABASE.hasRecord()) {
                    System.out.print("Enter Invoice Number to delete: ");
                    String invno = INPUT.nextLine();
                    if(DATABASE.Delete_Invoice(invno)){
                        System.out.println("Invoice deleted successfully.");
                    } else {
                        System.out.println("Failed to delete invoice.");
                    }
                    
                    
                } else if (!DATABASE.hasRecord()) {
                    System.out.println("No recorded invoices."); 
            }

                


            } else if (Choice == 5){
                System.out.println("Exiting...");
                break;
            }
            
        }
        INPUT.close();

    }
}
