package es.uvigo.esei.dgss.teama.microstories.service;

import es.uvigo.esei.dgss.teama.microstories.entities.StoryEntity;
import es.uvigo.esei.dgss.teama.microstories.enums.Gender;
import es.uvigo.esei.dgss.teama.microstories.enums.Topic;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/** This class contains the logic of Stories */
@Stateful(name = "StoryEJB", mappedName = "ejb/StoryEJB")
public class StoryEJB {

  @PersistenceContext private EntityManager em;

  public StoryEJB() {
  }

  public List<StoryEntity> findRecentStories() {
    return em.createQuery(
            "SELECT s FROM StoryEntity s WHERE s.publicationDate IS NOT NULL ORDER BY s.publicationDate DESC",
            StoryEntity.class)
        .setMaxResults(6)
        .getResultList();
  }

  public StoryEntity findStoryById(Integer id_story) {
    return em.find(StoryEntity.class, id_story);
  }


  public List<StoryEntity> findByText(String text, int page, int maxResults) {
    return em.createQuery(
            "SELECT s FROM StoryEntity s WHERE s.text LIKE :text "
                + "OR s.title LIKE :text AND s.publicationDate IS NOT NULL ORDER BY s.publicationDate DESC",
            StoryEntity.class)
        .setParameter("text", '%' + text + '%')
        .setFirstResult(page * maxResults)
        .setMaxResults(maxResults)
        .getResultList();
  }

  public Integer countFindByText(String text) {
    return em.createQuery(
            "SELECT COUNT(s) FROM StoryEntity s WHERE s.text LIKE :text "
                + "OR s.title LIKE :text "
                + "AND s.publicationDate IS NOT NULL",
            Long.class)
        .setParameter("text", '%' + text + '%')
        .getSingleResult()
        .intValue();
  }

  public List<StoryEntity> explore(
      Gender gender, Topic topic, Date startDate, Date endDate, int page, int maxResults) {
    String queryString = "SELECT s FROM StoryEntity s WHERE s.publicationDate IS NOT NULL";

    if (gender != null) {
      queryString += " AND s.gender = :gender";
    }
    if (topic != null) {
      queryString += " AND (s.topic1 = :topic OR s.topic2 = :topic)";
    }
    if (startDate != null && endDate != null) {
      queryString += " AND s.publicationDate BETWEEN :startDate AND :endDate";
    }

    queryString += " ORDER BY s.publicationDate DESC";
    TypedQuery<StoryEntity> query = em.createQuery(queryString, StoryEntity.class);

    if (gender != null) {
      query.setParameter("gender", gender);
    }
    if (topic != null) {
      query.setParameter("topic", topic);
    }
    if (startDate != null && endDate != null) {
      query.setParameter("startDate", startDate);
      query.setParameter("endDate", endDate);
    }

    query.setFirstResult(page * maxResults).setMaxResults(maxResults);

    return query.getResultList();
  }

  public Integer exploreCount(Gender gender, Topic topic, Date startDate, Date endDate) {
    String queryString = "SELECT COUNT(s) FROM StoryEntity s WHERE s.publicationDate IS NOT NULL";

    if (gender != null) {
      queryString += " AND s.gender = :gender";
    }
    if (topic != null) {
      queryString += " AND (s.topic1 = :topic OR s.topic2 = :topic)";
    }
    if (startDate != null && endDate != null) {
      queryString += " AND s.publicationDate BETWEEN :startDate AND :endDate";
    }

    TypedQuery<Long> query = em.createQuery(queryString, Long.class);

    if (gender != null) {
      query.setParameter("gender", gender);
    }
    if (topic != null) {
      query.setParameter("topic", topic);
    }
    if (startDate != null && endDate != null) {
      query.setParameter("startDate", startDate);
      query.setParameter("endDate", endDate);
    }

    return query.getSingleResult().intValue();
  }

  public List<StoryEntity> findMostViewedStories() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MONTH, -1);
    Date lastMonth = cal.getTime();

    List<Gender> genders = Arrays.asList(Gender.STORY, Gender.POETRY, Gender.NANO_STORY);

    List<StoryEntity> mostViewedStoriesByTopic = new ArrayList<>();

    for (Gender gender : genders) {
      List<StoryEntity> topTwoStoriesByTopic =
          em.createQuery(
                  "SELECT s "
                      + "FROM StoryEntity s "
                      + "JOIN s.views v "
                      + "WHERE s.gender = :gender "
                      + "AND v.viewDate >= :lastMonth "
                      + "GROUP BY s.id, s.title, s.text, s.gender "
                      + "ORDER BY COUNT(v.id) DESC, s.publicationDate DESC",
                  StoryEntity.class)
              .setParameter("gender", gender)
              .setParameter("lastMonth", lastMonth)
              .setMaxResults(2)
              .getResultList();

      mostViewedStoriesByTopic.addAll(topTwoStoriesByTopic);
    }

    return mostViewedStoriesByTopic;
  }

  public List<StoryEntity> findStoriesByAuthor(String authorLogin, int page, int maxResults) {
      return em.createQuery(
              "SELECT s FROM StoryEntity s WHERE s.author.login = :authorLogin", StoryEntity.class)
          .setParameter("authorLogin", authorLogin)
          .setFirstResult(page * maxResults)
          .setMaxResults(maxResults)
          .getResultList();
    }
}
