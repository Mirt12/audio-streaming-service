package by.itacademy.tatjana.balashevich;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class PlayListTest {
    @Test
    public void testCreatePlaylist() throws IOException {
//        //1 variant:
//        String playList = "{\n" +
//                "  \"description\": \"Metall\",\n" +
//                "  \"isPublic\": true,\n" +
//                "  \"name\": \"One\",\n" +
//                "  \"userId\": 1\n" +
//                "}";
//        String endpoint = "http://localhost:8080/api/playlists";
//        given().header("Content-Type", "application/json").body(playList).
//                when().post(endpoint).
//                then().log().body().
//                assertThat().
//                statusCode(201).
//                body("userId", equalTo(1));


//        //2 variant:
//        String endpoint = "http://localhost:8080/api/playlists";
//        ObjectMapper objectMapper = new ObjectMapper();
//        PlayList playList = new PlayList("Metall", true, "One", 1);
//        objectMapper.writeValue(new File("target/playlist.json"), playList);
//        given().header("Content-Type", "application/json").body(new File("target/playlist.json")).
//                when().post(endpoint).
//                then().
//                assertThat().
//                statusCode(201);

        //3 variant:
        String endpoint = "http://localhost:8080/api/playlists";
        ObjectMapper objectMapper = new ObjectMapper();
        PlayList playList = new PlayList("Metall", true, "One", 1);
        String listAsString = objectMapper.writeValueAsString(playList);
        given().header("Content-Type", "application/json").body(listAsString).
                when().post(endpoint).
                then().
                assertThat().
                statusCode(201);
    }

    @Test
    public void testModifyPlaylistAttributes() {
        //Create a new playlist for existing users
        String body1 = "{\n" +
                "  \"description\": \"Metall\",\n" +
                "  \"isPublic\": true,\n" +
                "  \"name\": \"One\",\n" +
                "  \"userId\": 2\n" +
                "}";
        String endpoint = "http://localhost:8080/api/playlists";
        given().header("Content-Type", "application/json").body(body1).
                when().post(endpoint).
                then().log().body().
                assertThat().
                statusCode(201).
                body("userId", equalTo(2));
        //Update details for just created playlist (name, description, public attribute)
        int id = 8;
        String endpoint2 = "http://localhost:8080/api/playlists/" + id;
        String body2 = "{\n" +
                "  \"description\": \"Metall3\",\n" +
                "  \"isPublic\": false,\n" +
                "  \"name\": \"Two\",\n" +
                "  \"userId\": 2\n" +
                "}";
        given().header("Content-Type", "application/json").body(body2).
                when().put(endpoint2).
                then().assertThat().statusCode(200).log().body();

        //Verify your playlist is available in the application with existing information.
        given().when().get(endpoint2).
                then().log().body().
                assertThat().
                statusCode(200).
                body("description", equalTo("Metall3"));
    }

    @Test
    public void testAddTrackToPlayList() {
        //Create a new playlist for existing users
        String body1 = "{\n" +
                "  \"description\": \"MANOWAR\",\n" +
                "  \"isPublic\": true,\n" +
                "  \"name\": \"Die for Metal\",\n" +
                "  \"userId\": 3\n" +
                "}";
        String endpoint = "http://localhost:8080/api/playlists";
        given().header("Content-Type", "application/json").body(body1).
                when().post(endpoint).
                then().assertThat().statusCode(201).
                body("userId", equalTo(3)).log().body();

        //Add existing 1 track to the playlist
        String endpoint2 = "http://localhost:8080/api/playlists/4/tracks/add";
        String body2 = "{\n" +
                "\t\"trackId\": 1\n" +
                "}";
        given().header("Content-Type", "application/json").body(body2).
                when().post(endpoint2).
                then().assertThat().statusCode(200).log().body();

        //Add existing 2 track to the playlist
        String body3 = "{\n" +
                "\t\"trackId\": 2\n" +
                "}";
        given().header("Content-Type", "application/json").body(body3).
                when().post(endpoint2).
                then().assertThat().statusCode(200).log().body();
        //Make sure tracks are added:
        String endpoint3 = "http://localhost:8080/api/playlists/4/tracks";
        given().when().get(endpoint3).then().log().body();
    }

    @Test
    public void testRemoveTracksFromPlaylist() {
        String endpoint = "http://localhost:8080/api/tracks/2";
        given().when().delete(endpoint).then().assertThat().statusCode(200).log().body();
        given().when().get(endpoint).then().log().body().assertThat().statusCode(404);
    }

    @Test
    public void testDeletePlayList() {
        String endpoint = "http://localhost:8080/api/playlists/4";
        given().when().delete(endpoint).
                then().assertThat().statusCode(200).body("id", equalTo(4)).log().body();
        given().when().get(endpoint).then().log().body().assertThat().statusCode(404);
    }
}
