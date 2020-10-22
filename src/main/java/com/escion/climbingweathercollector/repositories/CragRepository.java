package com.escion.climbingweathercollector.repositories;

import com.escion.climbingweathercollector.model.db.Crag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CragRepository extends CrudRepository<Crag, String> {
}
