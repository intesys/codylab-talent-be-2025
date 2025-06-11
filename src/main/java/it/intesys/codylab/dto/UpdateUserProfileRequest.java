package it.intesys.codylab.dto;

public class UpdateUserProfileRequest {
    private UserProfile profile;

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}