package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RESTurantManager.demo.db.interfaces.DeviationRepository;
import com.RESTurantManager.demo.model.Deviation;

/**
 * Repository class for managing deviations in the database.
 */
@Repository
public class JdbcDeviationRepository implements DeviationRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Saves a new deviation to the database.
     * @param deviation the deviation to be saved
     */
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

    /**
     * Finds a deviation by its ID.
     * @param id the ID of the deviation
     * @return the deviation with the specified ID
     */
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

    /**
     * Deletes a deviation by its ID.
     * @param id the ID of the deviation to be deleted
     */
    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM deviations WHERE deviation_id = ?", id);
    }

    /**
     * Updates an existing deviation in the database.
     * @param deviation the deviation to be updated
     */
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

    /** 
     * Finds deviations by the ID of the employee who registered them.
     * @param employeeId the ID of the employee
     * @return an array of deviations registered by the specified employee
     */
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
