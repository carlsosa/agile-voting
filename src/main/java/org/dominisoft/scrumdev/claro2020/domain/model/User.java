package org.dominisoft.scrumdev.claro2020.domain.model;

/**
 * dsfdsfasdf.
 * 
 * @author scrumdev
 *
 */
public class User {

  public String password;
  public String username;

  /**
   * asdfdsf.
   * 
   * @param psw  asdfsdaf.
   * @param user adsfsadf.
   */
  public User(String psw, String user) {
    if (psw == null || user == null || psw.isBlank() || user.isBlank()) {
      throw new IllegalArgumentException();
    }
    this.password = psw;
    this.username = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}