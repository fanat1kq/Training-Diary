package org.example.in.dto;

//import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
//для сохранения данных
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PuttingRequest {
    private String userName;
    private String name;
    private int value;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;




}