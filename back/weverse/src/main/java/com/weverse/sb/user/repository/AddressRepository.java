package com.weverse.sb.user.repository;

import com.weverse.sb.user.entity.Address;
import com.weverse.sb.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByUserOrderByCreatedAtDesc(User user);

    Optional<Address> findByUserAndIsDefaultTrue(User user);
}
