package com.example.taiprojekt.lineitem;

import com.example.taiprojekt.entities.LineItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository extends JpaRepository<LineItemEntity, Long> {
}
