package com.example.multidb.mapper.fenote;

import com.example.multidb.database.FeNoteMapper;
import com.example.multidb.domain.fenote.FePoint;

import java.util.List;

@FeNoteMapper
public interface FePointMapper {
    List<FePoint> findAll();
}
