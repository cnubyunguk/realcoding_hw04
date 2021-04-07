package org.cnu.realcoding.homework0420.service;

import org.cnu.realcoding.homework0420.domain.Dog;
import org.cnu.realcoding.homework0420.exception.DogNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class DogManagementService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Dog insertDog(Dog body){
        if (getDog(body.getName(), body.getOwnerName(), body.getOwnerPhoneNumber()) != null)
            throw new DogNotFoundException();

        return mongoTemplate.insert(body);
    }

    public Dog getDog(String name, String ownerName, String ownerPhoneNumber){
        Query query = new Query().addCriteria(Criteria.where("name").is(name));
        query.addCriteria(Criteria.where("ownerName").is(ownerName));
        query.addCriteria(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber));
        return mongoTemplate.findOne(query, Dog.class);
    }

    public Dog getDogByName(String name){
        Query query = new Query().addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Dog.class);
    }

    public void updateDog(String name, Dog body) {
        // TODO: 데이터베이스에 두번 접근하지 않는 다른 방법이 있을지?..
        if (getDogByName(name) == null)
            throw new DogNotFoundException();

        Query query = new Query().addCriteria(Criteria.where("name").is(name));
        Update update = new Update().
                set("name", body.getName()).
                set("kind", body.getKind()).
                set("ownerName", body.getOwnerName()).
                set("ownerPhoneNumber", body.getOwnerPhoneNumber());

        mongoTemplate.updateFirst(query, update, Dog.class);
    }


    public void addMedicalRecord(String name, String record) {
        if (getDogByName(name) == null)
            throw new DogNotFoundException();

        Query query = new Query().addCriteria(Criteria.where("name").is(name));
        Update update = new Update().addToSet("medicalRecords",record);
        mongoTemplate.findAndModify(query,update, FindAndModifyOptions.options().upsert(true),Dog.class);
    }

    public Dog getDogByOwnerName(String ownerName) {
        Query query = new Query().addCriteria(Criteria.where("ownerName").is(ownerName));
        if (!mongoTemplate.exists(query, Dog.class))
            throw new DogNotFoundException();

        return mongoTemplate.findOne(query, Dog.class);
    }

    public Dog getDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        Query query = new Query().addCriteria(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber));
        if (!mongoTemplate.exists(query, Dog.class))
            throw new DogNotFoundException();

        return mongoTemplate.findOne(query, Dog.class);
    }
}