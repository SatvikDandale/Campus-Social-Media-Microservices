package com.campussocialmedia.userservice.Proxies;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import feign.Headers;

@FeignClient(name = "media-service")
public interface MediaServiceProxy {

    @PostMapping(value = "/storage/uploadFile", consumes = "multipart/form-data")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file);

}
