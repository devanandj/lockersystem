package com.devanand.lockersystem.locker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document
@CompoundIndexes({
    @CompoundIndex(name = "name_email_idx", def = "{'name': 1, 'email': 1}", unique = true)
})
public class User {


  @Id
  public  String id;

  public  String name;

  @Indexed(unique = true, name = "unique_email")
  public  String email;
}
