package com.escion.climbingweathercollector.model.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "")
public class Crag {
    @DynamoDBHashKey
    String key;
}
