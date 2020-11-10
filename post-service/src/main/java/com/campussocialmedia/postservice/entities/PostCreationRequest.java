package com.campussocialmedia.postservice.entities;

import org.springframework.web.multipart.MultipartFile;

/*
* This is a POJO class for new post Request. It will then be converted to the actual Post class.
*/
public class PostCreationRequest {
    private String userName;
    private String caption;
    private MultipartFile file;
    // FILE will be null if the user does not upload file in the form-data

    public PostCreationRequest() {
    }

    public PostCreationRequest(String userName, String caption, MultipartFile file) {
        this.userName = userName;
        this.caption = caption;
        this.file = file;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
