package Java.Project.BigProject.services;


import Java.Project.BigProject.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User create(User user);
    User read(Long id);
    User findUserByIdentifierAndPassword(String identifier, String password);
    User update(User user);
    void delete(Long id);
}
