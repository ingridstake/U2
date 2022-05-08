package com.U2.backend.Services.Contracts;

import com.U2.backend.Data.DataObjectContracts.IEvent;
import com.U2.backend.Data.DataObjectContracts.IVenue;

import java.util.List;

public interface IAPIDataService {

    List<IEvent> getEvents();
    List<IVenue> getVenues();
}
