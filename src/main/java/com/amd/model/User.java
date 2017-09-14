package com.amd.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by liduankang on 2017/9/13
 */
@Entity
public class User extends AbstractAuditingEntity {
  @Column(length = 10)
  private String userName;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
