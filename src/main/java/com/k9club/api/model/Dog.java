package com.k9club.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Dog {

  protected int id;
  protected String name;
  protected Date birthday;
  protected String gender;
  protected Date createdAt;
}
