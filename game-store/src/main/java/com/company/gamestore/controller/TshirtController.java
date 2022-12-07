package com.company.gamestore.controller;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TshirtController {
    @Autowired
    TshirtRepository repo;

    @GetMapping("/tshirts")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirts(){
        return repo.findAll();
    }

    @GetMapping("/tshirts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tshirt getTshirtById(@PathVariable int id) {
        Optional<Tshirt> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        }else {
            return null;
        }
    }
    @GetMapping("/tshirts/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtBySize(@PathVariable @Valid String size){
    return repo.findBySize(size);
    }


    @GetMapping("/tshirts/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List <Tshirt> getTshirtByColor (@PathVariable @Valid String color) {
       return repo.findByColor(color);
    }

    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt addTshirt(@RequestBody @Valid Tshirt tshirt) {
        return repo.save(tshirt);
    }

    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody @Valid Tshirt tshirt){
        repo.save(tshirt);
    }

    @DeleteMapping("/tshirts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int id){
        repo.deleteById(id);
    }
}

