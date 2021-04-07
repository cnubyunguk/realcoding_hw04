package org.cnu.realcoding.homework0420.repository;

import org.cnu.realcoding.homework0420.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Dog findDog(String ownerName) {
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("ownerName").is(ownerName)),
                        Dog.class);


    }





    public boolean exist(String name) {
        return mongoTemplate.exists(
                Query.query(Criteria.where("name").is(name)),
                Dog.class
        );
    }

    public void insertDog(Dog dog) {
        mongoTemplate.insert(dog);
    }

    public List<Dog> findAllDog() {
        return mongoTemplate.findAll(Dog.class);
    }
}


