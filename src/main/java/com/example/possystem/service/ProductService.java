package com.example.possystem.service;

import com.example.possystem.modal.User;
import com.example.possystem.payload.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception;
    ProductDTO updateProduct(Long id , ProductDTO productDTO,User user) throws Exception;
    void deleteProduct(Long id , User user) throws Exception;
    List<ProductDTO> getProductByStoreId(Long storeId);
    List<ProductDTO> searchByKeyword(Long storeId , String keyword);
}
