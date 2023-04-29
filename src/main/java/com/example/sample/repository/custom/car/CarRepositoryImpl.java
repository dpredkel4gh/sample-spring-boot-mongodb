package com.example.sample.repository.custom.car;

import com.example.sample.entity.Car;
import com.example.sample.util.DatabaseConstants;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryImpl implements CarRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public long update(Car entity) {
        Query query = new Query(Criteria.where(DatabaseConstants.Car.UUID).is(entity.getUuid()));
        Update update = new Update();
        update
                .set(DatabaseConstants.Car.NAME, entity.getName())
                .set(DatabaseConstants.Car.TYPE, entity.getType())
                .set(DatabaseConstants.Car.COLOR, entity.getColor());
        UpdateResult updateResult = mongoOperations.updateFirst(query, update, Car.class);
        return updateResult.getModifiedCount();
    }

}
