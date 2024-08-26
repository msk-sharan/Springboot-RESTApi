package com.example.schoolAdmission.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="Boys_Detials")
public class Boys {
@Id
    private String id;
    @NotBlank(message = "Name shouls not be blank, Enter your Name")
    private String name;
    @Min(3)
    @Max(5)
    /*@NotBlank(message = "Age is Compulsory,Enter your age")*/
    private int age;
    @NotBlank(message = "mention your Gender")
    private String gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
