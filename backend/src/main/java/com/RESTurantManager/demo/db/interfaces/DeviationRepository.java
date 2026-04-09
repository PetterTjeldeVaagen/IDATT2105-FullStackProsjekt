package com.RESTurantManager.demo.db.interfaces;

import com.RESTurantManager.demo.model.Deviation;

/**
 * Interface for managing deviations in the database. Provides methods for saving, finding, deleting, and updating deviations, as well as retrieving deviations by employee ID.
 */
public interface DeviationRepository {
    /**
     * Saves a new deviation to the database.
     * @param deviation the deviation to be saved
     */
    void save(Deviation deviation);

    /**
     * Finds a deviation by its ID in the database.
     * @param id the ID of the deviation to be found
     * @return the deviation with the specified ID
     */
    Deviation findById(int id);

    /**
     * Deletes a deviation by its ID from the database.
     * @param id the ID of the deviation to be deleted
     */
    void deleteById(int id);

    /**
     * Updates an existing deviation in the database with new information provided in a Deviation object.
     * @param deviation the deviation to be updated
     */
    void update(Deviation deviation);

    /**
     * Retrieves all deviations associated with a specific employee ID from the database.
     * @param employeeId the ID of the employee whose deviations are to be retrieved
     * @return an array of deviations associated with the specified employee ID
     */
    Deviation[] findByEmployeeId(int employeeId);
}
