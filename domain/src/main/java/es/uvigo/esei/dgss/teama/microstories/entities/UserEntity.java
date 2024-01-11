package es.uvigo.esei.dgss.teama.microstories.entities;

import javax.persistence.*;

/** This class defines an entity User */
@Entity
@Table(name = "user")
public class UserEntity {

  @Id
  @Column(length = 100, nullable = false, unique = true)
  private String login;

  @Column(length = 32, nullable = false)
  private String password;

  @Column(name = "role", insertable = false, updatable = false)
  private String role;

  public UserEntity() {}

  public UserEntity(String login, String password, String role) {
    this.login = login;
    this.password = password;
    this.role = role;
    checkParams();
  }

  UserEntity(String login) {
    this.login = login;
  }

  private void checkParams() {

    if (this.login == null || this.login.length() <= 2)
      throw new IllegalArgumentException("The user needs a login with at least 3 characteres");

    if (this.password == null || this.password.length() <= 2)
      throw new IllegalArgumentException("The user needs a password with at least 3 characteres");

    if (this.role == null || this.role.length() <= 2)
      throw new IllegalArgumentException("The user needs a role with at least 3 characteres");
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }
}
