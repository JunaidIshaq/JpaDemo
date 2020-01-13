package org.learning.jpa.model;


import javax.persistence.*;

@Entity
@Table(name="user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer age;

    private String gender;

    private String favoriteColor;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", favoriteColor='" + favoriteColor + '\'' +
                ", user=" + user +
                '}';
    }
}
