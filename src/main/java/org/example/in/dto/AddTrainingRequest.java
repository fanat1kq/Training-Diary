package org.example.in.dto;

//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class AddTrainingRequest {
    public int time;
    public int calorie;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate date;
    public int typeId;
    public int extraId;
}