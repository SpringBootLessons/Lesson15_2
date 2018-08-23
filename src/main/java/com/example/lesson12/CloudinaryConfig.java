package com.example.lesson12;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryConfig {

  private Cloudinary cloudinary;

  @Autowired
  public CloudinaryConfig(@Value("${cloudinary.apikey}") String key,
                          @Value("${cloudinary.apisecret}") String secret,
                          @Value("${cloudinary.cloudname}") String cloud){
    cloudinary = Singleton.getCloudinary();
    cloudinary.config.cloudName=cloud;
    cloudinary.config.apiSecret=secret;
    cloudinary.config.apiKey=key;
  }

  public Map upload(Object file, Map options){
    try{
      return cloudinary.uploader().upload(file, options);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public String createUrl(String name) {
    return cloudinary.url().transformation( new Transformation().width(100)
            .height(100).crop("fill").radius(50).border(5, "green")
    ).generate(name);
  }
}
