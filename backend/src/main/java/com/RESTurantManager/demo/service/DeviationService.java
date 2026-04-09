package com.RESTurantManager.demo.service;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.DeviationRepository;
import com.RESTurantManager.demo.model.Deviation;

/**
 * Service class for handling deviation-related operations, such as creating, retrieving, updating, and deleting deviations.
 */
@Service
public class DeviationService {
    private final DeviationRepository deviationRepository;

    /**
     * Constructor for DeviationService. Initializes the deviation repository for managing deviation data.
     * @param deviationRepository the repository for managing deviation data
     */
    public DeviationService(DeviationRepository deviationRepository) {
        this.deviationRepository = deviationRepository;
    }

    /**
     * Creates a new deviation by saving it to the deviation repository.
     * @param deviation the deviation to be created
     */
    public void createDeviation(Deviation deviation) {
        deviationRepository.save(deviation);
    }
    
    /**
     * Retrieves a deviation by its ID from the deviation repository.
     * @param id the ID of the deviation to be retrieved
     * @return the deviation with the specified ID
     */
    public Deviation getDeviationById(int id) {
        return deviationRepository.findById(id);
    }

    /**
     * Deletes a deviation by its ID from the deviation repository.
     * @param id the ID of the deviation to be deleted
     */
    public void deleteDeviationById(int id) {
        deviationRepository.deleteById(id);
    }

    /**
     * Updates an existing deviation in the deviation repository.
     * @param deviation the deviation with updated information to be saved
     */
    public void updateDeviation(Deviation deviation) {
        deviationRepository.update(deviation);
    }

    /**
     * Retrieves all deviations associated with a specific employee ID from the deviation repository.
     * @param employeeId the ID of the employee whose deviations are to be retrieved
     * @return an array of deviations associated with the specified employee ID
     */
    public Deviation[] getDeviationsByEmployeeId(int employeeId) {
        return deviationRepository.findByEmployeeId(employeeId);
    }
}
