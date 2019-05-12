package SpringBootPackage.entity.MarketPlace.dao;

import SpringBootPackage.entity.Courses.models.Course;
import SpringBootPackage.entity.Courses.models.UserCourse;
import SpringBootPackage.entity.MarketPlace.models.MarketPlace;
import SpringBootPackage.entity.Products.models.Product;
import SpringBootPackage.entity.Utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MarketPlaceDaoImpl implements IMarketPlaceDao {
    private  Connection connection;


    public MarketPlaceDaoImpl() {
        this.connection = Utils.getConnection();
    }

    @Override
    public void addProductsToMarketPlace(Product product) {
        PreparedStatement preparedStatement = null;
        String sqlmarket = "INSERT INTO marketplaceproducts(productid,postdate,productpurchased) VALUES(?::int,?,?);";
        try {
            preparedStatement = connection.prepareStatement(sqlmarket);
            preparedStatement = connection.prepareStatement(sqlmarket);
            preparedStatement.setInt(1,product.getProductid());
            preparedStatement.setLong(2,product.getProductPostDate());
            preparedStatement.setString(3,product.getProductStatus());
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }



    @Override
    public void addCoursesToMarketPlace(UserCourse userCourse) {
        PreparedStatement preparedStatement1 = null;
        String sqlmarketCourse = "INSERT INTO marketplacecourse(usercourseid) VALUES(?::int);";
        try {
            preparedStatement1 = connection.prepareStatement(sqlmarketCourse);
            preparedStatement1.setInt(1,userCourse.getUsercourseid());
            preparedStatement1.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProductsFromMarketPlace() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT m.marketplaceproductsid,p.* from marketplaceproducts m INNER JOIN products p on p.productid=m.productid AND p.productstatus=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Product.productStatusEnum.SALE.toString());
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
                product.setCategory(resultSet.getString("category"));
                productList.add(product);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return productList;
    }


    @Override
    public List<Product> getAllProductsbyCategory(String category) {
        List<Product> productList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql;
        sql = "SELECT m.marketplaceproductsid,p.* from marketplaceproducts m INNER JOIN products p on p.productid=m.productid WHERE p.category=?;";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,category);

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
                product.setCategory(resultSet.getString("category"));
                productList.add(product);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return productList;
    }



    @Override
    public List<Course> getAllCoursesFromMarketPlace() {
        PreparedStatement preparedStatement = null;
        List<Course> courseList = new ArrayList<>();
        String sqlcourse = "select m.marketplacecourseid,u.usercourseid,c.*,us.* from usercourses u \n" +
                "INNER JOIN courses c on u.courseid = c.courseid\n" +
                "INNER JOIN marketplacecourse m on m.usercourseid=u.usercourseid\n" +
                "INNER JOIN users us on u.userid = us.userid;";
        try {
            preparedStatement = connection.prepareStatement(sqlcourse);
            ResultSet resultSet = preparedStatement.executeQuery();
            courseList = getCourses(resultSet);

        }catch (Exception e){e.printStackTrace();}
        return courseList;
    }

    @Override
    public List<Course> getAllCoursesByCategory(String category) {
        List<Course> courseList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sqlcategory = "select m.marketplacecourseid,u.usercourseid,c.*,us.* from usercourses u \n" +
                "INNER JOIN courses c on u.courseid = c.courseid\n" +
                "INNER JOIN marketplacecourse m on m.usercourseid=u.usercourseid\n" +
                "INNER JOIN users us on u.userid = us.userid where c.category = ?;";
        try {
            preparedStatement = connection.prepareStatement(sqlcategory);
            preparedStatement.setString(1,category);
            ResultSet resultSet = preparedStatement.executeQuery();
            courseList = getCourses(resultSet);

        }catch (Exception e){e.printStackTrace();}
        return courseList;
    }


    private List<Course> getCourses(ResultSet resultSet){
        List<Course> courseList = new ArrayList<>();
        try {
            while(resultSet.next()){
                Course course = new Course();
                course.setMarketplacecourseid(resultSet.getInt("marketplacecourseid"));
                course.setUsercourseid(resultSet.getInt("usercourseid"));
                course.setCourseName(resultSet.getString("coursename"));
                course.setCourseId(resultSet.getInt("courseid"));
                course.setStatus(resultSet.getString("status"));
                course.setCategory(resultSet.getString("category"));
                course.setUserid(resultSet.getInt("userid"));
                course.setFirstname(resultSet.getString("firstname"));
                course.setLastname(resultSet.getString("lastname"));
                course.setEmail(resultSet.getString("email"));
                courseList.add(course);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return courseList;
    }
}
