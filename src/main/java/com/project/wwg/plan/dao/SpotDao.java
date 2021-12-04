package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Spot;

import java.util.List;

/**
 * 아이템 DAO
 * @author giri
 */
public interface SpotDao {
    List<Spot> getSpotsList(int startRow, int endRow);

    Spot searchSpotOne(String title);

    List<Spot> searchSpots(PageInfo pageInfo);

    List<Spot> searchSpotsByTitles(List<String> titleList);

    int getSearchSpotsCount(String keyword);

    int insertSpot(Spot spot);

    int insertSpots(List<Spot> spots);

    int deleteSpot(String id);

    int deleteAllSpots();

}
