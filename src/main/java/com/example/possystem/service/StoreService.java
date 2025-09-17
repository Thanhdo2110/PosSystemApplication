package com.example.possystem.service;

import com.example.possystem.domain.StoreStatus;
import com.example.possystem.exceptions.UserException;
import com.example.possystem.modal.Store;
import com.example.possystem.modal.User;
import com.example.possystem.payload.dto.StoreDTO;

import java.util.List;

public interface StoreService {

    StoreDTO createStore(StoreDTO storeDTO , User user);
    StoreDTO getStoreById(Long id) throws Exception;
    List<StoreDTO> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDTO updateStore(Long id , StoreDTO storeDTO) throws UserException, Exception;
    void deleteStore (Long id) throws UserException;
    StoreDTO getStoreByEmployee() throws UserException;
    StoreDTO moderateStore(Long id, StoreStatus status) throws Exception;
}
