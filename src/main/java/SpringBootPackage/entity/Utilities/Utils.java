package SpringBootPackage.entity.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {

    public static final String BASE_URL = "/scoutit";
    public static Connection getConnection(){

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/scoutit", "postgres", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public enum ProductType{
        PRODUCT("PRODUCT"),
        COURSE("COURSE");
        private String type;
        ProductType(String type){
            this.type = type;
        }
        @Override
        public String toString() {
            return type;
        }
    }

    public enum productCategoryEnum
    {
        ELECTRONICS("ELECTRONICS"),
        BOOKS("BOOKS"),
        FURNITURE("FURNITURE");
        private String categoryName;
        productCategoryEnum(String s){
            this.categoryName = s;
        }

        @Override
        public String toString() {
            return categoryName;
        }
    }

    public enum CourseType{
        COMPUTER_SCIENCE("COMPUTER SCIENCE"),
        INFORMATION_SYSTEM("INFORMATION_SYSTEM"),
        COMPUTER_ENGINEERING("COMPUTER_ENGINEERING"),
        ELECTRICAL_ENGINEERING("ELECTRICAL_ENGINEERING");
        private String type;
        CourseType(String type){
            this.type = type;
        }
        @Override
        public String toString() {
            return type;
        }
    }
}
