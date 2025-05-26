package com.example.storage.repository;

import com.example.storage.domain.UsersEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends AbsRepository<UsersEntity, String> {

}
