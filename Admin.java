package supermarketbillingsystem;

import java.lang.*;
import java.util.*;

import supermarketbillingsystem.Usefull.*;
import supermarketbillingsystem.Database.*;
import supermarketbillingsystem.Product;

public class Admin 
{   
    public static void adminLogin()
    {
        Usefull.clearScreen();
        System.out.println("---------------------------------------");
        System.out.println("    Admin Portal  ");
        System.out.println("---------------------------------------\n");
        Scanner in = new Scanner(System.in);
        System.out.print(" Enter userId : ");
        String userId = in.next();
        System.out.print(" Enter Password : ");
        String password = in.next();
        String orginalPassword = Database.getAdminPassword(userId);
        //System.out.println(password+" "+orginalPassword);
        if(orginalPassword.compareTo(password)!=0)
        {
            System.out.println("\n\n  [*]  Invalid user Id or Password....!   \npress 0 and enter");
            in.next();
            return;
        }
        // login succesfull
        Admin.adminDashboard(userId);
    }
    
    public static void adminDashboard(String userId)
    {
        while(true)
        {
            Usefull.clearScreen();
            System.out.println("---------------------------------------------");
            System.out.println("        Admin Dashboard      ");
            System.out.println("---------------------------------------------");
            System.out.println("  1. Create Product");
            System.out.println("  2. Set commision percentage");
            System.out.println("  3. Set Tax percentage");
            System.out.println("  4. Display All Products");
            System.out.println("  5. Modify Product");
            System.out.println("  6. Delete Product");
            System.out.println("  0. Back to Main Menu");
            System.out.println("---------------------------------------------\n");
            System.out.print(userId + "@admin>> Enter option : ");
            Scanner in = new Scanner(System.in);
            int option = in.nextInt();
            switch(option)
            {
                case 1: // create prod
                    createProduct();
                    break;
                case 2: // set commision
                    setCommission();
                    break;
                case 3:  // set Tax percentage
                    setTaxes();
                    break;
                case 4:  // disp all prod
                    dispProducts();
                    break;
                case 5:  // modify product
                    modifyProduct();
                    break;
                case 6:  //delete product
                    deleteProduct();
                    break;
                case 0:  //main menu
                    return;
                default:
                    System.out.println(" Invalid option ...!   \n press 0 and enter");
                    in.next();
            }
        }
    }
    
    public static void createProduct()
    {
        while(true)
        {
            Usefull.clearScreen();
            System.out.println("----------------------------------------------");
            System.out.println("               Create Product   ");
            System.out.println("----------------------------------------------\n");
            Scanner in = new Scanner(System.in);
            System.out.print(" Enter Product Name: ");
            String name = in.next();
            System.out.print(" Enter Product Price: ");
            double price = in.nextInt();
            System.out.println(" >>  Enter Manfacutered Date  << ");
            System.out.print("Enter month : ");
            int mnfMonth = in.nextInt();
            System.out.print("Enter year : ");
            int mnfYear = in.nextInt();
            System.out.println("   >> Enter Expiration Date <<  ");
            System.out.print("Enter month : ");
            int expMonth = in.nextInt();
            System.out.print("Enter year : ");
            int expYear = in.nextInt();
            System.out.print("Enter product Id (Id on barcode) :");
            String id = in.next();
            System.out.print("Enter product quantity :");
            int quantity = in.nextInt();
            Product product = new Product(name,price,mnfMonth,mnfYear,expMonth,expYear,id,quantity);
            Database.insertProduct(product);
            System.out.print("Do you want to enter another product (y/n) : ");
            String quit = in.next();
            if(quit.compareTo("n")==0)break;
        }
    }
    
    public static void setCommission()
    {
        Usefull.clearScreen();
        System.out.println("-------------------------------------");
        System.out.println("             Commission ");
        System.out.println("-------------------------------------");
        int oldCommission = Database.getCommission();
        System.out.println(" Current Commission Rate : "+oldCommission+" \n");
        System.out.print(" Enter new Commission Rate : ");
        Scanner in = new Scanner(System.in);
        int commission = in.nextInt();
        Database.setCommission(commission);
        System.out.println(" Commission rate was updated from "+oldCommission + " to "+commission);
        System.out.println("\n Press 0 and Enter to continue....");
        in.next();
        
    }
    
    public static void setTaxes()
    {
        Usefull.clearScreen();
        System.out.println("---------------------------------------------");
        System.out.println("        Tax Percentages ");
        System.out.println("---------------------------------------------");
        int oldTax = Database.getTaxes();
        System.out.println(" Current Tax Percentage : "+oldTax);
        System.out.print(" Enter new Tax Rate : ");
        Scanner in = new Scanner(System.in);
        int tax = in.nextInt();
        Database.setTaxes(tax);
        System.out.println(" Tax rate was updated from "+oldTax + " to "+tax);
        System.out.println("\n Press 0 and Enter to continue....");
        in.next();
    }
    
    public static void dispProducts()
    {
        Usefull.clearScreen();
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("          Products ");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("  S.No           Product       Price   mnf.Date   exp.Date   quantity    Product Id");
        System.out.println("--------------------------------------------------------------------------");
        ArrayList<Product> products = Database.getProducts();
        for(int ind=0;ind<products.size();ind++)
        {
            Product product = products.get(ind);
            System.out.printf(" %5d %20s %10.4f  %d/%d   %d/%d %10d    %10s \n",ind+1,product.name,Database.getPrice(product.price),product.mnfMonth,product.mnfYear,product.expMonth,product.expYear,product.quantity,product.id);
        }
        System.out.println("--------------------------------------------------------------------------");
        Scanner in = new Scanner(System.in);
        System.out.println("\n Press 0 and Enter to continue....");
        in.next();
    }
    
    public static void modifyProduct()
    {
        Usefull.clearScreen();
        System.out.println("-------------------------------------------------");
        System.out.println("       Modify Prodcut");
        System.out.println("-------------------------------------------------");
        System.out.print(" Enter product name :");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        Product product = Database.getProductByName(name+".tej");
        if("none".compareTo(product.name)==0)
        {
            System.out.println(" No Product with name "+name+" was found in the database...!");
            System.out.println(" Press 0 and Enter to  continue........");
            in.next();
            return;
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(" Product              Price   mnf.Date   exp.Date   quantity    Product Id");
        System.out.println("                   (costprice) ");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf(" %20s %10.4f  %d/%d   %d/%d %10d    %10s \n",product.name,product.price,product.mnfMonth,product.mnfYear,product.expMonth,product.expYear,product.quantity,product.id);
        System.out.println("--------------------------------------------------------------------------\n");
        while(true)
        {
            Usefull.clearScreen();
            //System.out.println("--------------------------------------------------------------------------\n");
            System.out.println("  1. Modify Price");
            System.out.println("  2. Modify mnf.Date");
            System.out.println("  3. Modify exp.Date");
            System.out.println("  4. Modify quantity");
            System.out.println("  5. Modify product Id");
            System.out.println("--------------------------------------------------------------------------\n");

            System.out.print("  Enter you option: ");
            int option = in.nextInt();
            int intTemp;
            String strTemp;
            double doubleTemp;
            switch(option)
            {
                case 1: //modify price
                    System.out.print(" Enter new Price: ");
                    doubleTemp = in.nextDouble();
                    product.price = doubleTemp;
                    break;
                case 2: //modify mnf date
                    System.out.print(" Enter new mnf month: ");
                    intTemp = in.nextInt();
                    product.mnfMonth = intTemp;
                    System.out.print(" Enter new mnf month: ");
                    intTemp = in.nextInt();
                    product.mnfYear = intTemp;
                    break;
                case 3: //modify exp date
                    System.out.print(" Enter new exp month: ");
                    intTemp = in.nextInt();
                    product.expMonth = intTemp;
                    System.out.print(" Enter new exp month: ");
                    intTemp = in.nextInt();
                    product.expYear = intTemp;
                    break;
                case 4: //modify quantity
                    System.out.print(" Enter new quantity: ");
                    intTemp = in.nextInt();
                    product.quantity = intTemp;
                    break;
                case 5: // modify  product Id
                    System.out.print(" Enter new Product Id: ");
                    strTemp = in.next();
                    product.id = strTemp;
                    break;
                default:
                    System.out.println("Sorry...! Invalid option....!");
                    
            }
            
            String choice;
            System.out.print(" Do you want to modify more (y/n): ");
            choice = in.next();
            if(choice.compareToIgnoreCase("n")==0)
            {
                Database.insertProduct(product);
                break;
            }
        }
        
    }
    
    public static void deleteProduct()
    {
        Usefull.clearScreen();
        System.out.println("------------------------------------------------");
        System.out.println("       Delete Product");
        System.out.println("------------------------------------------------");
        System.out.print("  Enter product name : ");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        Product product = Database.getProductByName(name+".tej");
        if("none".compareTo(product.name)==0)
        {
            System.out.println("No product with name "+name+" was not found..!");
        }
        else
            Database.deletedProduct(name);
        System.out.println(" [*] Press 0 and Enter to continue..............");
        in.next();
    }
}
