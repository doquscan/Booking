package com.adp.demo.dto;

import java.time.LocalDateTime;

public class Booking {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Booking(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean overlaps(Booking other) {
        return (startTime.isBefore(other.getEndTime()) && endTime.isAfter(other.getStartTime()));
    }
}