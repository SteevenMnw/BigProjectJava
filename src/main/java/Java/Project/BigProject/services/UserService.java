package Java.Project.BigProject.services;


import Java.Project.BigProject.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User create(User user);
    User read(Long id);
    User update(User user);
    void delete(Long id);
}
