package com.spicalabs.bloop.dto.response;

import com.spicalabs.bloop.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemeResponse {

    private UUID id;
    private String title;
    private String imageUrl;
    private String soundUrl;
    private CategoryResponse category;
    private long playCount;
    private long shareCount;
}
