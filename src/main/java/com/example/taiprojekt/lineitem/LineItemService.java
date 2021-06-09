package com.example.taiprojekt.lineitem;

import com.example.taiprojekt.entities.LineItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineItemService {

    private final LineItemRepository lineItemRepository;

    public void deleteLineItems(List<LineItemEntity> lineItemEntities) {
        lineItemRepository.deleteAll(lineItemEntities);
    }
}
