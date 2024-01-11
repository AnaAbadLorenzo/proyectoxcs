package es.uvigo.esei.dgss.teama.microstories.enums;

public enum Gender {
  STORY,
  POETRY,
  NANO_STORY;

  public static Gender formatToGender(String value) {
    switch (value.toUpperCase()) {
      case "STORY":
        return STORY;
      case "POETRY":
        return POETRY;
      case "NANO_STORY":
        return NANO_STORY;
      default:
        throw new IllegalArgumentException("Unknown gender: " + value);
    }
  }
}
