package org.example.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//для отображения всех записей(на сервлет с json)
public class CaloriesStaticResponse {

    private String userLogin;
    private int calories;
}