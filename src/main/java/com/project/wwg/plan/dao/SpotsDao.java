package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.Page;
import com.project.wwg.plan.dto.Spot;

import java.util.List;

/**
 * 아이템 DAO
 * @author giri
 */
public interface SpotsDao {
    List<Spot> getSpotsList(int startRow, int endRow);

    Spot searchSpotOne(String title);

    List<Spot> searchSpots(Page page);

    int getSearchSpotsCount(String keyword);

    void insertSpot(Spot spot);

    int insertSpots(List<Spot> spots);

    void deleteSpot(String id);

    int deleteAllSpots();
}
