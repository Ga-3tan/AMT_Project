package com.example.amtech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.example.amtech.repository"})
public class DBConfig extends AbstractCouchbaseConfiguration {

    @Override
    public String getConnectionString() {
        return "couchbase://127.0.0.1"; // on peut peut-être spécifier le chemin du bucket / collection ici
    }

    @Override
    public String getUserName() {
        return "Administrator";
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public String getBucketName() {
        return "amtech";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    // essayer de préciser la collection
    // voir si on peut faire une requête sur une collection autre que default
}
