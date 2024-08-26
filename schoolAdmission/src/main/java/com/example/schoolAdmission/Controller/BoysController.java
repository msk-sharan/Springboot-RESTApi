package com.example.schoolAdmission.Controller;

import com.example.schoolAdmission.Exception.BoyNotFoundException;
import com.example.schoolAdmission.Model.Boys;
import com.example.schoolAdmission.Repository.BoysRepository;
import com.example.schoolAdmission.Service.BoysService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/boys")
public class BoysController {
    @Autowired
    private BoysRepository boysRepository;
    @Autowired
    private BoysService boysService;

   /* @PostMapping("/signup")
    public Boys signup(@RequestBody Boys boys){
        return boysRepository.save(boys);
    }*/
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid Boys boys) {
        Boys createdBoys = boysService.signup(boys);

            return new ResponseEntity<>("Boys created successfully with ID: " + createdBoys.getId(), HttpStatus.CREATED);

    }

   /* @GetMapping("/{id}")
    public Boys getBoys(@PathVariable String id) {
    return boysRepository.findById(id).orElse(null);
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBoys(@PathVariable String id) {
        Optional<Boys> boysOpt = boysService.getBoys(id);
        return boysOpt
                .map(boys -> new ResponseEntity<Object>(boys, HttpStatus.OK))
                .orElseThrow(() -> new BoyNotFoundException("Boy not found"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Boys>> getAllBoys(){
     List<Boys> Boys=boysService.getAllBoys();
     if(Boys.isEmpty()){
         throw  new BoyNotFoundException("Boy not Found");
     }
         return new ResponseEntity<>(Boys,HttpStatus.OK);
    }
    /*@PutMapping("/{id}")
    public Boys update(@RequestBody Boys boys){
        return boysRepository.save(boys);
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBoys(@PathVariable String id, @RequestBody Boys boys) {
        Boys updatedBoys = boysService.updateBoys(id, boys);
        return new ResponseEntity<>("Boys updated successfully with ID: " + updatedBoys.getId(), HttpStatus.OK);
    }

    /*@DeleteMapping("/{id}")
    private void delete(@PathVariable("id") String myId){
        boysRepository.deleteById(myId);
    }*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBoys(@PathVariable String id) {
        boolean isDeleted = boysService.deleteBoys(id);
        if (isDeleted) {
            return new ResponseEntity<>("Boys deleted successfully", HttpStatus.OK);
        } else {
            throw  new BoyNotFoundException("Boys not found");
        }
    }

    /*@PatchMapping("/{id}")
    public Boys updateBoys(@PathVariable String id, @RequestBody Map<String, Object>updates){
        return boysService.updateBoys(id,updates);
    }*/
    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchBoys(@PathVariable String id, @RequestBody  Boys boys ) {
        Optional<Boys> existingBoysOpt = boysService.patchBoys(id, boys);
        return existingBoysOpt
                .map(Boys -> new ResponseEntity<Object>(Boys, HttpStatus.OK))
                .orElseThrow(() -> new BoyNotFoundException("Boy not found"));
    }
}
