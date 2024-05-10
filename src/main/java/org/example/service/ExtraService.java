package org.example.service;

import org.example.in.dto.AddExtraRequest;
import org.example.model.Extra;

import java.util.List;

public interface ExtraService {
    Extra addExtra(AddExtraRequest request);

    List<Extra> getExtra();
}
