package models;

public class User {

    String name;
    long twitterHandle;
    String profileImageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(long twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
