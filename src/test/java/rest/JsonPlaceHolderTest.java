package rest;


import dtos.CommentDto;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import rest.base.TestBase;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static properties.AppProperties.FIRST_TASK_BASE_URL;
import static rest.ApplicationPath.COMMENTS;

@Slf4j
public class JsonPlaceHolderTest extends TestBase {

    @Test
    public void first() {
        log.info("First Rest task with CommentDto");
        String emailToCheck = "Jayne_Kuhic@sydney.com";
        List<CommentDto> comments = getComments();
        assertThat(comments.isEmpty(), is(false));
        boolean isEmailPresent = comments.stream()
                .anyMatch(commentDto -> commentDto.getEmail().equals(emailToCheck));
        assertTrue(isEmailPresent);
        log.info("Email {} founded", emailToCheck);
    }

    @Test
    public void firstWithoutDto() {
        log.info("First Rest task without dto");
        given().log().all()
                .baseUri(FIRST_TASK_BASE_URL.getValue())
                .get(COMMENTS)
                .then()
                .statusCode(SC_OK)
                .body("size", not(0))
                .body("email", hasItem("Jayne_Kuhic@sydney.com"));
    }

    @Test
    public void second() {
        log.info("Second Rest task");
        List<CommentDto> comments = getComments();
        assertThat(comments.isEmpty(), is(false));
    }

    @Test
    public void third() {
        log.info("Third Rest task");
        Predicate<CommentDto> filterCondition = commentDto -> commentDto.getPostId().equals(1) &&
                commentDto.getBody().contains("non");

        List<CommentDto> filteredComments = getComments().stream()
                .filter(filterCondition)
                .collect(Collectors.toList());
        assertThat(filteredComments.isEmpty(), is(false));

        boolean isConditionFulfilled = filteredComments.stream()
                .allMatch(filterCondition);
        assertTrue(isConditionFulfilled);
    }

    private List<CommentDto> getComments() {
        Response commentsResponse = given().log().all()
                .baseUri(FIRST_TASK_BASE_URL.getValue())
                .get(COMMENTS);
        assertThat(commentsResponse.statusCode(), is(SC_OK));
        return Arrays.stream(commentsResponse.as(CommentDto[].class))
                .collect(Collectors.toList());
    }

}
