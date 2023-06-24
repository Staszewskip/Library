package com.crud.library.mapper;

import com.crud.library.domain.BorrowRecord;
import com.crud.library.domain.dto.BorrowRecordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowMapper {


    public BorrowRecordDTO mapToBorrowRecordDTO(final BorrowRecord borrowRecord) {
        return new BorrowRecordDTO(
                borrowRecord.getBorrowId(),
                borrowRecord.getUser(),
                borrowRecord.getBookCopy()
        );
    }




    public List<BorrowRecordDTO> mapToBorrowRecordDTOList(List<BorrowRecord> BorrowRecordList) {
        return BorrowRecordList.stream()
                .map(this::mapToBorrowRecordDTO)
                .collect(Collectors.toList());
    }
}
