package org.example.in.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

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
