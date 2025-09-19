package com.example.possystem.controller;

import com.example.possystem.payload.dto.CategoryDTO;
import com.example.possystem.payload.response.ApiResponse;
import com.example.possystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(
            @RequestBody CategoryDTO categoryDTO )throws Exception {
                return ResponseEntity.ok(
                        categoryService.createCategory(categoryDTO));
    }


    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByStoreId(
            @PathVariable Long storeId)throws Exception {
        return ResponseEntity.ok(
                categoryService.getCategoriesByStore(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @RequestBody CategoryDTO categoryDTO ,
            @PathVariable Long id)throws Exception {
        return ResponseEntity.ok(
                categoryService.updateCategory(id,categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(
            @RequestBody CategoryDTO categoryDTO ,
            @PathVariable Long id)throws Exception {
        categoryService.updateCategory(id,categoryDTO);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("category geleted successfully");
        return ResponseEntity.ok(
                apiResponse);
    }

}
