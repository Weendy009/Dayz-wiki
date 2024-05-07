package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.items.Illnesses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IllnessesRepository extends JpaRepository<Illnesses, Long>, ItemRepository {
    Illnesses getByIdAndType(Long id, String type);
}
