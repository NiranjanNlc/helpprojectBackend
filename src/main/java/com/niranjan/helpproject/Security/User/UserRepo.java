package com.niranjan.helpproject.Security.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Customer, Long>
{

 Customer findCustomerByRid(String user);
}
