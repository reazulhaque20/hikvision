package com.apex.hikvision.responseMapperModel;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
@Data
public class UserInfoSearch {
    private String searchID;
    private String responseStatusStrg;
    private int numOfMatches;
    private int totalMatches;
    @JsonProperty("UserInfo")
    private ArrayList<UserInfo> userInfo;
}
