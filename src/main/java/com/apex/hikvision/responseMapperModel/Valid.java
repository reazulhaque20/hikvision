package com.apex.hikvision.responseMapperModel;

import lombok.Data;

import java.util.Date;
@Data
public class Valid {
    private boolean enable;
    private Date beginTime;
    private Date endTime;
    private String timeType;
}
