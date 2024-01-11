package es.uvigo.esei.dgss.teama.microstories.entities;

import es.uvigo.esei.dgss.teama.microstories.enums.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/** This class defines an entity Story */
@Entity
@Table(name = "story")
public class StoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_story")
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "text", length = 1000)
  private String text;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender")
  private Gender gender;

  @Enumerated(EnumType.STRING)
  @Column(name = "topic_one")
  private Topic topic1;

  @Enumerated(EnumType.STRING)
  @Column(name = "topic_two")
  private Topic topic2;

  @Column(name = "publication_date")
  private Date publicationDate;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "author", referencedColumnName = "login")
  private UserEntity author;

  @OneToMany(mappedBy = "story",cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<ViewEntity > views;
  public StoryEntity() {}

  public StoryEntity(
      Integer id,
      String title,
      String text,
      Gender gender,
      Topic topic1,
      Topic topic2,
      UserEntity author,
      Date publicationDate) {
    this.id = id;
    this.title = title;
    this.text = text;
    this.gender = gender;
    this.topic1 = topic1;
    this.topic2 = topic2;
    this.author = author;
    this.publicationDate = publicationDate;
    checkParams();
  }

  private void checkParams() {
    if (this.text == null) {
      throw new IllegalArgumentException("The Story needs a text");
    }
    if (Gender.STORY.equals(this.gender) && this.text.length() > 1000) {
      throw new IllegalArgumentException("Text length is too long");
    }
    if (Gender.POETRY.equals(this.gender) && this.text.length() > 500) {
      throw new IllegalArgumentException("Text length is too long");
    }
    if (Gender.NANO_STORY.equals(this.gender) && this.text.length() > 150) {
      throw new IllegalArgumentException("Text length is too long");
    }
    if (this.topic1 == null && this.topic2 == null) {
      throw new IllegalArgumentException("The Story needs at least one topic");
    }
    if (this.title == null || this.title.length() <= 2) {
      throw new IllegalArgumentException("The Story needs a title with at least 3 characteres");
    }
    if (this.author == null) {
      throw new IllegalArgumentException("The Story needs an author");
    }
  }

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

  public Gender getGender() {
    return gender;
  }

  public Topic getTopic1() {
    return topic1;
  }

  public Topic getTopic2() {
    return topic2;
  }

  public UserEntity getAuthor() {
    return author;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

  public List<ViewEntity> getViews() {return views;}
}
