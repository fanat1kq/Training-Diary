package org.example.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Type;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddTypeRequest {
    public String type;
}