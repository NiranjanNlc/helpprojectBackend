package com.niranjan.helpproject.HelpedOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HelpedService
{
    @Autowired
    HelpedRepo repository;

    public List<HelpedOne> getAllUsers()
    {
        List<HelpedOne> userList = repository.findAll();

        if(userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<HelpedOne>();
        }
    }

    public HelpedOne getUserById(Long id)
    {
        Optional<HelpedOne> user= repository.findById(id);

        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public HelpedOne createOrUpdateUser(HelpedOne entity) {
        Optional<HelpedOne> user= repository.findById(entity.getId());

        if(user.isPresent())
        {
            HelpedOne newEntity = user.get();
            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }

    public void deleteUserById(Long id)  
    {
        Optional<HelpedOne> user= repository.findById(id);

        if(user.isPresent())
        {
            repository.deleteById(id);
        }  
        return ;
    
}
}
