package com.campussocialmedia.userservice.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.campussocialmedia.userservice.entity.User;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public User addUser(User user) {
        mapper.save(user);
        return user;
    }

    public User findUserByIdAndUserName(Long userId, String userName) {
        return mapper.load(User.class, userName);
    }

    public User findUserByUserName(String userName) {
        return mapper.load(User.class, userName);
    }

    public String deleteUser(User user) {
        mapper.delete(user);
        return "User Deleted";
    }

    public String updateUser(User user) {
        mapper.save(user, buildExpression(user));
        System.out.print("FOLLOWERS ADDED");
        return "User Updated";
    }

    public User updateUserAboutDetails(User user) {
        mapper.save(user, buildExpression(user));
        return mapper.load(User.class, user.getUserName());

    }

    private DynamoDBSaveExpression buildExpression(User user) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("userName", new ExpectedAttributeValue(new AttributeValue().withS(user.getUserName())));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
