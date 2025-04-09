package com.devanand.lockersystem.locker.service;

import com.devanand.lockersystem.locker.model.Slot;
import com.devanand.lockersystem.locker.repository.ICustomSlotRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SlotService {


  private final ICustomSlotRepository slotRepository;

  private final ExecutorService executor = Executors.newFixedThreadPool(10);

  @Autowired
  public SlotService(ICustomSlotRepository slotRepository) {
    this.slotRepository = slotRepository;
  }

  public void addSlot(Slot s) {
    this.slotRepository.insert(s);
  }

  public Slot getSlot(String slotId) {
    return this.slotRepository.findById(slotId).orElse(null);
  }

  public CompletableFuture<List<Slot>> getAllSlots() {
    return CompletableFuture.supplyAsync( () -> this.slotRepository.findAll(),executor);
  }


  public List<Slot> getAllAvailableSlots() {
    return slotRepository.blhh();
  }
}
