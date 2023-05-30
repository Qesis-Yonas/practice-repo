package com.vaccinationcenter.service;

import com.vaccinationcenter.entity.PrintAllTogether;
import com.vaccinationcenter.entity.User;
import com.vaccinationcenter.entity.UserVacCenterResponse;
import com.vaccinationcenter.entity.VacCenter;
import com.vaccinationcenter.repository.VacCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacCenterService {

    @Autowired
    private VacCenterRepo vacCenterRepo;

    @Autowired
    private RestTemplate restTemplate;


    public VacCenter saveCenter(VacCenter vacCenter) {
        VacCenter savedCenter= vacCenterRepo.save(vacCenter);
        return savedCenter;

    }

    public VacCenter getCenterByVacId(int vacId) {
        VacCenter vacCenter=vacCenterRepo.findById(vacId).get();
        return vacCenter;
    }

    public List<VacCenter> getAllVacCenters() {
        List<VacCenter> vacCenterList = vacCenterRepo.findAll();
        return vacCenterList;
    }

    public UserVacCenterResponse getUserByVacCenterId(int vacCenId) {
        UserVacCenterResponse uvcResponse=new UserVacCenterResponse();

        VacCenter vacCenter=vacCenterRepo.findById(vacCenId).get();
        uvcResponse.setVacCenter(vacCenter);

        List<User> userList= restTemplate.getForObject("http://USER-SERVICE/user/getUserByVacCenter/"+vacCenId, List.class);
        uvcResponse.setUserList(userList);


        return uvcResponse;
    }


    public List<UserVacCenterResponse> getAllVacCenterWzAllUsers() {
        UserVacCenterResponse uVacCenRes= new UserVacCenterResponse();
        List<VacCenter> vacCenterList = vacCenterRepo.findAll();
        List<UserVacCenterResponse> resUVCResponse= new ArrayList<>();

        for(VacCenter result: vacCenterList){
            uVacCenRes.setVacCenter(result);

            int vacId= result.getVacId();
            List<User> userList= restTemplate.getForObject("http://USER-SERVICE/user/getUserByVacCenter/"+vacId, List.class);
            uVacCenRes.setUserList(userList);

            resUVCResponse.add(uVacCenRes);
        }
        return resUVCResponse;

//        List<VacCenter> v=vacCenterList.stream().collect(Collectors.toList());

//        if(vacCenterList.size()>0){
//            VacCenter popedVacCenter= vacCenterList.
//            uVacCenRes.setVacCenter(vacCenterList.);
//        }
    }

    public PrintAllTogether printAllTogether() {
        PrintAllTogether printAllTogether=new PrintAllTogether();
        List<UserVacCenterResponse> userVacCenterResponseList= restTemplate.getForObject("http://VACCINATION_CENTER-SERVICE/vacCenter/getAllVacCenterWzAllUsers",List.class);
        printAllTogether.setUserVacCenterResponseList(userVacCenterResponseList);

        return printAllTogether;
    }

    public UserVacCenterResponse handleUserDownTime1(int vacCenId) {
        UserVacCenterResponse uvcResponse= new UserVacCenterResponse();
        VacCenter vacCenter= vacCenterRepo.findById(vacCenId).get();
        uvcResponse.setVacCenter(vacCenter);
        return uvcResponse;
    }
}
