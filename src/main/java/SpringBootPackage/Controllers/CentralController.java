package SpringBootPackage.Controllers;

import SpringBootPackage.entity.Courses.dao.impl.CourseDaoImpl;
import SpringBootPackage.entity.Courses.models.Course;
import SpringBootPackage.entity.Courses.models.UserCourse;
import SpringBootPackage.entity.MarketPlace.dao.MarketPlaceDaoImpl;
import SpringBootPackage.entity.Products.dao.impl.ProductDaoImpl;
import SpringBootPackage.entity.Products.models.Product;
import SpringBootPackage.entity.Users.dao.impl.UserDaoImpl;
import SpringBootPackage.entity.Users.models.Users;
import SpringBootPackage.entity.Utilities.ResponseView;
import SpringBootPackage.entity.Utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CentralController {

//    @Autowired
//    TopicService topicService;

    @Autowired
    UserDaoImpl userDao;

    @Autowired
    ProductDaoImpl productDao;

    @Autowired
    CourseDaoImpl courseDao;

    @Autowired
    MarketPlaceDaoImpl marketPlaceDao;

    //APIs For User CRUD
    @RequestMapping(method = RequestMethod.POST, value = Utils.BASE_URL+"/users/adduser")
    public void AddUser(@RequestBody Users users)
    {
        userDao.createUser(users);
    }

    @RequestMapping(method = RequestMethod.GET,value = Utils.BASE_URL+"/users/getuserbyid/{id}")
    public Users getUserById(@PathVariable int id){
       return userDao.getUser(id);
    }

    @RequestMapping(method = RequestMethod.GET,value = Utils.BASE_URL+"/users/getallusers")
    public List<Users> getAllUsers(){
        ResponseView responseView = new ResponseView();
        return userDao.getAllUsers();
    }


    //APIs For Product CRUD.
    @RequestMapping(method = RequestMethod.POST, value = Utils.BASE_URL+"/products/addproduct")
    public void AddProduct(@RequestBody Product product)
    {
        productDao.addProduct(product);
    }

    @RequestMapping(method = RequestMethod.GET,value = Utils.BASE_URL+"/products/getallproductsforuser/{userid}")
    public List<Product> getAllProductsforUser(@PathVariable int userid){
        return productDao.getAllProductsForUser(userid);
    }

    @RequestMapping(method = RequestMethod.GET,value = Utils.BASE_URL+"/products/getallactiveproducts")
    public List<Product> getAllActiveProducts(){
        return marketPlaceDao.getAllProductsFromMarketPlace();
    }

    @RequestMapping(method = RequestMethod.GET,value = Utils.BASE_URL+"/products/getproductsbycategory/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category){
        return marketPlaceDao.getAllProductsbyCategory(category);
    }



    //APIs for Course CRUD.
    @RequestMapping(method = RequestMethod.POST, value = Utils.BASE_URL+"/courses/offercourse")
    public void offerCourse(@RequestBody Course course)
    {
        courseDao.offerCourse(course);
    }

    @RequestMapping(method = RequestMethod.GET,value = Utils.BASE_URL+"/courses/getallcourses")
    public List<Course> getAllCourses(){
        return marketPlaceDao.getAllCoursesFromMarketPlace();
    }

    @RequestMapping(method = RequestMethod.GET,value = Utils.BASE_URL+"/courses/getallexistingcourses")
    public List<Course> getAllExistingCourses(){
        return courseDao.getAllExistingCourses();
    }

    @RequestMapping(method = RequestMethod.GET,value = Utils.BASE_URL+"/courses/getcoursesbycategory/{category}")
    public List<Course> getAllCoursesByCategory(@PathVariable String category){
        return marketPlaceDao.getAllCoursesByCategory(category);
    }

    @RequestMapping(method = RequestMethod.GET,value = Utils.BASE_URL+"/courses/getallcoursesofferedforuser/{userid}")
    public List<Course> getAllCoursesOfferedForUser(@PathVariable int userid){
        return courseDao.getAllCoursesOfferedForUser(userid);
    }

    @RequestMapping(method = RequestMethod.POST, value = Utils.BASE_URL+"/courses/offerexistingcourse")
    public void offerExistingCourse(@RequestBody UserCourse userCourse)
    {
        courseDao.offerExistingCourse(userCourse);
    }

}

