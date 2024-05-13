package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "training_type", schema = "app")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {
    @Id
    @SequenceGenerator(name = "type_generator", sequenceName = "type_id_seq", allocationSize = 1, schema = "app")
    public int id;
    public String typeName;
}