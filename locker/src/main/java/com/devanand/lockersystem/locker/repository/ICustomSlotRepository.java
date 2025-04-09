package com.devanand.lockersystem.locker.repository;

import com.devanand.lockersystem.locker.model.Slot;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ICustomSlotRepository extends MongoRepository<Slot, String>, CustomSlotRepository {

  @Query("{'allocated':false}")
  List<Slot> getAllAvailableSlots();
}
