package com.devanand.lockersystem.locker.repository;


import com.devanand.lockersystem.locker.model.Slot;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class CustomSlotRepositoryImpl implements CustomSlotRepository  {


  private final  MongoTemplate mongoTemplate;

  @Autowired
  public CustomSlotRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }


  @Override
  public List<Slot> blhh() {
    Query query = new Query();


    query.addCriteria(Criteria.where("alloc").in(Arrays.asList("fr")));
     query.addCriteria(Criteria.where("allocated").is(false)); // existing condition
    query.addCriteria(Criteria.where("status").is("active")); // additional condition

    query.with(Sort.by(Sort.Order.asc("size.weight")));

    return mongoTemplate.find(query, Slot.class);
  }
}
