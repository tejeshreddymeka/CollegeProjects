package supermarketbillingsystem;

import java.util.ArrayList;
import java.util.Scanner;
import supermarketbillingsystem.*;

public class BillCalculator {
    public static void BillCalcLogin()
    {
        Usefull.clearScreen();
        System.out.println("---------------------------------------");
        System.out.println("    BillCalculator Portal  ");
        System.out.println("---------------------------------------\n");
        Scanner in = new Scanner(System.in);
        System.out.print(" Enter userId : ");
        String userId = in.next();
        System.out.print("  Enter Password : ");
        String password = in.next();
        String orginalPassword = Database.getBillCalculatorPassword(userId);
        //System.out.println(password+" "+orginalPassword);
        if(orginalPassword.compareTo(password)!=0)
        {
            System.out.println("\n\n  [*]  Invalid user Id or Password....!   \npress 0 and enter");
            in.next();
            return;
        }
        // login succesfull
        BillCalcDashboard();
    }
    
    private static void BillCalcDashboard()
    {
        ArrayList<Product> products = Database.getProducts();
        while(true)
        {
            Usefull.clearScreen();
            System.out.println("--------------------------------------------------");
            System.out.println("         Bill Calculator");
            System.out.println("--------------------------------------------------");
            System.out.println("  1. Show Product List");
            System.out.println("  2. Calculate bill  ");
            System.out.println("  3. Back to Main Menu");
            System.out.println("---------------------------------------------------");
            System.out.print(" Enter you option: ");
            Scanner in = new Scanner(System.in);
            int option = in.nextInt();
            switch(option)
            {
                case 1: // show product list
                    dispProducts(products);
                    break;
                case 2: // calc bill
                    dispProducts(products);
                    caclBill(products);
                    break;
                case 3:
                    return;
            }
        }
        
        
    }

    private static void dispProducts(ArrayList<Product> products) {
        Usefull.clearScreen();
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("          Products ");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("  S.No         Product          Price   mnf.Date   exp.Date   quantity    Product Id");
        System.out.println("------------------------------------------------------------------------------------");
        
        for(int ind=0;ind<products.size();ind++)
        {
            Product product = products.get(ind);
            System.out.printf(" %5d %20s %10.4f  %d/%d   %d/%d %10d    %10s \n",ind+1,product.name,Database.getPrice(product.price),product.mnfMonth,product.mnfYear,product.expMonth,product.expYear,product.quantity,product.id);
        }
        System.out.println("---------------------------------------------------------------------------------------");
        Scanner in = new Scanner(System.in);
        System.out.println("\n Press 0 and Enter to continue....");
        in.next();
    }

    private static void caclBill(ArrayList<Product> products) {
        //Usefull.clearScreen();
        System.out.println("\n\n------------------------------------------------");
        System.out.println("            Calcuate Bill Amount  ");
        System.out.println("------------------------------------------------");
        ArrayList cart = new ArrayList();
        ArrayList quantity = new ArrayList();
        Scanner in = new Scanner(System.in);
        while(true)
        {
            System.out.print(" Enter the product S.No :");
            int prodNo = in.nextInt();
            System.out.print("Enter quantity : ");
            int prodQuantity = in.nextInt();
            Product product = products.get(prodNo-1);
            int availQuantity = product.quantity;
            String prodName = product.name;
            if(prodQuantity>availQuantity)
            {
                System.out.println(" Sorry..! There are only "+availQuantity+" "+prodName+" products available..!\n Try selecting less than or equal to available quantity..!\n");
                continue;
            }
            
            cart.add(prodNo-1);
            quantity.add(prodQuantity);
            product.quantity -= prodQuantity;
            Database.insertProduct(product);
            System.out.print("Do you want to add anthoer product to cart (y/n):");
            String choice = in.next();
            if("n".compareToIgnoreCase(choice)==0)
                break;
        }
        
       genarateBill(products,cart,quantity);
    }

   

    private static void genarateBill(ArrayList<Product> products, ArrayList cart, ArrayList quantity) {
        Usefull.clearScreen();
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("                             Bill ");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("  S.No          Product    Price(each)  mnf.Date   exp.Date         Product Id  quantity  Amount ");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        double totalAmount = 0.0;
        for(int ind=0;ind<cart.size();ind++)
        {
            int prodInd = (int) cart.get(ind);
            Product product = products.get(prodInd);
            double price = Database.getPrice(product.price);
            System.out.printf(" %5d %20s %10.4f  %d/%d   %d/%d  %20s %6d  %10.4f\n",ind+1,product.name,price,product.mnfMonth,product.mnfYear,product.expMonth,product.expYear,product.id,quantity.get(ind),((int)quantity.get(ind))*price);
            totalAmount+=((int)quantity.get(ind))*price;
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("                                                                           Total Amount = %10.4f\n",totalAmount);
        System.out.println("----------------------------------------------------------------------------------------------------");
        Scanner in = new Scanner(System.in);
        System.out.println("\n Press 0 and Enter to continue....");
        in.next();
        
    }

   
    
}
