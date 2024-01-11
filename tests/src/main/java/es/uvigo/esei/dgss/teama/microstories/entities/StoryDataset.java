package es.uvigo.esei.dgss.teama.microstories.entities;

import es.uvigo.esei.dgss.teama.microstories.enums.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static es.uvigo.esei.dgss.teama.microstories.entities.ViewDataset.views;
import static java.util.Arrays.stream;

public class StoryDataset {
  public static final int EXISTENT_STORY_ID = 3;
  public static final int NON_EXISTENT_STORY_ID = -1;
  public static final int MAX_RESULTS = 10;
  public static final String EXISTENT_TEXT = "Night";
  public static final String START_DATE = "2021-04-02 23:59:59";
  public static final String END_DATE = "2022-04-03 00:00:01";
  public static final String EXPLORED_GENDER = "NANO_STORY";
  public static final String EXPLORED_TOPIC = "CHILDREN";

  public static StoryEntity storyWithId(int id) {
    return stream(stories())
        .filter(story -> story.getId().equals(id))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  public static StoryEntity[] storiesWithText(String text) {
    return Arrays.stream(stories())
        .filter(
            story ->
                story.getText().toLowerCase().contains(text.toLowerCase())
                    || (story.getTitle().toLowerCase().contains(text.toLowerCase())))
        .toArray(StoryEntity[]::new);
  }

  public static StoryEntity[] storiesWithParams(
      Gender gender, Topic topic, Date startDate, Date endDate, Integer maxResults) {
    return Arrays.stream(stories())
        .filter(
            story ->
                story.getGender() == gender
                    && (story.getTopic1() == topic || story.getTopic2() == topic)
                    && story.getPublicationDate() != null
                    && isDateInRange(story.getPublicationDate(), startDate, endDate))
        .limit(maxResults != null ? maxResults : Long.MAX_VALUE)
        .toArray(StoryEntity[]::new);
  }

  private static boolean isDateInRange(Date date, Date startDate, Date endDate) {
    return date.after(startDate) && date.before(endDate);
  }

  public static StoryEntity existentStory() {
    return storyWithId(EXISTENT_STORY_ID);
  }

  public static StoryEntity[] existentText() {
    return storiesWithText(EXISTENT_TEXT);
  }

  public static StoryEntity[] exploredStories() {
    Date startDateParsed = parseDate(START_DATE);
    Date endDateParsed = parseDate(END_DATE);

    Gender genderParsed = Gender.formatToGender(EXPLORED_GENDER);
    Topic topicParsed = Topic.formatToTopic(EXPLORED_TOPIC);

    return storiesWithParams(
        genderParsed, topicParsed, startDateParsed, endDateParsed, MAX_RESULTS);
  }

  public static StoryEntity[] getAllStoriesByMaxResults() {
    return Arrays.stream(stories())
        .filter(story -> story.getPublicationDate() != null)
        .sorted(Comparator.comparing(StoryEntity::getPublicationDate).reversed())
        .limit(10)
        .toArray(StoryEntity[]::new);
  }

  public static StoryEntity[] stories() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      StoryEntity[] stories =
          new StoryEntity[] {
            new StoryEntity(
                1,
                "Sliding Doors",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.HORROR,
                Topic.HISTORY,
                new UserEntity("efrem", "efrempass", "user"),
                formatter.parse("2023-06-23 00:00:00")),
            new StoryEntity(
                2,
                "Maurice",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.ADVENTURE,
                null,
                new UserEntity("sada", "sadapass", "user"),
                formatter.parse("2022-03-01 00:00:00")),
            new StoryEntity(
                3,
                "Metrobranding",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.HISTORY,
                Topic.ROMANCE,
                new UserEntity("beverley", "beverleypass", "user"),
                formatter.parse("2019-06-02 00:00:00")),
            new StoryEntity(
                4,
                "Mirror, The (Ayneh)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.CHILDREN,
                Topic.ADVENTURE,
                new UserEntity("gail", "gailpass", "user"),
                formatter.parse("2021-06-25 00:00:00")),
            new StoryEntity(
                5,
                "Mirror, The (Ayneh)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.CHILDREN,
                Topic.ADVENTURE,
                new UserEntity("konstantine", "konstantinepass", "user"),
                null),
            new StoryEntity(
                6,
                "Tale of Two Cities, A",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.HORROR,
                Topic.ADVENTURE,
                new UserEntity("holmes", "holmespass", "user"),
                formatter.parse("2023-10-13 00:00:00")),
            new StoryEntity(
                7,
                "Bread and Chocolate (Pane e cioccolata)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.HISTORY,
                null,
                new UserEntity("fritz", "fritzpass", "admin"),
                formatter.parse("2023-09-24 00:00:00")),
            new StoryEntity(
                8,
                "Skyscraper (Skyskraber)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.HORROR,
                Topic.SCIENCE,
                new UserEntity("kare", "karepass", "user"),
                formatter.parse("2021-05-25 00:00:00")),
            new StoryEntity(
                9,
                "Charlotte Sometimes",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.HORROR,
                Topic.SUSPENSE,
                new UserEntity("alonzo", "alonzopass", "user"),
                formatter.parse("2019-08-18 00:00:00")),
            new StoryEntity(
                10,
                "Our Dancing Daughters",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.ROMANCE,
                Topic.SUSPENSE,
                new UserEntity("tobin", "tobinpass", "admin"),
                formatter.parse("2021-11-16 00:00:00")),
            new StoryEntity(
                11,
                "Queen Margot (Reine Margot, La)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.SCIENCE,
                null,
                new UserEntity("carola", "carolapass", "user"),
                formatter.parse("2022-03-21 00:00:00")),
            new StoryEntity(
                12,
                "Green Ray, The (Rayon vert, Le)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.SCIENCE,
                Topic.CHILDREN,
                new UserEntity("papageno", "papagenopass", "user"),
                null),
            new StoryEntity(
                13,
                "Private Lives of Elizabeth and Essex, The",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.SCIENCE,
                null,
                new UserEntity("nari", "naripass", "user"),
                formatter.parse("2019-09-19 00:00:00")),
            new StoryEntity(
                14,
                "Jim Jefferies: Alcoholocaust",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.SCIENCE,
                Topic.ROMANCE,
                new UserEntity("witty", "wittypass", "user"),
                formatter.parse("2023-08-02 00:00:00")),
            new StoryEntity(
                15,
                "When Dinosaurs Ruled the Earth",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.HISTORY,
                Topic.ADVENTURE,
                new UserEntity("tyrus", "tyruspass", "user"),
                null),
            new StoryEntity(
                16,
                "Wetlands (Feuchtgebiete)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.SCIENCE,
                Topic.HORROR,
                new UserEntity("xylia", "xyliapass", "user"),
                formatter.parse("2022-02-04 00:00:00")),
            new StoryEntity(
                17,
                "Downhill",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.HORROR,
                null,
                new UserEntity("corey", "coreypass", "user"),
                formatter.parse("2023-09-01 00:00:00")),
            new StoryEntity(
                18,
                "Uphill",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.SUSPENSE,
                Topic.CHILDREN,
                new UserEntity("mellie", "melliepass", "user"),
                formatter.parse("2022-06-13 00:00:00")),
            new StoryEntity(
                19,
                "City Heat",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.HISTORY,
                Topic.HORROR,
                new UserEntity("fletcher", "fletcherpass", "user"),
                formatter.parse("2022-11-30 00:00:00")),
            new StoryEntity(
                20,
                "Tormented",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.CHILDREN,
                Topic.HORROR,
                new UserEntity("jordana", "jordanapass", "user"),
                null),
            new StoryEntity(
                21,
                "Condemned, The",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.CHILDREN,
                Topic.SCIENCE,
                new UserEntity("pauly", "paulypass", "user"),
                null),
            new StoryEntity(
                22,
                "Sheepman, The",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.HISTORY,
                null,
                new UserEntity("gabriella", "grabiellapass", "user"),
                formatter.parse("2021-12-07 00:00:00")),
            new StoryEntity(
                23,
                "Two Cents Worth of Hope (Due soldi di speranza)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.ROMANCE,
                null,
                new UserEntity("corliss", "corlisspass", "user"),
                null),
            new StoryEntity(
                24,
                "Encounter in the Third Dimension",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.CHILDREN,
                Topic.ROMANCE,
                new UserEntity("jayson", "jaysonpass", "user"),
                null),
            new StoryEntity(
                25,
                "Night Train To Lisbon",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.CHILDREN,
                null,
                new UserEntity("nevins", "nevinspass", "user"),
                formatter.parse("2021-04-03 00:00:00")),
            new StoryEntity(
                26,
                "Paper Lion",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.CHILDREN,
                Topic.ADVENTURE,
                new UserEntity("celine", "celinepass", "user"),
                formatter.parse("2023-01-26 00:00:00")),
            new StoryEntity(
                27,
                "Bye-Bye",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.ROMANCE,
                Topic.SUSPENSE,
                new UserEntity("emmalee", "emmaleepass", "user"),
                null),
            new StoryEntity(
                28,
                "Runaway Jury",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.ROMANCE,
                Topic.HORROR,
                new UserEntity("ronni", "ronnipass", "user"),
                null),
            new StoryEntity(
                29,
                "Big Bounce, The",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.ADVENTURE,
                Topic.ROMANCE,
                new UserEntity("dukie", "dukiepass", "user"),
                formatter.parse("2023-08-03 00:00:00")),
            new StoryEntity(
                30,
                "Scandal Sheet",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.SUSPENSE,
                Topic.HISTORY,
                new UserEntity("joni", "jonipass", "user"),
                formatter.parse("2020-07-06 00:00:00")),
            new StoryEntity(
                31,
                "Your Life in 65 (Tu vida en 65')",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.SCIENCE,
                Topic.HISTORY,
                new UserEntity("astra", "astrapass", "user"),
                formatter.parse("2022-04-05 00:00:00")),
            new StoryEntity(
                32,
                "Island at War",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.SCIENCE,
                Topic.ADVENTURE,
                new UserEntity("jarvis", "jarvispass", "user"),
                formatter.parse("2022-09-11 00:00:00")),
            new StoryEntity(
                33,
                "Heart Is Deceitful Above All Things, The",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.ADVENTURE,
                Topic.SUSPENSE,
                new UserEntity("jobi", "jobipass", "user"),
                formatter.parse("2019-05-17 00:00:00")),
            new StoryEntity(
                34,
                "Rebecca",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.ROMANCE,
                Topic.CHILDREN,
                new UserEntity("janeen", "janeenpass", "user"),
                formatter.parse("2023-02-21 00:00:00")),
            new StoryEntity(
                35,
                "Alamo, The",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.CHILDREN,
                Topic.SCIENCE,
                new UserEntity("mikael", "mikaelpass", "user"),
                null),
            new StoryEntity(
                36,
                "Eddie and the Cruisers II: Eddie Lives!",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.HORROR,
                Topic.CHILDREN,
                new UserEntity("maegan", "maeganpass", "user"),
                formatter.parse("2021-03-24 00:00:00")),
            new StoryEntity(
                37,
                "Lucky Luke: The Ballad of the Daltons",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.ADVENTURE,
                Topic.SCIENCE,
                new UserEntity("deina", "deinaepass", "user"),
                formatter.parse("2019-12-11 00:00:00")),
            new StoryEntity(
                38,
                "Puff, Puff, Pass",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.HORROR,
                null,
                new UserEntity("maryjane", "maryjanepass", "user"),
                formatter.parse("2020-07-17 00:00:00")),
            new StoryEntity(
                39,
                "Female Perversions",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.HISTORY,
                Topic.ROMANCE,
                new UserEntity("bab", "babpass", "user"),
                formatter.parse("2021-06-10 00:00:00")),
            new StoryEntity(
                40,
                "Aberdeen",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.CHILDREN,
                null,
                new UserEntity("kevyn", "kevynpass", "user"),
                null),
            new StoryEntity(
                41,
                "Electric Shadows (Meng ying tong nian)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.SUSPENSE,
                Topic.CHILDREN,
                new UserEntity("eugen", "eugenpass", "user"),
                null),
            new StoryEntity(
                42,
                "Sunset",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.STORY,
                Topic.HISTORY,
                Topic.ROMANCE,
                new UserEntity("lily", "lilypass", "user"),
                formatter.parse("2019-12-17 00:00:00")),
            new StoryEntity(
                43,
                "Caddyshack II",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.ADVENTURE,
                Topic.ROMANCE,
                new UserEntity("costa", "costapass", "user"),
                formatter.parse("2020-09-06 00:00:00")),
            new StoryEntity(
                44,
                "Playing from the Plate (Grajacy z talerza)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.HORROR,
                Topic.SUSPENSE,
                new UserEntity("otto", "ottopass", "user"),
                formatter.parse("2022-12-09 00:00:00")),
            new StoryEntity(
                45,
                "Vanya on 42nd Street",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.SCIENCE,
                Topic.SUSPENSE,
                new UserEntity("mylo", "mylopass", "user"),
                formatter.parse("2021-12-10 00:00:00")),
            new StoryEntity(
                46,
                "Mantle",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.SCIENCE,
                null,
                new UserEntity("juditha", "juditha", "user"),
                formatter.parse("2021-01-01 00:00:00")),
            new StoryEntity(
                47,
                "The Beehive",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.CHILDREN,
                Topic.SUSPENSE,
                new UserEntity("langston", "langstonpass", "user"),
                formatter.parse("2020-07-29 00:00:00")),
            new StoryEntity(
                48,
                "One Fine Day",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.SCIENCE,
                null,
                new UserEntity("lammond", "lammondpass", "user"),
                formatter.parse("2023-07-16 00:00:00")),
            new StoryEntity(
                49,
                "Bakhita",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.ROMANCE,
                Topic.HORROR,
                new UserEntity("imogen", "imogenpass", "user"),
                formatter.parse("2021-07-31 00:00:00")),
            new StoryEntity(
                50,
                "Trojan Eddie",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.SCIENCE,
                Topic.SUSPENSE,
                new UserEntity("gardner", "gardnerpass", "user"),
                null),
          };
      return stories;
    } catch (ParseException | IllegalArgumentException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static StoryEntity[] recentStories() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      StoryEntity[] stories =
          new StoryEntity[] {
            new StoryEntity(
                6,
                "Tale of Two Cities, A",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.HORROR,
                Topic.ADVENTURE,
                new UserEntity("holmes", "holmespass", "user"),
                formatter.parse("2023-10-13 00:00:00")),
            new StoryEntity(
                7,
                "Bread and Chocolate (Pane e cioccolata)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.HISTORY,
                null,
                new UserEntity("fritz", "fritzpass", "admin"),
                formatter.parse("2023-09-24 00:00:00")),
            new StoryEntity(
                17,
                "Downhill",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.HORROR,
                null,
                new UserEntity("corey", "coreypass", "user"),
                formatter.parse("2023-09-01 00:00:00")),
            new StoryEntity(
                29,
                "Big Bounce, The",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.ADVENTURE,
                Topic.ROMANCE,
                new UserEntity("dukie", "dukiepass", "user"),
                formatter.parse("2023-08-03 00:00:00")),
            new StoryEntity(
                14,
                "Jim Jefferies: Alcoholocaust",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.NANO_STORY,
                Topic.SCIENCE,
                Topic.ROMANCE,
                new UserEntity("witty", "wittypass", "user"),
                formatter.parse("2023-08-02 00:00:00")),
            new StoryEntity(
                48,
                "One Fine Day",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere urna scelerisque, elementum elit nec, egestas quam. Vivamus rhoncus.",
                Gender.POETRY,
                Topic.SCIENCE,
                null,
                new UserEntity("lammond", "lammondpass", "user"),
                formatter.parse("2023-07-16 00:00:00")),
          };
      return stories;
    } catch (ParseException | IllegalArgumentException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static StoryEntity[] mostViewedStories() {
    try {
      StoryEntity[] stories = stories();
      ViewEntity[] views = views(stories);
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.MONTH, -1);
      Date lastMonth = cal.getTime();

      // Group views by story
      Map<StoryEntity, List<ViewEntity>> viewsByStory =
          Arrays.stream(views)
              .filter(e -> e.getViewDate().after(lastMonth))
              .collect(Collectors.groupingBy(ViewEntity::getStory));

      // Count the views for each story
      Map<StoryEntity, Long> viewsCountByStory =
          viewsByStory.entrySet().stream()
              .collect(
                  Collectors.toMap(Map.Entry::getKey, entry -> (long) entry.getValue().size()));

      // Group stories by genre
      Map<Gender, List<StoryEntity>> storiesByGenre =
          Arrays.stream(stories).collect(Collectors.groupingBy(StoryEntity::getGender));

      // Get the top two stories with the most views for each genre
      Map<Gender, List<StoryEntity>> topTwoStoriesByGenre =
          storiesByGenre.entrySet().stream()
              .collect(
                  Collectors.toMap(
                      Map.Entry::getKey,
                      entry ->
                          entry.getValue().stream()
                              .filter(e -> viewsCountByStory.containsKey(e))
                              .sorted(
                                  Comparator.comparing(StoryEntity::getPublicationDate).reversed())
                              .sorted(
                                  (s1, s2) -> {
                                    long s1Views = viewsCountByStory.getOrDefault(s1, 0L);
                                    long s2Views = viewsCountByStory.getOrDefault(s2, 0L);
                                    return Long.compare(s2Views, s1Views); // Descending order
                                  })
                              .limit(2)
                              .collect(Collectors.toList())));

      // Flatten and return the result
      return topTwoStoriesByGenre.values().stream()
          .flatMap(List::stream)
          .sorted(Comparator.comparing(StoryEntity::getGender))
          .toArray(StoryEntity[]::new);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static Date parseDate(String dateStr) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      return dateFormat.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }
}
