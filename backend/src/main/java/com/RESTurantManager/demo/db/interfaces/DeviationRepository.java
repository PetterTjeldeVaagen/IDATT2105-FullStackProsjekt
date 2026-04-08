package com.RESTurantManager.demo.db.interfaces;

import com.RESTurantManager.demo.model.Deviation;

public interface DeviationRepository {
    void save(Deviation deviation);

    Deviation findById(int id);

    void deleteById(int id);

    void update(Deviation deviation);

    Deviation[] findByEmployeeId(int employeeId);

    Deviation[] findByResturantId(int resturantId);
}
