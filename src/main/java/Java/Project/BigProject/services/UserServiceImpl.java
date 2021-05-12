package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.User;
import Java.Project.BigProject.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        log.trace("FilmServiceImpl instanced ");
        this.repository = repository;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User create(User user) { return repository.save(user); }

    @Override
    public User read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) {
        return repository.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User findUserByEmailAndPassword(String mail, String password) {
        return repository.findByEmailEqualsAndPasswordEquals(mail, password);
    }

    public void addUser(String name, String surname, String identifier, String password, String email){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setIdentifier(identifier);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(0L);
        create(user);
    }
}
