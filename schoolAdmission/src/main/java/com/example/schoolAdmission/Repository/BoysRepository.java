package com.example.schoolAdmission.Repository;

import com.example.schoolAdmission.Model.Boys;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoysRepository extends MongoRepository<Boys,String> {
}
