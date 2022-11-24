package com.apex.hikvision.responseMapperModel;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Root {
    @JsonProperty("UserInfoSearch")
    private UserInfoSearch userInfoSearch;
}
