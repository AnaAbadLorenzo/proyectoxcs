package es.uvigo.esei.dgss.teama.microstories.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import es.uvigo.esei.dgss.teama.microstories.entities.StoryEntity;
import es.uvigo.esei.dgss.teama.microstories.enums.Gender;
import es.uvigo.esei.dgss.teama.microstories.enums.Topic;
import es.uvigo.esei.dgss.teama.microstories.model.Pagination;
import es.uvigo.esei.dgss.teama.microstories.model.StoryDto;
import es.uvigo.esei.dgss.teama.microstories.service.StoryEJB;

/** This class define all the endpoints to add, get, modify and delete entity Story */
@Stateless
@Path("/microstory")
public class StoryResource {

  @Inject private StoryEJB storyEJB;

  @GET
  @Path(value = "/recent")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getRecentStories() {
    List<StoryEntity> entityList = storyEJB.findRecentStories();
    return Response.status(Response.Status.OK).entity(mapStoryList(entityList)).build();
  }

  @GET
  @Path(value = "/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStoryById(@PathParam("id") int id) throws NotFoundException {
    final StoryEntity story = this.storyEJB.findStoryById(id);

    if (story == null) {
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }

    return Response.ok(mapToDto(story)).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response exploreStories(
      @QueryParam("contains") String text,
      @QueryParam("gender") String gender,
      @QueryParam("topic") String topic,
      @QueryParam("startDate") String startDate,
      @QueryParam("endDate") String endDate,
      @QueryParam("page") Integer page,
      @QueryParam("maxResults") Integer maxResults) {

      if (page == null) page = 0;
      if (maxResults == null) maxResults = 10;
      if (maxResults > 100 || page < 0) {
        return Response.status(Response.Status.BAD_REQUEST).build();
      }

      Date startDateParsed = (startDate != null) ? parseDate(startDate) : null;
      Date endDateParsed = (endDate != null) ? parseDate(endDate) : null;

      Gender genderEnum = (gender == null) || (gender.isEmpty())? null: Gender.formatToGender(gender);
      Topic topicEnum = (topic == null) || (topic.isEmpty())? null: Topic.formatToTopic(topic);

      if (text != null) {
        List<StoryEntity> entityList = storyEJB.findByText(text, page, maxResults);
        Integer totalElements = storyEJB.countFindByText(text);
        return Response.status(Response.Status.OK)
            .entity(mapToPagination(entityList, page, maxResults, totalElements))
            .build();
      } else {
        List<StoryEntity> entityList =
            storyEJB.explore(
                genderEnum, topicEnum, startDateParsed, endDateParsed, page, maxResults);
        Integer totalElements =
            storyEJB.exploreCount(genderEnum, topicEnum, startDateParsed, endDateParsed);
        return Response.status(Response.Status.OK)
            .entity(mapToPagination(entityList, page, maxResults, totalElements))
            .build();
      }
  }

  @GET
  @Path(value = "/hottest")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getMostViewedStories() {
      List<StoryEntity> entityList = storyEJB.findMostViewedStories();
      return Response.status(Response.Status.OK).entity(mapStoryList(entityList)).build();
  }

  private Date parseDate(String dateStr) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      return dateFormat.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  private Pagination<StoryDto> mapToPagination(
      List<StoryEntity> entityList, int page, int maxResults, int totalElements) {
    List<StoryDto> listDto = this.mapStoryList(entityList);
    return new Pagination<>(listDto, totalElements, maxResults, page * maxResults);
  }

  private List<StoryDto> mapStoryList(List<StoryEntity> entityList) {
    return entityList.stream().map(this::mapToDto).collect(Collectors.toList());
  }

  private StoryDto mapToDto(StoryEntity story) {
    StoryDto dto = new StoryDto();
    dto.setAuthor(story.getAuthor());
    dto.setGender(story.getGender().name());
    dto.setId(story.getId());
    dto.setPublicationDate(
        story.getPublicationDate() != null ? story.getPublicationDate().toString() : null);
    dto.setText(story.getText());
    dto.setTitle(story.getTitle());
    dto.setTopic1(story.getTopic1().name());
    dto.setTopic2(story.getTopic2() != null ? story.getTopic2().name() : null);
    return dto;
  }
}
