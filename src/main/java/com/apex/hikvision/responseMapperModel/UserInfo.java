package com.apex.hikvision.responseMapperModel;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UserInfo {
    private String employeeNo;
    private String name;
    private String userType;
    private boolean closeDelayEnabled;
    @JsonProperty("Valid")
    private Valid valid;
    private String belongGroup;
    private String password;
    private String doorRight;
    @JsonProperty("RightPlan")
    public ArrayList<RightPlan> rightPlan;
    private int maxOpenDoorTime;
    private int openDoorTime;
    private int roomNumber;
    private int floorNumber;
    private boolean localUIRight;
    private String gender;
    private int numOfCard;
    private int numOfFace;
    @JsonProperty("PersonInfoExtends")
    private ArrayList<PersonInfoExtend> personInfoExtends;
}
