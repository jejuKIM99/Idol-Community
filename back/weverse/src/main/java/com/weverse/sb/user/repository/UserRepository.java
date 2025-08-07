package com.weverse.sb.user.repository;

<<<<<<< HEAD
=======
import java.util.Optional;

>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
<<<<<<< HEAD
=======
	
	boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df

}
