package org.example.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//для отображения всех записей(на сервлет с json)
public class TrainingHistoryResponse {

    private String userLogin;
    private List<TrainingDTO> reading;
}
