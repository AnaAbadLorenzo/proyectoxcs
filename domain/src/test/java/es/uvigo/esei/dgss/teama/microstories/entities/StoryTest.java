package es.uvigo.esei.dgss.teama.microstories.entities;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import es.uvigo.esei.dgss.teama.microstories.enums.Gender;
import es.uvigo.esei.dgss.teama.microstories.enums.Topic;

/*
 * This class defines all the tests for entity Story
 */
public class StoryTest {
  private String title;
  private String text;
  private Gender gender;
  private Topic topic1;
  private Topic topic2;
  private UserEntity author;
  private Date publicationDate;

  @Before
  public void setUp() throws Exception {

    this.title = "Titulo";
    this.text = "Contenido";
    this.gender = Gender.STORY;
    this.topic1 = Topic.ADVENTURE;
    this.topic2 = Topic.HORROR;
    this.author = new UserEntity("user1", "user1pass", "user");
    this.publicationDate = new Date();
  }

  @Test
  public void testStoryOk() {
    final StoryEntity story =
        new StoryEntity(1, title, text, gender, topic1, topic2, author, publicationDate);

    assertThat(story.getTitle(), is(equalTo(title)));
    assertThat(story.getText(), is(equalTo(text)));
    assertThat(story.getGender(), is(equalTo(gender)));
    assertThat(story.getTopic1(), is(equalTo(topic1)));
    assertThat(story.getTopic2(), is(equalTo(topic2)));
    assertThat(story.getAuthor(), is(equalTo(author)));
    assertThat(story.getPublicationDate(), is(equalTo(publicationDate)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoryTitleNull() {
    new StoryEntity(1, null, text, gender, topic1, topic2, author, publicationDate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoryTitleTooShort() {
    new StoryEntity(1, "a", text, gender, topic1, topic2, author, publicationDate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoryTextNull() {
    new StoryEntity(1, title, null, gender, topic1, topic2, author, publicationDate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoryTextTooLongWhenStory() {
    String longText = getLongString(1003);
    new StoryEntity(1, title, longText, Gender.STORY, topic1, topic2, author, publicationDate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoryTextTooLongWhenPoetry() {
    String longText = getLongString(503);
    new StoryEntity(1, title, longText, Gender.POETRY, topic1, topic2, author, publicationDate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoryTextTooLongWhenNanoStory() {
    String longText = getLongString(153);
    new StoryEntity(1, title, longText, Gender.NANO_STORY, topic1, topic2, author, publicationDate);
  }

  private String getLongString(Integer length) {
    String toret = "";
    for (int i = 0; i < length; i++) {
      toret += "a";
    }
    return toret;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoryAuthorNull() {
    new StoryEntity(1, title, text, gender, topic1, topic2, null, publicationDate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoryTopicNotNull() {
    new StoryEntity(1, title, text, gender, null, null, author, publicationDate);
  }
}
