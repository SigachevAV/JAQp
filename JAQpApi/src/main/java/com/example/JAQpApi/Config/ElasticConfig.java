package com.example.JAQpApi.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;

@Configuration
public class ElasticConfig extends ElasticsearchConfiguration
{
    @Override
    public ClientConfiguration clientConfiguration()
    {
        return  ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build();
    }
}
