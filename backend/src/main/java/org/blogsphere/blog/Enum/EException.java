package org.blogsphere.blog.Enum;

public enum EException {
    USER_NOT_FOUND_EXCEPTION("User DO NOT EXIST"),
    USERNAME_ALREADY_EXIST("Usernam Already in use"),
    EMAIL_ALREADY_EXIST("Email Already in use"),
    PASSWORD_DOES_NOT_MATCH("Password Does not match for changing it"),
    POST_NOT_FOUND_EXCEPTION("POST DO NOT EXIST"),
    TAG_NOT_FOUND_EXCEPTION("TAG DO NOT EXIST"),
    BAD_CREDENTIALS_FOR_AUTHENTICATION("Incorrect Email or password");

    String message;
    EException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    
}
    
