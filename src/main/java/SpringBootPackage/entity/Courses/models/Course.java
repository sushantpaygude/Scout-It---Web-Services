package SpringBootPackage.entity.Courses.models;

import SpringBootPackage.entity.Utilities.IScoutItView;

public class Course implements IScoutItView {
    private String courseName;
    private int courseId;
    private String status;
    private String category;

    //Not using for courses table.
    private int userid;
    private int marketplacecourseid;
    private int usercourseid;
    private String firstname;
    private String lastname;
    private String email;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMarketplacecourseid() {
        return marketplacecourseid;
    }

    public void setMarketplacecourseid(int marketplacecourseid) {
        this.marketplacecourseid = marketplacecourseid;
    }

    public int getUsercourseid() {
        return usercourseid;
    }

    public void setUsercourseid(int usercourseid) {
        this.usercourseid = usercourseid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
