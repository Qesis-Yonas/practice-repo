package com.vaccinationcenter.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vaccinationcenter.entity.PrintAllTogether;
import com.vaccinationcenter.entity.User;
import com.vaccinationcenter.entity.UserVacCenterResponse;
import com.vaccinationcenter.entity.VacCenter;
import com.vaccinationcenter.service.VacCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacCenter")
public class VacCenterController {

    @Autowired
    private VacCenterService vacCenterService;

    @PostMapping("/saveCenter")
    public ResponseEntity<?> saveCenter(@RequestBody VacCenter vacCenter){
        VacCenter savedCenter = vacCenterService.saveCenter(vacCenter);
        return new ResponseEntity<VacCenter>(savedCenter, HttpStatus.OK);
    }

    @GetMapping("/getCenter/{vacId}")
    public ResponseEntity<?> getCenterByVacId(@PathVariable int vacId){
        VacCenter vacCenter = vacCenterService.getCenterByVacId(vacId);
        return new ResponseEntity<VacCenter>(vacCenter,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllVacCenters(){
        List<VacCenter> vacCenterList=vacCenterService.getAllVacCenters();
        return  new ResponseEntity<List<VacCenter>>(vacCenterList,HttpStatus.OK);
    }

    @GetMapping("/getUserByVacCenterId/{vacCenId}")
    @HystrixCommand(fallbackMethod = "handleUserDownTime")
    public ResponseEntity<?> getUserByVacCenterId(@PathVariable int vacCenId){
        UserVacCenterResponse uvcRes=vacCenterService.getUserByVacCenterId(vacCenId);
        return new ResponseEntity<UserVacCenterResponse>(uvcRes,HttpStatus.OK);
    }

    public ResponseEntity<?> handleUserDownTime(@PathVariable int vacCenId){
        UserVacCenterResponse uvcRes= new UserVacCenterResponse();
        VacCenter vacCenter= vacCenterService.getCenterByVacId(vacCenId);

        uvcRes.setVacCenter(vacCenter);
//        UserVacCenterResponse uvcRes=vacCenterService.handleUserDownTime1(vacCenId);
        return new ResponseEntity<UserVacCenterResponse>(uvcRes,HttpStatus.OK);
    }

    @GetMapping("/getAllVacCenterWzAllUsers")
    public ResponseEntity<?> getAllVacCenterWzAllUsers(){
        List<UserVacCenterResponse> uvcResList= vacCenterService.getAllVacCenterWzAllUsers();
        return new ResponseEntity<List<UserVacCenterResponse>>(uvcResList,HttpStatus.OK);
    }

    @GetMapping("/printAllTogether")
    public ResponseEntity<?> printAllTogether(){
        PrintAllTogether printAllTogether= vacCenterService.printAllTogether();
        return new ResponseEntity<PrintAllTogether>(printAllTogether,HttpStatus.OK);
    }


    @GetMapping("/getUserByVacCenterIdHestrix/{vacCenId}")
    public ResponseEntity<?> getUserByVacCenterIdUsingHestrix(@PathVariable int vacCenId){
        UserVacCenterResponse uvcRes=vacCenterService.getUserByVacCenterId(vacCenId);
        return new ResponseEntity<UserVacCenterResponse>(uvcRes,HttpStatus.OK);
    }
}
