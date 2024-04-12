package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for extra information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Extra {
    public String name;
    public int value;
}
