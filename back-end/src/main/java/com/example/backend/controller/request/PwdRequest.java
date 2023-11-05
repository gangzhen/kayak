package com.example.backend.controller.request;

import lombok.Data;

@Data
public class PwdRequest {

    private String id;
    private String oldPwd;
    private String newPwd;
    private String confirmPwd;

}
