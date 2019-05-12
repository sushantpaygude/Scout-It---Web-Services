package SpringBootPackage.entity.Users.dao;

import SpringBootPackage.entity.Users.models.Users;

import java.util.List;

public interface IUserDao {

    public int createUser(Users users);

    public void updateUser(Users users);

    public Users getUser(int userid);

    public List<Users> getAllUsers();

}
