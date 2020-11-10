package com.campussocialmedia.postservice.entities;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedTimestamp;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Posts")
public class Post implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String postID;
    private String userName;
    private String caption;
    private String timeStamp;
    // The URL will be NULL if there is no file.
    private String url;

    public Post() {
    }

    @DynamoDBHashKey(attributeName = "postID")
    @DynamoDBAutoGeneratedKey
    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "UserIndex")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDBAttribute
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @DynamoDBAttribute
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @DynamoDBAttribute
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Post(String postID, String userName, String caption, String timeStamp, String url) {
        this.postID = postID;
        this.userName = userName;
        this.caption = caption;
        this.timeStamp = timeStamp;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Post [caption=" + caption + ", postID=" + postID + ", timeStamp=" + timeStamp + ", url=" + url
                + ", userName=" + userName + "]";
    }
}
