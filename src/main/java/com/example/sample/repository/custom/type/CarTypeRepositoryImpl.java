package com.example.sample.repository.custom.type;

import com.example.sample.entity.CarType;
import com.example.sample.util.DatabaseConstants;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CarTypeRepositoryImpl implements CarTypeRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public long update(CarType entity) {
        Query query = new Query(Criteria.where(DatabaseConstants.CarType.UUID).is(entity.getUuid()));
        Update update = new Update();
        update
                .set(DatabaseConstants.CarType.NAME, entity.getName());
        UpdateResult updateResult = mongoOperations.updateFirst(query, update, CarType.class);
        return updateResult.getModifiedCount();
    }

}
