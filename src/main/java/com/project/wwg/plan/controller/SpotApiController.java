package com.project.wwg.plan.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Spot;
import com.project.wwg.plan.service.SpotService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/spot")
public class SpotApiController {

    private final SpotService spotService;
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    private final JSONParser jsonParser = new JSONParser();

    public SpotApiController(SpotService spotService) {
        this.spotService = spotService;
    }

    /**
     * 검색어와 페이지 번호로 관광지 목록, 페이지 정보 가져오기
     */
    @GetMapping("/search")
    public String getSearchSpotsByPage(String keyword, int pageNum) {
        List<Spot> spots = spotService.searchSpots(keyword, pageNum);
        int searchCount = spotService.getSearchSpotsCount(keyword);
        PageInfo pageInfo = new PageInfo(8, pageNum, searchCount, keyword);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", gson.toJson(spots));
        jsonObject.put("page_info", gson.toJson(pageInfo));

        return jsonObject.toJSONString();
    }

    /**
     * 정확한 관광지 이름으로 하나의 관광지 세부정보 가져오기
     */
    @GetMapping("/search/one")
    public String getSearchSpot(String title) {
        Spot spot = spotService.searchSpotOne(title);
        return gson.toJson(spot);
    }

    /**
     * 관광지 이름 배열로 여러개의 관광지 세부정보 가져오기
     */
    @GetMapping("/search/many")
    public String getSearchSpotsByTitles(String titles) throws ParseException {
        if (titles.equals("[]")) {
            return "{\"response\":\"none\"}";
        }
        JSONArray jsonArray = (JSONArray) jsonParser.parse(titles);
        String[] titlesArray = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            titlesArray[i] = jsonArray.get(i).toString();
        }
        List<Spot> spots = spotService.searchSpotsByTitles(titlesArray);
        return gson.toJson(spots);
    }

    /**
     * 외부 API에 접근하여 DB에 저장된 모든 관광지 정보를 리셋
     */
    @PostMapping("/reset")
    @Transactional
    public int resetAllSpots() {
        return spotService.resetAllSpotsFromApi();
    }

}
