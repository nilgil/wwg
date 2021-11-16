package com.project.wwg.plan.service;

import com.project.wwg.plan.dto.Spot;

import java.util.List;

public interface SpotService {
    // -------------- Spot CRUD --------------
    List<Spot> searchSpots(String keyword);

    void insertSpot(Spot spot);

    int insertSpots(List<Spot> spots);

    void deleteSpot(String id);

    int deleteAllSpots();

    // --------------- Spot Reset 관련 ---------------

    int resetAllSpots();

    String searchSpotsFromApi();

    String searchSpotsFromApi(int page);

    int getPageCountFromApi();

    void jsonToList(String response);
}
