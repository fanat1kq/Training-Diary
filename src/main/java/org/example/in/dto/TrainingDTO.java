package org.example.in.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainingDTO {
    private int id;
    private int time;
    private int calorie;
    private LocalDate date;
    private int userId;
    private int typeId;
    private int extraId;
}
