package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Spot;

import java.util.List;

public interface SpotDao {
    // ------------------ [C] ------------------
    int insertSpot(Spot spot);

    int insertSpots(List<Spot> spots);

    // ------------------ [R] ------------------
    Spot searchSpotOne(String title);

    List<Spot> searchSpots(PageInfo pageInfo);

    int getSearchSpotsCount(String keyword);

    // ------------------ [U] ------------------

    // ------------------ [D] ------------------
    int deleteAllSpots();

}
