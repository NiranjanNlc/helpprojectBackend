package com.niranjan.helpproject.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Customer, Long> {


}
