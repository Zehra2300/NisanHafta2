package productmanagementsystem.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
    private int id;
   @Column(name="name")
    private String name;
   @Column(name="price")
    private double price;
   @Column(name="stock")
   private int stock;

    public Product(int id,String name,double price,int stock) {
        this.id = id;
        this.price=price;
        this.name=name;
    }
    public Product() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }


}
