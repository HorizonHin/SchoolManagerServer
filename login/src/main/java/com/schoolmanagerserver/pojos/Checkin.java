package com.schoolmanagerserver.pojos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Checkin {
    private String checkinDetailId;
    private LocalDateTime checkinTime;
    private String courseId;
    private String courseName;
    private Long studentId;

    // 无参构造函数
    public Checkin() {
    }

    // 全参构造函数
    public Checkin(String checkinDetailId, LocalDateTime checkinTime, String courseId, String courseName) {
        this.checkinDetailId = checkinDetailId;
        this.checkinTime = checkinTime;
        this.courseId = courseId;
        this.courseName = courseName;
    }
}
