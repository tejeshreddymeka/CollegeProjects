
package supermarketbillingsystem;

import java.lang.*;
import java.util.*;
import supermarketbillingsystem.Usefull.*;
import supermarketbillingsystem.Admin.*;
//import supermarketbillingsystem.BillCalculator.*;

public class SuperMarketBillingSystem {

    public static void main(String[] args) {
        
        while(true)
        {
            Usefull.clearScreen();
            System.out.println("-------------------------------------------");
            System.out.println("       Super Market Billing System");
            System.out.println("-------------------------------------------\n");
            System.out.println("      1. Administrator Login");
            System.out.println("      2. Bill Calculator Login");
            System.out.println("      0. Exit");
             System.out.println("-------------------------------------------\n");
            System.out.print("  Enter your option >>  ");
            Scanner in = new Scanner(System.in);
            int option;
            try
            {
                option =  in.nextInt();
            }
            catch(Exception ex)
            {
                System.out.println("Sorry..! Invalid input....!\nTry again after pressing 0 and enter...!");
                in.next();
                continue;
            }
            
            switch(option)
            {
                case 1:  //Admin
                    Admin.adminLogin();
                    break;
                case 2: // Bill calc
                    BillCalculator.BillCalcLogin();
                    break;
                case 0:
                    System.exit(0);
                default:
            }
                
              
            
           
        }
        
    }
    
}


