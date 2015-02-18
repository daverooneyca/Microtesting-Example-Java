package ca.daverooney.workshop.microtesting;

import java.util.List;

public interface ProductRepository {

    public abstract Product create(Product product);

    public abstract Product delete(Product product);

    public abstract Product getProductById(int id);

    public abstract List<Product> getAllProducts();

    public abstract List<Product> getInStockProducts();

}
