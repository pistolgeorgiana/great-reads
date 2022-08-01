package com.devmind.greatreads.model.mappers;

import com.devmind.greatreads.dto.ReviewDTO;
import com.devmind.greatreads.model.Review;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReviewFromDto(ReviewDTO dto, @MappingTarget Review entity);
}
