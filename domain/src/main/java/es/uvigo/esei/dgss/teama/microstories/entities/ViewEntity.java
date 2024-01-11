package es.uvigo.esei.dgss.teama.microstories.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** This class defines an entity representing views on stories. */
@Entity
@Table(name = "view")
public class ViewEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_view")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_story", nullable = false)
  private StoryEntity story;

  @Column(name = "view_date", nullable = false)
  private Date viewDate;

  public ViewEntity() {}

  /**
   * Creates a new instance of {@code ViewEntity}.
   *
   * @param story story identifies the StoryEntity. This parameter must be a non {@code null}.
   * @param viewDate date when the StoryEntity was viewed. This parameter must be a non {@code
   *     null}.
   * @throws IllegalArgumentException if value provided for any parameter is {@code null}.
   */
  public ViewEntity(StoryEntity story, Date viewDate) {
    this.story = story;
    this.viewDate = viewDate;
    checkParams();
  }

  /**
   * Checks the validity of parameters. Throws IllegalArgumentException if parameters are invalid.
   */
  private void checkParams() {
    if (this.story == null) {
      throw new IllegalArgumentException("The View needs a Story");
    }
    if (this.viewDate == null) {
      throw new IllegalArgumentException("The View needs a Date of View");
    }
  }

  /**
   * Return the ID of the view.
   *
   * @return The ID of the view.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Return the StoryEntity associated with this view.
   *
   * @return The StoryEntity.
   */
  public StoryEntity getStory() {
    return story;
  }

  /**
   * Return the date when the StoryEntity was viewed.
   *
   * @return The date of the view.
   */
  public Date getViewDate() {
    return viewDate;
  }
}
