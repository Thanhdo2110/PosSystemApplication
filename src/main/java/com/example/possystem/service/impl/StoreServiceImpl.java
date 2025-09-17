package com.example.possystem.service.impl;

import com.example.possystem.domain.StoreStatus;
import com.example.possystem.exceptions.UserException;
import com.example.possystem.mapper.StoreMapper;
import com.example.possystem.modal.Store;
import com.example.possystem.modal.StoreContact;
import com.example.possystem.modal.User;
import com.example.possystem.payload.dto.StoreDTO;
import com.example.possystem.repository.StoreRepository;
import com.example.possystem.service.StoreService;
import com.example.possystem.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService  {

    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public StoreDTO createStore(StoreDTO storeDTO, User user) {
        Store store = StoreMapper.toEntity(storeDTO,user);

        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    public StoreDTO getStoreById(Long id) throws Exception {

        Store store = storeRepository.findById(id).orElseThrow(
                ()-> new Exception("store not found...")
        );
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDTO> getAllStores() {
        List<Store> dtos= storeRepository.findAll();
         return dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDTO)throws Exception {
        User currentUser =userService.getCurrentUser();

        Store existing = storeRepository.findByStoreAdminId(currentUser.getId());

        if (existing==null){
            throw new Exception("store not found");
        }

        existing.setBrand(storeDTO.getBrand());
        existing.setDescription(storeDTO.getDescription());

        if (storeDTO.getStoreType()!=null){
            existing.setStoreType(storeDTO.getStoreType());
        }

        if (storeDTO.getContact()!=null){
            StoreContact contact = StoreContact.builder()
                    .address(storeDTO.getContact().getAddress())
                    .phone(storeDTO.getContact().getPhone())
                    .email(storeDTO.getContact().getEmail())
                    .build();
            existing.setContact(contact);
        }
        Store updatedStore = storeRepository.save(existing);
        return StoreMapper.toDTO(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws UserException {
        Store store = getStoreByAdmin();

        storeRepository.delete(store);
    }

    @Override
    public StoreDTO getStoreByEmployee() throws UserException {
        User crrentUser = userService.getCurrentUser();
        if (crrentUser==null){
            throw new UserException("you don't have permission to access this store");
        }
        return StoreMapper.toDTO(crrentUser.getStore());
    }

    @Override
    public StoreDTO moderateStore(Long id, StoreStatus status) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(
                ()-> new Exception("store not found...")
        );

        store.setStatus(status);
        Store  updatedStore =storeRepository.save(store);
        return StoreMapper.toDTO(updatedStore);
    }
}
