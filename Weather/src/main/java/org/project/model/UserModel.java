package org.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Users")
public class UserModel {
  @Id
  int id;

  @Column(name="Login")
  String login;
 
  @Column(name="Password")
  String password;

  public void setPassword (String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }
  
}
