package by.itacademy.tatjana.balashevich;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayList {
    public String description;
    @JsonProperty("isPublic")
    public boolean isPublic;
    public String name;
    public int userId;

    public PlayList(String description, boolean isPublic, String name, int userId) {
        this.description = description;
        this.isPublic = isPublic;
        this.name = name;
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
}
