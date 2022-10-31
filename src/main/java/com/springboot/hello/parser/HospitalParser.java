package com.springboot.hello.parser;

import com.springboot.hello.domain.Hospital;

import java.time.LocalDateTime;
import java.util.Arrays;

public class HospitalParser implements Parser<Hospital> {
    @Override
    public Hospital parse(String str) {
        //String[] row = str.split("\",\""); // 따옴표를 기준으로 split
        String[] row = str.replace("\"", "").split(",");
        System.out.println(Arrays.toString(row));

        // 객체 생성
        Hospital hospital = new Hospital();

        // setter
        hospital.setId(Integer.parseInt(row[0].replace("\n","")));
        hospital.setOpenServiceName(row[1]);
        hospital.setOpenLocalGovernmentCode(Integer.parseInt(row[3]));
        hospital.setManagementNumber(row[4]);
        // substring
        int year = Integer.parseInt(row[5].substring(0, 4));
        int month = Integer.parseInt(row[5].substring(4, 6));
        int day = Integer.parseInt(row[5].substring(6, 7));
        // System.out.printf("%d %d %d \n", year, month, day);
        hospital.setLicenseDate(LocalDateTime.of(year, month, day, 0, 0, 0));

        hospital.setBusinessStatus(Integer.parseInt(row[7]));
        hospital.setBusinessStatusCode(Integer.parseInt(row[9]));
        hospital.setPhone(row[15]);
        hospital.setFullAddress(row[18]);
        hospital.setRoadNameAddress(row[19]);
        hospital.setHospitalName(row[21]);
        hospital.setBusinessTypeName(row[25]);
        hospital.setHealthcareProviderCount(Integer.parseInt(row[29]));
        hospital.setPatientRoomCount(Integer.parseInt(row[30]));
        hospital.setTotalNumberOfBeds(Integer.parseInt(row[31]));
        hospital.setTotalAreaSize(Integer.parseInt(row[32].replace("\"","")));

        return hospital;
    }
}
