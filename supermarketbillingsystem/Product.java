package supermarketbillingsystem;

public class Product {
    String name;
    double price;
    int mnfMonth;
    int mnfYear;
    int expMonth;
    int expYear;
    String id;
    int quantity;
    Product(String name,double price,int mnfMonth,int mnfYear,int expMonth,int expYear,String id,int quantity)
    {
        this.name = name;
        this.price = price;
        this.mnfMonth = mnfMonth;
        this.mnfYear = mnfYear;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.id = id;
        this.quantity = quantity;
    }

    Product() {
       this.name = "none"; // this means by default product doesn't exists
    }
}
