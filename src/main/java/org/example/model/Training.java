package org.example.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for keeping the track of id, type, time, calorie, date, extra
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Training implements Comparator<Training> {
    public int id;
    public int time;
    public int calorie;
    public LocalDate date;
    public int userId;
    public int typeId;
    public int extraId;



    @Override
    public int compare(Training o1, Training o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
