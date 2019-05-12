package SpringBootPackage.entity.MarketPlace.dao;

import SpringBootPackage.entity.Courses.models.Course;
import SpringBootPackage.entity.Courses.models.UserCourse;
import SpringBootPackage.entity.MarketPlace.models.MarketPlace;
import SpringBootPackage.entity.Products.models.Product;

import java.util.List;

public interface IMarketPlaceDao {
    public void addProductsToMarketPlace(Product product);
    public void addCoursesToMarketPlace(UserCourse course);
    public List<Product> getAllProductsFromMarketPlace();
    public List<Course> getAllCoursesFromMarketPlace();
    public List<Product> getAllProductsbyCategory(String category);
    public List<Course> getAllCoursesByCategory(String category);
}
