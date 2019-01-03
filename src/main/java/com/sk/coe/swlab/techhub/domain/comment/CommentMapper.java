package com.sk.coe.swlab.techhub.domain.comment;

import com.sk.coe.swlab.techhub.controller.comment.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mappings({
            @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "modifiedAt", source = "modifiedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    CommentDTO toDTO(Comment sound);
}
