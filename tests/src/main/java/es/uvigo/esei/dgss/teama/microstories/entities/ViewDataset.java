package es.uvigo.esei.dgss.teama.microstories.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ViewDataset {
  public static ViewEntity[] views(StoryEntity[] stories) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      ViewEntity[] views =
          new ViewEntity[] {
            new ViewEntity(stories[0], formatter.parse("2023-11-18 12:00:00")),
            new ViewEntity(stories[0], formatter.parse("2023-11-18 13:00:00")),
            new ViewEntity(stories[1], formatter.parse("2023-11-18 14:00:00")),
            new ViewEntity(stories[2], formatter.parse("2023-11-18 15:00:00")),
            new ViewEntity(stories[2], formatter.parse("2023-11-18 16:00:00")),
            new ViewEntity(stories[2], formatter.parse("2023-11-18 17:00:00")),
            new ViewEntity(stories[2], formatter.parse("2022-11-18 18:00:00")),
            new ViewEntity(stories[0], formatter.parse("2023-11-18 19:00:00")),
            new ViewEntity(stories[3], formatter.parse("2023-11-18 19:00:00")),
            new ViewEntity(stories[6], formatter.parse("2023-11-18 19:00:00")),
            new ViewEntity(stories[5], formatter.parse("2023-11-18 19:00:00"))
          };
      return views;
    } catch (ParseException | IllegalArgumentException e) {
      e.printStackTrace();
      return null;
    }
  }
}
