package org.project.model;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Data
@Entity
@Table(name = "users")
public class UserModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  int id;

  @Column(name="login")
  String login;
 
  @Column(name="password")
  String password;

  public void setPassword (String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }  
}
