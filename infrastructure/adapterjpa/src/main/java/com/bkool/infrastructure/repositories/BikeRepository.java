package com.bkool.infrastructure.repositories;


import com.bkool.infrastructure.entities.BikeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<BikeEntity, Long> {

    @Query("SELECT DISTINCT b FROM Bike b JOIN b.items i WHERE " +
            "(:name IS NULL OR b.name = :name) AND " +
            "(:manufacturer IS NULL OR b.manufacturer = :manufacturer) AND " +
            "(:itemType IS NULL OR i.type = :itemType)")
    List<BikeEntity> findByItemsIn(@Param("name") String name,
                                   @Param("manufacturer") String manufacturer,
                                   @Param("itemType") String itemType,
                                   Sort sort);

}
