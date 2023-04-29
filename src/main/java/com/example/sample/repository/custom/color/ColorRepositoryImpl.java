package com.example.sample.repository.custom.color;

import com.example.sample.entity.Color;
import com.example.sample.util.DatabaseConstants;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ColorRepositoryImpl implements ColorRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public long update(Color entity) {
        Query query = new Query(Criteria.where(DatabaseConstants.Color.UUID).is(entity.getUuid()));
        Update update = new Update();
        update
                .set(DatabaseConstants.Color.NAME, entity.getName());
        UpdateResult updateResult = mongoOperations.updateFirst(query, update, Color.class);
        return updateResult.getModifiedCount();
    }

}
