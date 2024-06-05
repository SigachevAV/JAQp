package com.example.JAQpApi.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@Setter
@ConfigurationProperties(prefix = "minio")
public class MinioProperties
{
    private String url;
    private String accessKey;
    private String secretKey;
}