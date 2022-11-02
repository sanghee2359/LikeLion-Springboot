package com.springboot.hello.Controller;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.domain.Hospital;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//id 110000의 1병원 이름, 2주소, 3도로명주소, 4의료진 수, 5병상 수, 6면적, 7폐업여부
@RestController
@RequestMapping("/api/v3/get-api")
public class HospitalController {
    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @GetMapping(value ="/id/{hospitalId}")
    public String getId(@PathVariable int hospitalId){
        boolean closing = false;
        String str ="";
        Hospital hospital = hospitalDao.findById(hospitalId);
        str += "병원이름: "+hospital.getHospitalName()+"\n";
        str += "주소 : "+hospital.getFullAddress()+"\n";
        str += "도로명 주소: "+hospital.getRoadNameAddress()+"\n";
        str += "의료진 수: "+hospital.getHealthcareProviderCount()+"\n";
        str += "병상 수: "+hospital.getTotalNumberOfBeds()+"\n";
        str += "면적: "+hospital.getTotalAreaSize()+"\n";
//        System.out.printf("병원이름 : %s\n",hospital.getHospitalName());
        if(hospital.getBusinessStatus() == 3) closing = true;
        else closing = false;
        str+= "폐업 여부 : "+closing;
        return str;
    }
}
