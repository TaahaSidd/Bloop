package com.spicalabs.bloop.service;

import com.spicalabs.bloop.dto.request.CategoryRequest;
import com.spicalabs.bloop.dto.response.CategoryResponse;
import com.spicalabs.bloop.entity.Category;
import com.spicalabs.bloop.mapper.DtoMapper;
import com.spicalabs.bloop.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final DtoMapper dtoMapper;

    //Add category
    public CategoryResponse createCategory(CategoryRequest req) {
        Category category = Category.builder()
                .name(req.getName())
                .slug(req.getSlug())
                .build();

        Category savedCategory = categoryRepo.save(category);
        return dtoMapper.toCategoryResponse(savedCategory);
    }

    //Get All Categories
    public List<CategoryResponse> getAllCategories() {
        return categoryRepo.findAll()
                .stream()
                .map(dtoMapper::toCategoryResponse)
                .toList();
    }

    //Get Category by ID
    public CategoryResponse getCategoryById(UUID id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with ID " + id + "not found"));

        return dtoMapper.toCategoryResponse(category);
    }

}

