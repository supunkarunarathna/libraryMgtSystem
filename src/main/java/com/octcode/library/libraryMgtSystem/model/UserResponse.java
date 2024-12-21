package com.octcode.library.libraryMgtSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    private String responseMessage;
    private String responseCode;
}
