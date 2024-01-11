package es.uvigo.esei.dgss.teama.microstories.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/**
 * Matcher for Story class.
 *
 * @author Nicolás Cid Gómez (ncgomez17)
 */
public class IsEqualToStoryEntity extends IsEqualToEntity<StoryEntity> {

  public IsEqualToStoryEntity(StoryEntity story) {
    super(story);
  }

  @Override
  protected boolean matchesSafely(StoryEntity actual) {
    this.clearDescribeTo();

    if (actual == null) {
      this.addTemplatedDescription("actual", expected.toString());
      return false;
    } else {
      return checkAttribute("text", StoryEntity::getText, actual)
          && checkAttribute("publicationdate", StoryEntity::getPublicationDate, actual)
          && checkAttribute("id", StoryEntity::getId, actual)
          && checkAttribute("title", StoryEntity::getTitle, actual)
          && checkAttribute("gender", StoryEntity::getGender, actual)
          && checkAttribute("topic1", StoryEntity::getTopic1, actual)
          && checkAttribute("topic2", StoryEntity::getTopic2, actual)
          && checkAttribute("author", storyEntity -> storyEntity.getAuthor().getLogin(), actual);
    }
  }

  @Factory
  public static IsEqualToStoryEntity equalToStory(StoryEntity story) {
    return new IsEqualToStoryEntity(story);
  }

  @Factory
  public static Matcher<Iterable<? extends StoryEntity>> containsStoriesInAnyOrder(
      StoryEntity... stories) {
    return containsEntityInAnyOrder(IsEqualToStoryEntity::equalToStory, stories);
  }

  @Factory
  public static Matcher<Iterable<? extends StoryEntity>> containsStoriesInAnyOrder(
      Iterable<StoryEntity> stories) {
    return containsEntityInAnyOrder(IsEqualToStoryEntity::equalToStory, stories);
  }

  @Factory
  public static Matcher<Iterable<? extends StoryEntity>> containsStoriesInOrder(
      Iterable<StoryEntity> stories) {
    return containsEntityInOrder(IsEqualToStoryEntity::equalToStory, stories);
  }
}
