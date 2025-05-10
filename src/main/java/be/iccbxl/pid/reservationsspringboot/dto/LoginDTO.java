package be.iccbxl.pid.reservationsspringboot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class LoginDTO {


    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
