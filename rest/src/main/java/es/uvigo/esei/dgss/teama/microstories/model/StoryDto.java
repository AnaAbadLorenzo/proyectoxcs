package es.uvigo.esei.dgss.teama.microstories.model;

import es.uvigo.esei.dgss.teama.microstories.entities.UserEntity;

import java.util.Objects;

public class StoryDto {

  private Integer id;

  private String title;

  private String text;

  private String gender;

  private String topic1;

  private String topic2;

  private String author;

  private String publicationDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getTopic1() {
    return topic1;
  }

  public void setTopic1(String topic1) {
    this.topic1 = topic1;
  }

  public String getTopic2() {
    return topic2;
  }

  public void setTopic2(String topic2) {
    this.topic2 = topic2;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(UserEntity author) {
    this.author = author.getLogin();
  }

  public String getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StoryDto storyDto = (StoryDto) o;
    return Objects.equals(id, storyDto.id)
        && Objects.equals(title, storyDto.title)
        && Objects.equals(text, storyDto.text)
        && Objects.equals(gender, storyDto.gender)
        && Objects.equals(topic1, storyDto.topic1)
        && Objects.equals(topic2, storyDto.topic2)
        && Objects.equals(author, storyDto.author)
        && Objects.equals(publicationDate, storyDto.publicationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, text, gender, topic1, topic2, author, publicationDate);
  }
}
