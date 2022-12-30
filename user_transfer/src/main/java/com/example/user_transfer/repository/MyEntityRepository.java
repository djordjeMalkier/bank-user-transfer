package com.example.user_transfer.repository;

import com.example.user_transfer.model.ApplicationLog;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MyEntityRepository extends MongoRepository<ApplicationLog,String> {



}
