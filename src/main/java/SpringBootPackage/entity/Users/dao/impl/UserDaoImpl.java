package SpringBootPackage.entity.Users.dao.impl;

import SpringBootPackage.entity.Utilities.Utils;
import SpringBootPackage.entity.Users.dao.IUserDao;
import SpringBootPackage.entity.Users.models.Users;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements IUserDao {


    private Connection connection;

    public UserDaoImpl(){
        this.connection = Utils.getConnection();
    }


    @Override
    public int createUser(Users users) {

        String sql = "INSERT INTO users(firstName,lastName,email ) VALUES (?,?,?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,users.getFirstName());
            preparedStatement.setString(2,users.getLastName());
            preparedStatement.setString(3,users.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateUser(Users users) {

    }

    @Override
    public Users getUser(int userid) {
        String sql = "SELECT * from users where userid = ?::int ";
        PreparedStatement preparedStatement = null;
        Users users = new Users();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(userid));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                users.setUserid(resultSet.getInt("userid"));
                users.setEmail(resultSet.getString("email"));
                users.setFirstName(resultSet.getString("firstname"));
                users.setLastName(resultSet.getString("lastname"));
            }
        }catch (Exception e){e.printStackTrace();}

        return users;
    }

    @Override
    public List<Users> getAllUsers() {

        String sql = "SELECT * from users";
        PreparedStatement preparedStatement = null;
        List<Users> usersList = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Users users = new Users();
                users.setUserid(resultSet.getInt("userid"));
                users.setEmail(resultSet.getString("email"));
                users.setFirstName(resultSet.getString("firstname"));
                users.setLastName(resultSet.getString("lastname"));
                usersList.add(users);
            }
        }
        catch (Exception e){e.printStackTrace();}

        return usersList;
    }
}
