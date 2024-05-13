package org.example.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Comparator;

/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for keeping the track of id, type, time, calorie, date, extra
 */
@Entity
@Table(name = "training", schema = "app")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Training implements Comparator<Training> {
    @Id
    @SequenceGenerator(name = "training_generator", sequenceName = "Training_id_seq", allocationSize = 1, schema = "app")
    @GeneratedValue(generator = "training_generator", strategy = GenerationType.SEQUENCE)
    public int id;
    @NotNull
    public int time;
    @NotNull
    public int calorie;
    @NotNull
    public LocalDate date;
    public int userId;
    @NotNull
    public int typeId;
    @NotNull
    public int extraId;



    @Override
    public int compare(Training o1, Training o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}