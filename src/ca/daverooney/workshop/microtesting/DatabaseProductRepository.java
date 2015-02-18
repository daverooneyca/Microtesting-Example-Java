package ca.daverooney.workshop.microtesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProductRepository implements ProductRepository {

    /* (non-Javadoc)
     * @see ca.daverooney.workshop.microtesting.ProductRepository#create(ca.daverooney.workshop.microtesting.Product)
     */
    @Override
    public Product create(Product product) {
        Connection connection = null;
        PreparedStatement statement = null;
        Product newProduct = null;
    
        String url = "jdbc:mysql://localhost:3306/microtesting";
        String user = "microtesting";
        String password = "microtesting";
    
        try {
            connection = DriverManager.getConnection(url, user, password);
            
            String query = "INSERT INTO PRODUCTS VALUES(?, ?, ?, ?)";
            
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, 0);
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
    
            int rows = statement.executeUpdate();
    
            if (rows > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                
                if(resultSet.next())
                {
                    int id = resultSet.getInt(1);
                    
                    newProduct = getProductById(id);
                }
            }
    
        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(Product.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
    
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
    
            } catch (SQLException ex) {
                Logger logger = Logger.getLogger(Product.class.getName());
                logger.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    
        return newProduct;
    }

    /* (non-Javadoc)
     * @see ca.daverooney.workshop.microtesting.ProductRepository#delete(ca.daverooney.workshop.microtesting.Product)
     */
    @Override
    public Product delete(Product product) {
        Connection connection = null;
        PreparedStatement statement = null;
        Product newProduct = null;
    
        String url = "jdbc:mysql://localhost:3306/microtesting";
        String user = "microtesting";
        String password = "microtesting";
    
        try {
            connection = DriverManager.getConnection(url, user, password);
            
            String query = "DELETE FROM PRODUCTS WHERE ID = ?";
            
            statement = connection.prepareStatement(query);
            
            statement.setLong(1, product.getId());
    
            statement.executeUpdate();
    
        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(Product.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
    
        } finally {
    
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
    
            } catch (SQLException ex) {
                Logger logger = Logger.getLogger(Product.class.getName());
                logger.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    
        return newProduct;
    }

    /* (non-Javadoc)
     * @see ca.daverooney.workshop.microtesting.ProductRepository#getProductById(int)
     */
    @Override
    public Product getProductById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        Product product = null;
    
        String url = "jdbc:mysql://localhost:3306/microtesting";
        String user = "microtesting";
        String password = "microtesting";
    
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                product = new Product();
    
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setQuantity(resultSet.getInt(4));
            }
    
        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(Product.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
    
        } finally {
    
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
    
            } catch (SQLException ex) {
                Logger logger = Logger.getLogger(Product.class.getName());
                logger.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    
        return product;
    }

    /* (non-Javadoc)
     * @see ca.daverooney.workshop.microtesting.ProductRepository#getAllProducts()
     */
    @Override
    public List<Product> getAllProducts() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        List<Product> products = new ArrayList<Product>();
    
        String url = "jdbc:mysql://localhost:3306/microtesting";
        String user = "microtesting";
        String password = "microtesting";
    
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.prepareStatement("SELECT * FROM PRODUCTS");
            resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Product product = new Product();
    
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setQuantity(resultSet.getInt(4));
    
                products.add(product);
            }
    
        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(Product.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
    
        } finally {
    
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
    
            } catch (SQLException ex) {
                Logger logger = Logger.getLogger(Product.class.getName());
                logger.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    
        return products;
    }

    /* (non-Javadoc)
     * @see ca.daverooney.workshop.microtesting.ProductRepository#getInStockProducts()
     */
    @Override
    public List<Product> getInStockProducts() {
        List<Product> products = getAllProducts();
        List<Product> inStockProducts = new ArrayList<Product>();
        
        for (Product product : products) {
            if (product.getQuantity() > 0) {
                inStockProducts.add(product);
            }
        }
    
        return inStockProducts;
    }

}
