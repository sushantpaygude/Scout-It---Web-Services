package SpringBootPackage.entity.Products.dao.impl;

import SpringBootPackage.entity.MarketPlace.dao.MarketPlaceDaoImpl;
import SpringBootPackage.entity.Products.dao.IProductDao;
import SpringBootPackage.entity.Products.models.Product;
import SpringBootPackage.entity.Utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoImpl implements IProductDao {
    private Connection connection;
    @Autowired
    MarketPlaceDaoImpl marketPlaceDao;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public ProductDaoImpl(){
        this.connection = Utils.getConnection();
    }

    @Override
    public Product getProduct(int productid) {
        return null;
    }

    @Override
    public List<Product> getAllProductsForUser(int userid) {
       return getProductsfromDb(userid);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return getProductsfromDb(null);
    }

    private List<Product> getProductsfromDb(Integer userid){
        List<Product> productList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql;
        if(userid == null){
             sql = "SELECT m.marketplaceproductsid,p.* From marketplaceproducts m INNER JOIN products p on p.productid=m.productid;";
        }
        else{
             sql = "SELECT m.marketplaceproductsid,p.* From marketplaceproducts m INNER JOIN products p on p.productid=m.productid where p.userid =?::int;";
        }
        try {
            preparedStatement = connection.prepareStatement(sql);
            if(userid != null){
                preparedStatement.setInt(1,userid);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setMarketplaceproductsid(resultSet.getInt("marketplaceproductsid"));
                product.setProductName(resultSet.getString("productname"));
                product.setProductid(resultSet.getInt("productid"));
                product.setUserid(resultSet.getInt("userid"));
                product.setProductCost(resultSet.getInt("productcost"));
                product.setProductDescription(resultSet.getString("productdescription"));
                product.setProductPostDate(resultSet.getLong("productdate"));
                productList.add(product);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void deleteProduct(int productid) {

    }

    @Override
    public List<Product> getAllProductsbyCategory(String category) {
        List<Product> productList = new ArrayList<>();
//        PreparedStatement preparedStatement = null;
//        String sql;
//
//            sql = "SELECT * FROM products where category = ?;";
//
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,category);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){
//                Product product = new Product();
//                product.setProductName(resultSet.getString("productname"));
//                product.setProductid(resultSet.getInt("productid"));
//                product.setUserid(resultSet.getInt("userid"));
//                product.setProductCost(resultSet.getInt("productcost"));
//                product.setProductDescription(resultSet.getString("productdescription"));
//                product.setProductPostDate(resultSet.getLong("productdate"));
//                product.setCategory(resultSet.getString("category"));
//                productList.add(product);
//            }
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
        return productList;
    }


        @Override
    public void addProduct(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO products(userid,productname,productdescription,productcost,productdate,productstatus,category) VALUES(?::int,?,?,?,?,?,?);";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,String.valueOf(product.getUserid()));
                preparedStatement.setString(2,product.getProductName());
                preparedStatement.setString(3,product.getProductDescription());
                preparedStatement.setInt(4,product.getProductCost());
                preparedStatement.setLong(5,product.getProductPostDate());
                preparedStatement.setString(6,Product.productStatusEnum.SALE.toString());
                preparedStatement.setString(7,product.getCategory());
                return preparedStatement;
            }
        },keyHolder);

        int productid = (Integer)keyHolder.getKeys().get("productid");
        product.setProductid(productid);
        marketPlaceDao.addProductsToMarketPlace(product);
        System.out.println(productid);
    }




}
