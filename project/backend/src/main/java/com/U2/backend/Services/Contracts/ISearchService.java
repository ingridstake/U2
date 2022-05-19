package com.U2.backend.Services.Contracts;

public interface ISearchService {

    String getAllHits(String searchParam);

    String getLimitedNoHits(String searchParam, int maxNo);
}
