package by.itacademy.tatjana.balashevich;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTest {
    @Test
    public void testCreatePlaylist() {
        String body = "{\n" +
                "  \"description\": \"Metall\",\n" +
                "  \"isPublic\": true,\n" +
                "  \"name\": \"One\",\n" +
                "  \"userId\": 1\n" +
                "}";
        //PlayList body= new PlayList ("Metall", true, "One", 1);
        String endpoint = "http://localhost:8080/api/playlists";
        given().header("Content-Type", "application/json").body(body).
                when().post(endpoint).
                then().log().body().
                assertThat().
                statusCode(201).
                body("userId", equalTo(1));
    }

    @Test
    public void testModifyPlaylistAttributes() {
    }

    @Test
    public void testAddTrackToPlayList() {
    }

    @Test
    public void testRemoveTracksFromPlaylist() {
    }

    @Test
    public void testDeletePlayList() {
    }
}
