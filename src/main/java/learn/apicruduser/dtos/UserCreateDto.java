package learn.apicruduser.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(value = {"passWord"}, allowSetters = true)
public class UserCreateDto {

    @NotEmpty
    @Size(min = 4,max = 16, message = "user name must have from 4 to 16 character")
    private String name;

    @NotEmpty
    @Email(message = "email must is valid email")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "password must have at least 8 character")
    private String passWord;

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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserCreateDto [name=" + name + ", email=" + email + ", passWord=" + passWord + "]";
    }

    
}
