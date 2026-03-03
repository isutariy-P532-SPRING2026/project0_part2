package edu.iu.habahram.ducksservice.repository;

import edu.iu.habahram.ducksservice.model.DuckData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DucksJpaRepository extends CrudRepository<DuckData, Integer> {
    List<DuckData> findByType(String type);
}