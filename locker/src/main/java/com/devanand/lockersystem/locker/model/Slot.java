package com.devanand.lockersystem.locker.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Document("slot")
public class Slot {


  @Id
   String id;
   Size size;
  Date allocatedDate;

  @Indexed
  Boolean allocated;

}
