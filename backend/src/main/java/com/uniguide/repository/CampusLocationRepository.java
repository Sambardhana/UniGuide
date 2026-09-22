package com.uniguide.repository;

import com.uniguide.entity.CampusLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link CampusLocation} entity persistence operations.
 */
@Repository
public interface CampusLocationRepository extends JpaRepository<CampusLocation, Long> {

    Optional<CampusLocation> findByCode(String code);

    Optional<CampusLocation> findByQrCodeKey(String qrCodeKey);

    boolean existsByCode(String code);

    boolean existsByQrCodeKey(String qrCodeKey);

    List<CampusLocation> findByCategoryIgnoreCase(String category);

    List<CampusLocation> findByNameContainingIgnoreCase(String keyword);
}
