package SpringBootPackage.entity.Courses.dao;

import SpringBootPackage.entity.Courses.models.Course;
import SpringBootPackage.entity.Courses.models.UserCourse;

import java.util.List;

public interface ICourseDao {
    public void offerCourse(Course course);
    public void offerExistingCourse(UserCourse course);
    public List<Course> getAllExistingCourses();
    public List<Course> getAllCoursesOfferedForUser(int userid);
    public List<Course> getAllCoursesRegisteredForUser(int userid);
}
