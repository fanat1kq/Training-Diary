package org.example.in.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class TrainingDTO {

    private String name;
    private Integer value;
    private LocalDate date;

}
