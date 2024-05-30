package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public void saveUser(User user);

    public User getUserById(long id);

    public void deleteUser(long id);
}
