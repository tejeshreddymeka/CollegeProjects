
package supermarketbillingsystem;

import java.lang.*;

public class Usefull {
    public static void clearScreen(){
 
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
    
    public static String encrypt(String clearText)
    {
        StringBuffer cipherText = new StringBuffer();
        for(char ch : clearText.toCharArray())
        {   
            cipherText.append( (char)( ((int)ch)^((int)'$') ));
        }
        return cipherText.toString();
    }
}

