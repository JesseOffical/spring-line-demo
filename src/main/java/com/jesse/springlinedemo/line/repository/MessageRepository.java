package com.jesse.springlinedemo.line.repository;

import com.jesse.springlinedemo.line.model.LineMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<LineMessage,String> {

    List<LineMessage> findAllByUserId(String userId);

}
