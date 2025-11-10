package plants.finalexam.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import plants.finalexam.model.User;

public interface UserRepository extends JpaRepository <User, Integer>{

    public Optional<User> findByUsername(String username);
}
