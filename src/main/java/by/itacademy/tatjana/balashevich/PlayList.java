package by.itacademy.tatjana.balashevich;

public class PlayList {
    String description;
    boolean isPublic;
    String name;
    int userId;

    public PlayList(String description, boolean isPublic, String name, int userId) {
        this.description = description;
        this.isPublic = isPublic;
        this.name = name;
        this.userId = userId;
    }
}
