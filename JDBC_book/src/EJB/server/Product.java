/*
  Product.java defines a class to represent a row from the
  products table
*/

package EJB.server;

public class Product implements java.io.Serializable {

    // declare the Product attributes
    public int id;
    public String name;
    public String description;
    public float price;

    // define the constructor
    public Product(
            int id, String name, String description, float price
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

}