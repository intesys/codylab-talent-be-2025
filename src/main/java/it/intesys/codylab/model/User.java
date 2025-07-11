package it.intesys.codylab.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String profile;

    private Double dailyHours;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<Project> projects;

    @ManyToMany
    @JoinTable(
            name = "users_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> tasks;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<Project> managedProjects;

    public User() {
        // Default constructor
    }

    public void setManagedProjects(List<Project> managedProjects) {
        this.managedProjects = managedProjects;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Double getDailyHours() {
        return dailyHours;
    }

    public void setDailyHours(Double dailyHours) {
        this.dailyHours = dailyHours;
    }

    public List<Project> getManagedProjects() {
        return managedProjects;
    }

    public void setManagedProjects(Object o) {
        if (o instanceof List) {
            this.managedProjects = (List<Project>) o;
        } else {
            this.managedProjects = null;
        }
    }

    public Long getTaskId() {
        if (this.tasks != null && !this.tasks.isEmpty()) {
            return this.tasks.get(0).getId();
        } else {
            return null;
        }
    }

    public void setProjectManagers(Object o) {
        if (o instanceof List) {
            this.managedProjects = (List<Project>) o;
        } else {
            this.managedProjects = null;
        }
    }
}
