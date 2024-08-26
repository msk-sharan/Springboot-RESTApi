package com.example.schoolAdmission.Repository;

import com.example.schoolAdmission.Model.Girls;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GirlsRepository extends MongoRepository<Girls,String> {
}
