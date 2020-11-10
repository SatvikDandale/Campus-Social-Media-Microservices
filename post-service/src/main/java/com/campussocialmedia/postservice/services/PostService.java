package com.campussocialmedia.postservice.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.campussocialmedia.postservice.entities.Post;
import com.campussocialmedia.postservice.entities.PostCreationRequest;
import com.campussocialmedia.postservice.proxies.AuthServiceProxy;
import com.campussocialmedia.postservice.proxies.MediaServiceProxy;
import com.campussocialmedia.postservice.repositories.PostRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MediaServiceProxy mediaService;

    @Autowired
    private AuthServiceProxy authService;

    private Post convertToPost(PostCreationRequest post) {
        return modelMapper.map(post, Post.class);
    }

    /*
     * Creating a Post is pretty straight-forward. Convert PostCreationRequest
     * object to the actual Post Object and then save it in the database. But we
     * also need to check if the user has provided a file in the CreationObject. If
     * yes, we need to upload it using "MediaService" and add the URL into the new
     * Post object.
     */
    public Post addPost(PostCreationRequest post, String token) {

        Map<String, String> reqBody = new HashMap<String, String>();
        reqBody.put("token", token);
        ResponseEntity<Map<String, String>> response = authService.decodeJwt(reqBody);
        String userName = response.getBody().get("userName");
        post.setUserName(userName);

        Post convertedPost = convertToPost(post);
        if (post.getFile() != null) {
            // Upload this file.
            String url = mediaService.uploadFile(post.getFile());
            convertedPost.setUrl(url);
        }
        return repository.addPost(convertedPost);
    }

    public Post findPostByID(String postID) {
        return repository.findPostByID(postID);
    }

    public List<Post> findPostsByUserName(String userName) {
        return repository.findPostsByUserName(userName);
    }

}
