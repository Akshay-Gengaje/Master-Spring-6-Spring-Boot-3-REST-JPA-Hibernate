package com.darklord.school.repository;

import com.darklord.school.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidayRepository {
    private  final JdbcTemplate jdbcTemplate;
    @Autowired
    public HolidayRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAllHolidays(){
        String sql = "SELECT * FROM HOLIDAYS";
        RowMapper<Holiday> rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        return  jdbcTemplate.query(sql, rowMapper );
    }
}
