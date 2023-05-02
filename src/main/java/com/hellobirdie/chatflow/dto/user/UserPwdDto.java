package com.hellobirdie.chatflow.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
// compare the old password and new password to determine if the new password is valid
public class UserPwdDto {
        @NotEmpty(message = "Old password must not be empty")
        @Pattern(regexp = "^(?=\\S*[a-zA-Z])(?=\\S*[0-9#!\"$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,}$",
                message = "Your password must be at least 8 character long and contains at least one non-letter character.")
        private String oldPassword;
    
        @NotEmpty(message = "New password must not be empty")
        @Pattern(regexp = "^(?=\\S*[a-zA-Z])(?=\\S*[0-9#!\"$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,}$",
                message = "Your password must be at least 8 character long and contains at least one non-letter character.")
        private String newPassword;
    
        @NotEmpty(message = "Confirm password must not be empty")
        @Pattern(regexp = "^(?=\\S*[a-zA-Z])(?=\\S*[0-9#!\"$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,}$",
                message = "Your password must be at least 8 character long and contains at least one non-letter character.")
        private String confirmPassword;
}
