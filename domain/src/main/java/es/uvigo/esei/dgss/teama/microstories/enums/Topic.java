package es.uvigo.esei.dgss.teama.microstories.enums;

public enum Topic {
  ADVENTURE,
  SCIENCE,
  FICTION,
  HISTORY,
  CHILDREN,
  ROMANCE,
  SUSPENSE,
  HORROR;

  public static Topic formatToTopic(String value) {
    switch (value.toUpperCase()) {
      case "ADVENTURE":
        return ADVENTURE;
      case "SCIENCE":
        return SCIENCE;
      case "FICTION":
        return FICTION;
      case "HISTORY":
        return HISTORY;
      case "CHILDREN":
        return CHILDREN;
      case "ROMANCE":
        return ROMANCE;
      case "SUSPENSE":
        return SUSPENSE;
      case "HORROR":
        return HORROR;
      default:
        throw new IllegalArgumentException("Unknown topic: " + value);
    }
  }
}
