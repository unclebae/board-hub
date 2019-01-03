package com.sk.coe.swlab.techhub.domain.board;

import com.sk.coe.swlab.techhub.controller.board.dto.BoardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    @Mappings({
            @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "modifiedAt", source = "modifiedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    BoardDTO toDTO(Board board);

    List<BoardDTO> toDTO(List<Board> board);


}
