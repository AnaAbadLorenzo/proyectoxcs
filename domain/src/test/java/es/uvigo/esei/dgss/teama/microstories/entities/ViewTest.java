package es.uvigo.esei.dgss.teama.microstories.entities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.uvigo.esei.dgss.teama.microstories.enums.Gender;
import es.uvigo.esei.dgss.teama.microstories.enums.Topic;

/**
 * This class defines test cases for the ViewEntity class. It validates the behavior of the
 * ViewEntity class methods and constructors.
 */
public class ViewTest {
  private StoryEntity story;
  private Date viewDate;

  /**
   * Set up method for testing.
   *
   * @throws Exception If there is an issue.
   */
  @Before
  public void setUp() throws Exception {
    UserEntity author = new UserEntity("user1", "user1pass", "user");
    this.story =
        new StoryEntity(
            1, "test", "test", Gender.STORY, Topic.HISTORY, Topic.ROMANCE, author, new Date());
    this.viewDate = new Date();
  }

  /**
   * Tests ViewEntity with valid parameters. It checks if the story and view date match the expected
   * values.
   */
  @Test
  public void testViewOk() {
    final ViewEntity view = new ViewEntity(story, viewDate);

    assertThat(view.getStory(), is(equalTo(story)));
    assertThat(view.getViewDate(), is(equalTo(viewDate)));
  }

  /** Tests ViewEntity with a {@code null} story. Expects an IllegalArgumentException. */
  @Test(expected = IllegalArgumentException.class)
  public void testViewStoryNull() {
    new ViewEntity(null, viewDate);
  }

  /** Tests ViewEntity with a {@code null} view date. Expects an IllegalArgumentException. */
  @Test(expected = IllegalArgumentException.class)
  public void testViewViewDateNull() {
    new ViewEntity(story, null);
  }
}
