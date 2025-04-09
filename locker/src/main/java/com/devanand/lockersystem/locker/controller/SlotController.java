package com.devanand.lockersystem.locker.controller;

import com.devanand.lockersystem.locker.model.Slot;
import com.devanand.lockersystem.locker.service.SlotService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slot")
public class SlotController {



  private final SlotService slotService;

  @Autowired
  public SlotController(SlotService slotService) {
    this.slotService = slotService;
  }

  @PostMapping
  public ResponseEntity<Object> addSlot(@RequestBody  Slot s) {
    this.slotService.addSlot(s);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/{id}")
  public Slot getSlot(@PathVariable String id) {
    return slotService.getSlot(id);
  }

  @GetMapping
  public CompletableFuture<List<Slot>> getAllSlots() {
    return slotService.getAllSlots();
  }
}
