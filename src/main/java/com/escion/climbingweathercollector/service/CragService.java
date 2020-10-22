package com.escion.climbingweathercollector.service;

import com.escion.climbingweathercollector.model.db.Crag;

public interface CragService {
    Iterable<Crag> findAllCrags();
}
