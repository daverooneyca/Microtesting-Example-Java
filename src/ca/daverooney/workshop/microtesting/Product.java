package ca.daverooney.workshop.microtesting;

import java.util.List;

public class Product {
    private long   id;
    private String name;
    private double price;
    private int    quantity;
    
    ProductRepository repo;

    public Product() {
        this(new DatabaseProductRepository());
    }
    
    public Product(ProductRepository repo) {
        this.repo = repo;
    }
    
    public Product create(Product product) {
        return repo.create(product);
    }

    public Product delete(Product product) {
        return repo.delete(product);
    }

    public Product getProductById(int id) {
        return repo.getProductById(id);
    }

    public List<Product> getAllProducts() {
        return repo.getAllProducts();
    }

    public List<Product> getInStockProducts() {
        return repo.getInStockProducts();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
