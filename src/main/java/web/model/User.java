package web.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be between 2 and 30 characters")
    private String name;

    @Column
    @NotNull(message = "Age should not be empty")
    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 127, message = "Age should be less than 127")
    private byte age;

    @Column
    @NotEmpty(message = "email name should not be empty")
    @Email(message = "Invalid email")
    private String email;

    public User() {

    }

    public User(String name, byte age, String email) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
