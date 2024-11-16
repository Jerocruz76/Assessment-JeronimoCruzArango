package com.example.demo.application.services.interfaces;

import com.example.demo.application.services.crud.Create;
import com.example.demo.application.services.crud.Delete;
import com.example.demo.application.services.crud.GetById;
import com.example.demo.application.services.crud.Update;
import com.example.demo.domain.model.entities.Availability;

public interface AvailabilityService extends
        Create<Availability, Availability>,
        Update<Long, Availability>,
        GetById<Availability, Long>,
        Delete<Long> {
}
