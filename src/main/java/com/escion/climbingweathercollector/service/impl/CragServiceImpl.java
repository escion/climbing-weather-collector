package com.escion.climbingweathercollector.service.impl;

import com.escion.climbingweathercollector.model.db.Crag;
import com.escion.climbingweathercollector.repositories.CragRepository;
import com.escion.climbingweathercollector.service.CragService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CragServiceImpl implements CragService {

    @Autowired
    private CragRepository repository;

    @Override
    public Iterable<Crag> findAllCrags() {
        return repository.findAll();
    }
}
