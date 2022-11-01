package com.springboot.hello.dao;

import com.springboot.hello.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Component
public class HospitalDao {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public HospitalDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }
    public void add(Hospital hospital){
        /*this.jdbcTemplate.update("INSERT INTO nation_wide_hospital(id, openServiceName, openLocalGovernmentCode, " +
                "managementNumber, licenseDate, businessStatus, businessStatusCode, phone, fullAddress, roadNameAddress, " +
                "hospitalName, businessTypeName, healthcareProviderCount, patientRoomCount, totalNumberOfBeds, totalAreaSize )" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");*/
        String sql = "INSERT INTO `like-lion-db`.`nation_wide_hospital`(`id`, `open_service_name`, `open_local_goverment_code`, `management_number`, \n" +
                "`license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, \n" +
                "`road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, \n" +
                "`patient_room_count`, `total_number_of_beds`, `total_area`) \n" +
                "VALUES(?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?);";
        jdbcTemplate.update(sql,
                hospital.getId(),hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(), hospital.getManagementNumber(),
                hospital.getLicenseDate(), hospital.getBusinessStatus(), hospital.getBusinessStatusCode(),
                hospital.getPhone(),hospital.getFullAddress(),hospital.getRoadNameAddress(),
                hospital.getHospitalName(), hospital.getBusinessTypeName(), hospital.getHealthcareProviderCount(),
                hospital.getPatientRoomCount(),hospital.getTotalNumberOfBeds(),hospital.getTotalAreaSize()
        );
    }
}
