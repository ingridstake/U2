package com.U2.backend.DataObjectContracts;

public interface IEvent {
    String getId();
    IVenue getVenue();
    String getName();
    String getImageUrl();
}
