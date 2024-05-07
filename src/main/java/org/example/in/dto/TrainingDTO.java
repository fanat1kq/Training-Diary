package org.example.in.dto;

import java.time.LocalDate;



public class TrainingDTO {

    private String name;
    private Integer value;
    private LocalDate date;



    public TrainingDTO() {
    }

    public TrainingDTO(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
