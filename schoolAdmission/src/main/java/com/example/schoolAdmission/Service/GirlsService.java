package com.example.schoolAdmission.Service;

import com.example.schoolAdmission.Model.Girls;
import com.example.schoolAdmission.Repository.GirlsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GirlsService {
    @Autowired
    private GirlsRepository girlsRepository;

    public Girls signup(Girls girls) {
        return girlsRepository.save(girls);
    }
    public Optional<Girls> getGirls(String id) {
        return girlsRepository.findById(id);
    }
    public List<Girls> getAllGirls(){
        return girlsRepository.findAll();
    }
    public Girls updateGirls(String id,Girls girls){
        return girlsRepository.save(girls);
    }
    public boolean deleteGirls(String id) {
        if (girlsRepository.existsById(id)) {
            girlsRepository.deleteById(id);
            return true;
        }
        return false;
    }
/*

    public Girls updateGirls(String id, Map<String, Object> updates) {
        Girls girls = girlsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    girls.setName((String) value);
                    break;
                case "age":
                    girls.setAge((int) value);
                    break;
                case "gender":
                    girls.setGender((String) value);
                    break;

            }
        });

        return girlsRepository.save(girls);
    }*/

    public Optional<Girls> patchGirls(String id, Girls girls) {
        Optional<Girls> existingGirlsOpt = girlsRepository.findById(id);
        if (existingGirlsOpt.isPresent()) {
            Girls existingGirls=existingGirlsOpt.get();
            if (girls.getName() != null) {
                existingGirls.setName(girls.getName());
            }
            if (girls.getAge() != 0) {
                existingGirls.setAge(girls.getAge());
            }
            if (girls.getGender() != null) {
                existingGirls.setGender(girls.getGender());
            }
            return Optional.of(girlsRepository.save(existingGirls));
        }
        return Optional.empty();
    }
}
