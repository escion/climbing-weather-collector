package com.escion.climbingweathercollector.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.escion.climbingweathercollector.repository")
public class DynamoDBConfiguration {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;
    @Value("${amazon.dynamodb.region}")
    private String amazonDynamoDBRegion;
    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }
    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    /**
     * TODO add credentials for deployed version on aws
     * @return
     */
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
                .build();
    }
    @Bean
    public DynamoDB dynamoDB() {
        return new DynamoDB(amazonDynamoDB());
    }
}
