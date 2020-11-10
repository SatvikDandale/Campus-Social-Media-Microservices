package com.campussocialmedia.postservice.proxies;

import java.util.Map;

import org.springframework.cloud.openfeign.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "gateway")
public interface AuthServiceProxy {

    @PostMapping(value = "/jwt")
    public ResponseEntity<Map<String, String>> decodeJwt(@RequestBody Map<String, String> reqBody);

}
