package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {
    @Autowired
    ConsoleRepository repo;

    //GET Methods
    @GetMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable int id){
        Optional<Console> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/consoles")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles(){
        return repo.findAll();
    }

    @GetMapping("/consoles/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsolesByManufacturer(@PathVariable String manufacturer){
        List<Console> returnVal = null;
        returnVal = repo.findByManufacturer(manufacturer);
        return returnVal;
    }

    //POST Method
    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody @Valid Console console) {
        return repo.save(console);
    }

    //PUT Method
    @PutMapping("/consoles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody @Valid Console console) {
        repo.save(console);
    }

    //DELETE Method
    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        repo.deleteById(id);
    }
}
