package org.cnu.realcoding.homework0420.controller;

import org.cnu.realcoding.homework0420.domain.Dog;
import org.cnu.realcoding.homework0420.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
public class DogController {

    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDog(@RequestBody Dog dog) {
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs")
    @ResponseStatus(HttpStatus.OK)
    public Dog getDog(@RequestParam(value="name") String name, @RequestParam(value="ownerName") String ownerName,
                             @RequestParam(value="ownerPhoneNumber") String ownerPhoneNumber){
        return dogManagementService.getDog(name, ownerName, ownerPhoneNumber);
    }

    @GetMapping("/dogs/names/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Dog getDogByName(@PathVariable String name)
    {
        return dogManagementService.getDogByName(name);
    }


    @GetMapping("/dogs/ownerName/{ownerName}")
    @ResponseStatus(HttpStatus.OK)
    public Dog getDogByOwnerName(@PathVariable String ownerName)
    {
        return dogManagementService.getDogByOwnerName(ownerName);
    }

    @GetMapping("/dogs/ownerPhoneNumber/{phoneNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Dog getDogByOwnerPhoneNumber(@PathVariable("phoneNumber") String ownerPhoneNumber)
    {
        return dogManagementService.getDogByOwnerPhoneNumber(ownerPhoneNumber);
    }


    @PutMapping("/dogs/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDog(@PathVariable String name, @RequestBody Dog body){
        dogManagementService.updateDog(name, body);
    }

    // 강아지 진료기록 추가 API
    @PatchMapping("/dogs/{name}/medicalRecords")
    @ResponseStatus(HttpStatus.OK)
    public void updateMedicalRecords(@PathVariable String name, @RequestBody String medicalRecord) {
        dogManagementService.addMedicalRecord(name, medicalRecord);
    }


    //강아지 종류 수정
    @PatchMapping("/dogs/{name}/kinds/{kind}")
    @ResponseStatus(HttpStatus.OK)
    public void updateKind(@PathVariable("name") String name, @PathVariable("kind") String kind) {
        dogManagementService.updateKind(name, kind);
    }
}