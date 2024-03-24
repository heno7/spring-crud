package learn.apicruduser.dtos;

import jakarta.validation.constraints.Size;

public class UserUpdateDto {

    @Size(min = 4,max = 16, message = "user name must have from 4 to 16 character")
    private String name;

    @Size(min = 8, message = "password must have at least 8 character")
    private String passWord;

    public UserUpdateDto(String name,String passWord) {
        this.name = name;
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
}
