package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for keeping the track of id, type, time, calorie, date, extra
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Training {
    public int id;
    public String type;
    public int time;
    public int calorie;
    public LocalDate date;
    public Extra extra;
}
