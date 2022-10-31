package com.springboot.hello.dao;

import com.springboot.hello.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class HospitalDao {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public HospitalDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }
    public void add(Hospital hospital){
        this.jdbcTemplate.update("INSERT INTO nation_wide_hospital(id, openServiceName, openLocalGovernmentCode, " +
                "managementNumber, licenseDate, businessStatus, businessStatusCode, phone, fullAddress, roadNameAddress, " +
                "hospitalName, businessTypeName, healthcareProviderCount, patientRoomCount, totalNumberOfBeds, totalAreaSize )" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }
}
