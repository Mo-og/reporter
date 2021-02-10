package com.practice3.reporter.Services;

import com.practice3.reporter.Entities.Coordinator;
import com.practice3.reporter.Repositories.UserRepository;
import com.practice3.reporter.Entities.User;
import com.practice3.reporter.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public List<User> getAllUsers() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    public User getById(long id) {
        return repository.getOne(id);
    }

    public void removeById(long id) {
        repository.deleteById(id);
    }

    public void removeByUsername(String username) {
        Optional<User> userOptional = repository.findByUsername(username);
        userOptional.ifPresent(user -> repository.delete(user));
    }

    public boolean existsWithId(long id) {
        return repository.existsById(id);
    }

    public boolean existsWithUsername(String username) {
        return repository.findByUsername(username).isPresent();
    }
    public User getByUsername(String username){
        return repository.findByUsername(username).orElse(null);
    }

    public void remove(User user){
        repository.delete(user);
    }

    @Override //для авторизации Spring Security
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        System.out.println(user);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return user.map(MyUserDetails::new).get();
    }
}
