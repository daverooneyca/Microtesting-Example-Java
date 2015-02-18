package ca.daverooney.workshop.microtesting;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {
    
    Product product;
    
    @Before
    public void setup() {
        ProductRepository repo = new DatabaseProductRepository() {
            public List<Product> getAllProducts() {
                List<Product> allProducts = new ArrayList<Product>();
                
                Product product1 = new Product();
                product1.setId(1);
                product1.setName("foo");
                product1.setQuantity(0);
                
                Product product2 = new Product();
                product2.setId(2);
                product2.setName("bar");
                product2.setQuantity(1);
                
                allProducts.add(product1);
                allProducts.add(product2);
                
                return allProducts;
            }
        };
        
        product = new Product(repo);
    }
    
    @After
    public void teardown() {
        // This is executed after each test
    }
    
    @Test
    public void testGetAllProductsShouldReturnAllProducts() throws Exception {
        List<Product> products = product.getAllProducts();
        
        assertEquals(2, products.size());
    }

    @Test
    public void testGetInStockProductsShouldOnlyHaveNoProductsWithQuantityZero() throws Exception {
        List<Product> products = product.getInStockProducts();
        
        assertEquals(1, products.size());
        
        Product product = products.get(0);
        
        assertEquals(1, product.getQuantity());
    }
    
    @Test
    public void testGetKnownProductShouldReturnThatProduct() throws Exception {
        Product foo = new Product();
        Product product = foo.getProductById(1);
        
        assertEquals(1, product.getId());
        assertEquals("Ibuprofen", product.getName());
        assertEquals(14.39d, product.getPrice(), 0);
        assertEquals(124, product.getQuantity());
    }
    
    @Test
    public void testCreateAddsANewProduct() throws Exception {
        Product foo = new Product();
        Product product = new Product();
        
        product.setName("Codeine");
        product.setPrice(3.16d);
        product.setQuantity(100);
        
        Product newProduct = foo.create(product);
        
        assertEquals("Codeine", newProduct.getName());
        assertEquals(3.16d, newProduct.getPrice(), 0);
        assertEquals(100, newProduct.getQuantity());
    }
    
    @Test
    public void testKnownDataSet() {
        ProductRepository knownData = new XMLProductRepository();
        
        assertEquals(true, true);
    }
}
