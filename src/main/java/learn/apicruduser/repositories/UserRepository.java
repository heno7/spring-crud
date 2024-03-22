package learn.apicruduser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.apicruduser.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
