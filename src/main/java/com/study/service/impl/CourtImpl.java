package com.study.service.impl;

import com.study.domain.Court;
import com.study.mapper.CourtMapper;
import com.study.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourtImpl implements CourtService {
    @Autowired
    CourtMapper courtMapper;
    @Override
    public Court selectByName(String name) {
        Court court = courtMapper.selectByName(name);
        return court;
    }
}
