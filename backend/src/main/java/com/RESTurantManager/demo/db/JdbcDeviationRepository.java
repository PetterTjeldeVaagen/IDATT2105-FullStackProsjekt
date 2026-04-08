package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
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
            "INSERT INTO deviations (title, description, registered_by, date_registered) VALUES (?, ?, ?, ?)",
            deviation.getName(),
            deviation.getDescription(),
            deviation.getRegisteredBy() != null ? deviation.getRegisteredBy().getEmployeeId() : null,
            deviation.getDateRegistered()
        );
    }

    @Override
    public Deviation findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM deviations WHERE deviation_id = ?",
                (rs, rowNum) -> {
                    Deviation deviation = new Deviation();
                    deviation.setDeviationId(rs.getInt("deviation_id"));
                    deviation.setName(rs.getString("title"));
                    deviation.setDescription(rs.getString("description"));
                    deviation.setDateRegistered(rs.getTimestamp("date_registered"));
                    int registeredBy = rs.getInt("registered_by");
                    if (!rs.wasNull()) {
                        com.RESTurantManager.demo.model.Employee employee = new com.RESTurantManager.demo.model.Employee();
                        employee.setEmployeeId(registeredBy);
                        deviation.setRegisteredBy(employee);
                    }
                    return deviation;
                },
                id
        );
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM deviations WHERE deviation_id = ?", id);
    }

    @Override
    public void update(Deviation deviation) {
        jdbcTemplate.update(
            "UPDATE deviations SET title = ?, description = ?, registered_by = ?, date_registered = ? WHERE deviation_id = ?",
            deviation.getName(),
            deviation.getDescription(),
            deviation.getRegisteredBy() != null ? deviation.getRegisteredBy().getEmployeeId() : null,
            deviation.getDateRegistered(),
            deviation.getDeviationId()
        );
    }

    @Override
    public Deviation[] findByEmployeeId(int employeeId) {
        return jdbcTemplate.query(
                "SELECT * FROM deviations WHERE registered_by = ?",
                (rs, rowNum) -> {
                    Deviation deviation = new Deviation();
                    deviation.setDeviationId(rs.getInt("deviation_id"));
                    deviation.setName(rs.getString("title"));
                    deviation.setDescription(rs.getString("description"));
                    deviation.setDateRegistered(rs.getTimestamp("date_registered"));
                    int registeredBy = rs.getInt("registered_by");
                    if (!rs.wasNull()) {
                        com.RESTurantManager.demo.model.Employee employee = new com.RESTurantManager.demo.model.Employee();
                        employee.setEmployeeId(registeredBy);
                        deviation.setRegisteredBy(employee);
                    }
                    return deviation;
                },
                employeeId
        ).toArray(new Deviation[0]);
    }
}
