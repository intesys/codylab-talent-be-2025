package it.intesys.codylab.dto;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserProfile profile;
    private WorkingHours workingHours;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public User setProfile(UserProfile profile) {
        this.profile = profile;
        return this;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public User setWorkingHours(WorkingHours workingHours) {
        this.workingHours = workingHours;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", profile=" + profile +
                ", workingHours=" + workingHours +
                '}';
    }
}