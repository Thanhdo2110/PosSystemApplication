package com.example.possystem.service;

import com.example.possystem.exceptions.UserException;
import com.example.possystem.payload.dto.CategoryDTO;
import com.stripe.model.tax.Registration;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO dto) throws UserException, Exception;
    List<CategoryDTO> getCategoriesByStore(Long storeId);
    CategoryDTO updateCategory (Long id, CategoryDTO dto) throws Exception;
    void deleteCategory(Long id) throws Exception;

}
