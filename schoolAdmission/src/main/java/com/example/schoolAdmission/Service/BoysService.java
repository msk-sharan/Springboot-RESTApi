package com.example.schoolAdmission.Service;

import com.example.schoolAdmission.Model.Boys;
import com.example.schoolAdmission.Repository.BoysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoysService {
        @Autowired
        private BoysRepository boysRepository;

    public Boys signup(Boys boys) {
        return boysRepository.save(boys);
    }

    public Optional<Boys> getBoys(String id) {
        return boysRepository.findById(id);
    }

    public List<Boys> getAllBoys()
    {
        return boysRepository.findAll();
    }

    public Boys updateBoys(String id, Boys boys) {

        return boysRepository.save(boys);
    }

    public boolean deleteBoys(String id) {
        if (boysRepository.existsById(id)) {
            boysRepository.deleteById(id);
            return true;
        }
        return false;
    }

       /* public Boys updateBoys(String id, Map<String, Object> updates) {
            Boys boys = boysRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        boys.setName((String) value);
                        break;
                    case "age":
                        boys.setAge((String) value);
                        break;
                    case "gender":
                        boys.setGender((String) value);
                        break;

                }
            });

            return boysRepository.save(boys);
        }*/

    public Optional<Boys> patchBoys(String id, Boys boys) {
        Optional<Boys> existingBoysOpt = boysRepository.findById(id);
        if (existingBoysOpt.isPresent()) {
            Boys existingBoys=existingBoysOpt.get();
            if (boys.getName() != null) {
                existingBoys.setName(boys.getName());
            }
            if (boys.getAge() != 0) {
                existingBoys.setAge(boys.getAge());
            }
            if (boys.getGender() != null) {
                existingBoys.setGender(boys.getGender());
            }
            return Optional.of(boysRepository.save(existingBoys));
        }
        return Optional.empty();
    }
}
