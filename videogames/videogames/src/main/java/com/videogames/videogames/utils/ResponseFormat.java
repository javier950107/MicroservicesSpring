package com.videogames.videogames.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ResponseFormat {
    public Map<String,Object> getResponse(String reason, Object data){
        Map<String, Object> response = new HashMap<>();
        response.put("msg", reason);
        response.put("data", data);
        return response;
    }

    public Map<String,Object> handleErrors(BindingResult result){
        List<Map<String,Object>> errors = result.getFieldErrors().stream()
            .map(err ->{
                Map<String,Object> error = new HashMap<>();
                error.put("msg", err.getDefaultMessage());
                return error;
            }).collect(Collectors.toList());
        return errors.get(0);
    }
}
