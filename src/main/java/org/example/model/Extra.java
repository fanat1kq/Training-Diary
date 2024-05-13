package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for extra information
 */
@Entity
@Table(name = "training_extra", schema = "app")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Extra {
    @Id
    @SequenceGenerator(name = "extra_generator", sequenceName = "extra_id_seq", allocationSize = 1, schema = "app")
    public int id;
    @NotNull
    public String name;
    @NotNull
    public int value;
}