package com.RESTurantManager.demo.service;

import java.lang.reflect.Array;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.DeviationRepository;
import com.RESTurantManager.demo.model.Deviation;

@Service
public class DeviationService {
    private final DeviationRepository deviationRepository;

    public DeviationService(DeviationRepository deviationRepository) {
        this.deviationRepository = deviationRepository;
    }

    public void createDeviation(Deviation deviation) {
        deviationRepository.save(deviation);
    }
    
    public Deviation getDeviationById(int id) {
        return deviationRepository.findById(id);
    }

    public void deleteDeviationById(int id) {
        deviationRepository.deleteById(id);
    }

    public void updateDeviation(Deviation deviation) {
        deviationRepository.update(deviation);
    }

    public Deviation[] getDeviationsByEmployeeId(int employeeId) {
        return deviationRepository.findByEmployeeId(employeeId);
    }

    public Deviation[] getDeviationsByResturantId(int resturantId) {
        return deviationRepository.findByResturantId(resturantId);
    }
}
