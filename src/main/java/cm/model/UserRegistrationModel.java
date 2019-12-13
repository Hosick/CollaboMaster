package cm.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationModel {

    @NotEmpty
    @Size(min=4, max=15)
    String id;

    @NotEmpty
    @Size(min=2, max=12)
    String name;

    @NotEmpty
    @Size(min=4, max=15)
    String password1;

    String password2;

    @Email
    String email;

    String userType;

}
