package SpringBootPackage.entity.Courses.dao.impl;

import SpringBootPackage.entity.Courses.dao.ICourseDao;
import SpringBootPackage.entity.Courses.models.Course;
import SpringBootPackage.entity.Courses.models.UserCourse;
import SpringBootPackage.entity.MarketPlace.dao.MarketPlaceDaoImpl;
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
public class CourseDaoImpl implements ICourseDao {
    private Connection connection;

    @Autowired
    private MarketPlaceDaoImpl marketPlaceDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CourseDaoImpl() {
        this.connection = Utils.getConnection();
    }


    //TODO Change logic to get courseid using jdbcTemplate.
    @Override
    public void offerCourse(Course course) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sqlCourses = "INSERT INTO courses(coursename,status,category) VALUES(?,?,?);";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement1 = null;
                preparedStatement1 = connection.prepareStatement(sqlCourses,Statement.RETURN_GENERATED_KEYS);
                preparedStatement1.setString(1,course.getCourseName());
                preparedStatement1.setString(2,course.getStatus());
                preparedStatement1.setString(3,course.getCategory());
                return preparedStatement1;
            }
        },holder);

        int courseid = (Integer) holder.getKeys().get("courseid");
        UserCourse userCourse = new UserCourse();
        userCourse.setCourseid(courseid);
        //Getting userid from course, not added in usercourse
        userCourse.setUserid(course.getUserid());
        userCourse.setStatus(course.getStatus());
        this.offerExistingCourse(userCourse);
    }


    @Override
    public void offerExistingCourse(UserCourse userCourse) {

        KeyHolder holder = new GeneratedKeyHolder();
        String sqlUserCourse = "INSERT INTO usercourses(courseid,userid,status) VALUES(?::int,?::int,?);";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement1 = null;
                preparedStatement1 = connection.prepareStatement(sqlUserCourse, Statement.RETURN_GENERATED_KEYS);
                preparedStatement1.setInt(1,userCourse.getCourseid());
                preparedStatement1.setInt(2,userCourse.getUserid());
                preparedStatement1.setString(3,userCourse.getStatus());
                return preparedStatement1;
            }
        },holder);

        int usercourseid = (Integer) holder.getKeys().get("usercourseid");
        userCourse.setUsercourseid(usercourseid);
        marketPlaceDao.addCoursesToMarketPlace(userCourse);
    }

    @Override
    public List<Course> getAllExistingCourses() {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * From courses;";
        List<Course> courseList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            courseList = getCourses(resultSet);
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return courseList;
    }

    @Override
    public List<Course> getAllCoursesOfferedForUser(int userid) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT c.* From usercourses u INNER JOIN courses c on c.courseid=u.courseid where u.userid =?::int;";

        List<Course> courseList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Course course = new Course();
                course.setCourseName(resultSet.getString("coursename"));
                course.setCourseId(resultSet.getInt("courseid"));
                course.setStatus(resultSet.getString("status"));
                courseList.add(course);
                System.out.println(course.getCourseName());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return courseList;
    }

    @Override
    public List<Course> getAllCoursesRegisteredForUser(int userid) {
        //TODO

        return null;
    }

    private List<Course> getCourses(ResultSet resultSet){
        List<Course> courseList = new ArrayList<>();
        try {
            while(resultSet.next()){
                Course course = new Course();
                course.setCourseName(resultSet.getString("coursename"));
                course.setCourseId(resultSet.getInt("courseid"));
                course.setStatus(resultSet.getString("status"));
                //course.setUserid(resultSet.getInt("userid"));
                courseList.add(course);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       return courseList;
    }


}
