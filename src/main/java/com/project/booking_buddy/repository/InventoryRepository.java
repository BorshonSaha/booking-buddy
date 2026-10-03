package com.project.booking_buddy.repository;

import com.project.booking_buddy.entity.Hotel;
import com.project.booking_buddy.entity.Inventory;
import com.project.booking_buddy.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByRoom(Room room);

    @Query("""
            SELECT h
             FROM Hotel h
             WHERE h.active = true
                 AND LOWER(h.city) = LOWER(:city)
                 AND EXISTS (
                     SELECT 1
                     FROM Inventory i
                     WHERE i.hotel = h
                         AND i.date BETWEEN :startDate AND :endDate
                         AND i.closed = false
                         AND (i.totalCount - i.bookedCount) >= :roomsCount
                     GROUP BY i.room
                     HAVING COUNT(i.date) = :dateCount
                 )
             ORDER BY h.name, h.id
            """)
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount") Long dateCount, // total number of dates between startDate and endDate
            Pageable pageable
    );
}
