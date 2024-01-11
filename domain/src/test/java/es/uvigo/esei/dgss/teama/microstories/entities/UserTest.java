package es.uvigo.esei.dgss.teama.microstories.entities;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This class defines test cases for the UserEntity class. It validates the behavior of the
 * UserEntity class methods and constructors.
 */
public class UserTest {

  String login, password, role;

  /**
   * Set up method for testing.
   *
   * @throws Exception If there is an issue during setup.
   */
  @Before
  public void setUp() throws Exception {
    this.login = "user1";
    this.password = "user1pass";
    this.role = "user";
  }

  /** Test case for creating a UserEntity with valid parameters. */
  @Test
  public void testUserOk() {
    final UserEntity user = new UserEntity(login, password, role);
    assertThat(user.getLogin(), is(equalTo(login)));
    assertThat(user.getPassword(), is(equalTo(password)));
    assertThat(user.getRole(), is(equalTo(role)));
  }

  /**
   * Test case for creating a UserEntity with an invalid login. Expects IllegalArgumentException to
   * be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUserWithInvalidLogin() {
    this.login = "ab";
    new UserEntity(login, password, role);
  }

  /**
   * Test case for creating a UserEntity with an invalid password. Expects IllegalArgumentException
   * to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUserWithInvalidPassword() {
    this.password = "12";
    new UserEntity(login, password, role);
  }

  /**
   * Test case for creating a UserEntity with an invalid role. Expects IllegalArgumentException to
   * be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUserWithInvalidRole() {
    this.role = "ab";
    new UserEntity(login, password, role);
  }
}
