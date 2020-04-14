package com.niranjan.helpproject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    UserRepo repository;

    public List<Customer> getAllUsers()
    {
        List<Customer> customerList = repository.findAll();

        if(customerList.size() > 0) {
            return customerList;
        } else {
            return new ArrayList<Customer>();
        }
    }

    public Customer getUserById(Long id)
    {
        Optional<Customer> user= repository.findById(id);

        if(user.isPresent()) {
            return user.get();
        }  
        return null;
    }

    public Customer createOrUpdateUser(Customer entity) {
        Optional<Customer> user= repository.findById(entity.getId());

        if(user.isPresent())
        {
            Customer newEntity = user.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());

            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }

    public void deleteUserById(Long id)  
    {
        Optional<Customer> user= repository.findById(id);

        if(user.isPresent())
        {
            repository.deleteById(id);
        }  
        return ;
    
}
}
