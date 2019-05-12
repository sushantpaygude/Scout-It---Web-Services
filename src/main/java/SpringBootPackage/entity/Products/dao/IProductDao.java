package SpringBootPackage.entity.Products.dao;

import SpringBootPackage.entity.Products.models.Product;

import java.util.List;

public interface IProductDao {
    public void addProduct(Product product);
    public Product getProduct(int productid);
    public List<Product> getAllProductsForUser(int userid);
    public List<Product> getAllActiveProducts();
    public void deleteProduct(int productid);
    public List<Product> getAllProductsbyCategory(String category);
}
