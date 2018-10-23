
package supermarketbillingsystem;

import supermarketbillingsystem.Product;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    
    public static String getAdminPassword(String userName)
    {
        if(userName.compareTo("tomcruze")==0)
        {
            return "tomcruze";
        }
        else
            return "";
    }
    
    public static int getCommission()
    {
        String path = System.getProperty("user.dir");
        String dir = path+"\\database\\";
        new File(dir).mkdirs();
        File file = new File(path+"\\database\\commission.tej");
        Scanner inFile;
        try {
             inFile = new Scanner(file);
        } catch (FileNotFoundException ex) {
            //System.out.println(" commission.tej file not found...!");
            return 0;
        }
        int commission = inFile.nextInt();
        inFile.close();
        return commission;
        
    }
    
    public static void setCommission(int commission)
    {
        String path = System.getProperty("user.dir");
        String dir = path+"\\database\\";
        new File(dir).mkdirs();
        String name = path+"\\database\\commission.tej";
        PrintWriter writer;
        try
        {
            writer = new PrintWriter(name);
            writer.println(commission);
            writer.close();
        }
        catch(Exception ex)
        {
            //System.out.println(ex);
        } 
    }
    
    public static int getTaxes()
    {
        String path = System.getProperty("user.dir");
        String dir = path+"\\database\\";
        new File(dir).mkdirs();
        File file = new File(path+"\\database\\taxes.tej");
        Scanner inFile;
        try {
             inFile = new Scanner(file);
        } catch (FileNotFoundException ex) {
           // System.out.println(" commission.tej file not found...!");
            return 0;
        }
        int tax = inFile.nextInt();
        inFile.close();
        return tax;
        
    }
    
    public static void setTaxes(int tax)
    {
        String path = System.getProperty("user.dir");
        String dir = path+"\\database\\";
        new File(dir).mkdirs();
        String name = path+"\\database\\taxes.tej";
        PrintWriter writer;
        try
        {
            writer = new PrintWriter(name);
            writer.println(tax);
            writer.close();
        }
        catch(Exception ex)
        {
           // System.out.println(ex);
        } 
    }
    
    
    public static void insertProduct(Product product)
    {
        String path = System.getProperty("user.dir");
        //System.out.println(path);
        String dir = path+"\\database\\";
        new File(dir).mkdirs();
        String dir2 = dir+"\\products\\";
        new File(dir2).mkdirs();
        String name = path+"\\database\\products\\"+product.name+".tej";
        PrintWriter writer;
        try {
            writer = new PrintWriter(name);
            writer.println(product.name);
            writer.println(product.price);
            writer.println(product.mnfMonth);
            writer.println(product.mnfYear);
            writer.println(product.expMonth);
            writer.println(product.expYear);
            writer.println(product.id);
            writer.println(product.quantity);
            writer.close();
        } catch (FileNotFoundException ex) {
           // System.out.println(ex);
        }
        //writer.println("");    
        //System.out.println(product.name + " inserted into database....!");
    }
    
    public static ArrayList<Product> getProducts()
    {
        ArrayList<Product> products =  new ArrayList<Product>();
        
        String path = System.getProperty("user.dir");
        //System.out.println(path);
        String dir = path+"\\database\\";
        new File(dir).mkdirs();
        String dir2 = dir+"\\products\\";
        new File(dir2).mkdirs();
        File folder = new File(dir2);
        File[] listOfFiles = folder.listFiles();
        for(int i = 0; i < listOfFiles.length; i++) {
          if (listOfFiles[i].isFile()) {
            //System.out.println("File " + listOfFiles[i].getName());
            String fileName = listOfFiles[i].getName();
            products.add(getProductByName(fileName));
          } 
        }
        return products;
    }
    
    public static Product getProductByName(String productName)
    {
        String path = System.getProperty("user.dir");
        //System.out.println(productName);
        String fileName = path+"\\database\\products\\"+productName;
        File file = new File(fileName);
        Scanner inFile;
         try {
             inFile = new Scanner(file);
        } catch (FileNotFoundException ex) {
           // System.out.println("file not found...!");
            return new Product();
        }
        String name = inFile.next();
        double price = inFile.nextDouble();
        int mnfMonth = inFile.nextInt();
        int mnfYear = inFile.nextInt();
        int expMonth = inFile.nextInt();
        int expYear = inFile.nextInt();
        String id = inFile.next();
        int quantity = inFile.nextInt();
        inFile.close();
        return new Product(name,price,mnfMonth,mnfYear,expMonth,expYear,id,quantity);
        
    }

    static double getPrice(double price) {
        int commission = getCommission();
        int tax = getTaxes();
        double newPrice = (price+(price*commission)*0.01)+(price+(price+(price*commission)*0.01) * tax)*0.01;
        return newPrice;
    }
 
    static void deletedProduct(String productName)
    {
        String path = System.getProperty("user.dir");
        String fileName = path+"\\database\\products\\"+productName+".tej";
        File file = new File(fileName); 
        //System.out.println(file.getName());
        //System.out.println(fileName);
        if(file.delete()) 
        { 
            System.out.println(productName+" deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the product "+productName); 
        } 
    }

    static String getBillCalculatorPassword(String userId) {
        if(userId.compareTo("tom")==0)
        {
            return "tom";
        }
        else
            return "";
    }
}
