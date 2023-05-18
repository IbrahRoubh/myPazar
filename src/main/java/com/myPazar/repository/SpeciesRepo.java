package com.myPazar.repository;

import com.myPazar.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepo extends JpaRepository<Species, Long> {
     Species findByName(String speciesName);
}
