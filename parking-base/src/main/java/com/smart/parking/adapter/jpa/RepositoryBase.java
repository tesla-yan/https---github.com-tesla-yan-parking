package com.smart.parking.adapter.jpa;

import com.smart.parking.domain.EntityBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBase<T extends EntityBase, Integer> extends JpaRepository<T, Integer> {

}
