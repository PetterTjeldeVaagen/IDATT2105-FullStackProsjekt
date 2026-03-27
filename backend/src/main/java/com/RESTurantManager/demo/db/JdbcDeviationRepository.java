package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RESTurantManager.demo.db.interfaces.DeviationRepository;
import com.RESTurantManager.demo.model.Deviation;

@Repository
public class JdbcDeviationRepository implements DeviationRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Deviation deviation) {
        jdbcTemplate.update(
                "INSERT INTO deviations (name, deviation_id, description, registered_by, date_registered) VALUES (?, ?, ?, ?, ?)",
                deviation.getName(),
                deviation.getDeviationId(),
                deviation.getDescription(),
                deviation.getRegisteredBy().getEmployeeId(),
                deviation.getDateRegistered()
        );
    }

    @Override
    public Deviation findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM deviations WHERE deviation_id = ?",
                new BeanPropertyRowMapper<>(Deviation.class),
                id
        );
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM deviations WHERE deviation_id = ?", id);
    }
    
}
