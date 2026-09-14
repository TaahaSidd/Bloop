package com.spicalabs.bloop.mapper;

import com.spicalabs.bloop.dto.response.CategoryResponse;
import com.spicalabs.bloop.dto.response.MemeResponse;
import com.spicalabs.bloop.entity.Category;
import com.spicalabs.bloop.entity.Meme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoMapper {

    //Dto for MemeResponse
    public MemeResponse toMemeResponse(Meme meme) {

        if (meme == null) return null;

        return MemeResponse.builder()
                .id(meme.getId())
                .title(meme.getTitle())
                .imageUrl(meme.getImageUrl())
                .soundUrl(meme.getSoundUrl())
                .category(toCategoryResponse(meme.getCategory()))
                .playCount(meme.getPlayCount())
                .shareCount(meme.getShareCount())
                .build();
    }

    //Dto for CategoryResponse
    public CategoryResponse toCategoryResponse(Category category) {

        if (category == null) return null;

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .build();
    }
}
