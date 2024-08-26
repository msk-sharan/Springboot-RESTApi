package com.example.schoolAdmission.Controller;
import com.example.schoolAdmission.Exception.GirlNotFoundException;
import com.example.schoolAdmission.Model.Boys;
import com.example.schoolAdmission.Model.Girls;
import com.example.schoolAdmission.Repository.GirlsRepository;
import com.example.schoolAdmission.Service.GirlsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/girls")
public class GirlsController {
    @Autowired
    private GirlsRepository girlsRepository;
    @Autowired
    private GirlsService girlsService;

    /*@PostMapping("/signup")
    public Girls signup(@RequestBody Girls girls) {
        return girlsRepository.save(girls);
    }*/
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid Girls girls) {
        Girls createdGirls = girlsService.signup(girls);
        return new ResponseEntity<>("Girls created successfully with ID: " + createdGirls.getId(), HttpStatus.CREATED);
    }

   /* @GetMapping("/{id}")
    public Girls getBoys(@PathVariable String id) {
        return girlsRepository.findById(id).orElse(null);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGirls(@PathVariable String id) {
        Optional<Girls> girlsOpt = girlsService.getGirls(id);
        return girlsOpt
                .map(girls -> new ResponseEntity<Object>(girls, HttpStatus.OK))
                .orElseThrow(() -> new GirlNotFoundException("Girls not found"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Girls>> getAllGirls(){
        List<Girls> girls=girlsService.getAllGirls();
        if(girls.isEmpty()){
            throw  new GirlNotFoundException("Girl not found");
        }
        return new ResponseEntity<>(girls,HttpStatus.OK);
    }
    /*@PutMapping("/update")
    public Girls updateGirls(@RequestBody Girls girls){
        return girlsRepository.save(girls);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGirls(@PathVariable String id, @RequestBody Girls girls) {
        Girls updatedGirls = girlsService.updateGirls(id, girls);
        return new ResponseEntity<>("Girls updated successfully with ID: " + updatedGirls.getId(), HttpStatus.OK);
    }
    /*@DeleteMapping("/{id}")
    private void delete(@PathVariable("id") String myId){
        girlsRepository.deleteById(myId);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGirls(@PathVariable String id) {
        boolean isDeleted = girlsService.deleteGirls(id);
        if (isDeleted) {
            return new ResponseEntity<>("Girls deleted successfully", HttpStatus.OK);
        } else {
            throw  new GirlNotFoundException("Girls not found");
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchGirls(@PathVariable String id, @RequestBody Girls girls){
       Optional<Girls> existingGirlsOpt = girlsService.patchGirls(id,girls);
       return existingGirlsOpt
               .map(Girls->new ResponseEntity<Object>(Girls,HttpStatus.OK))
               .orElseThrow(()->new GirlNotFoundException("Girls not found"));
    }
}