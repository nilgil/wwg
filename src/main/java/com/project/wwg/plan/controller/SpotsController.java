package com.project.wwg.plan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Spot;
import com.project.wwg.plan.service.SpotsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 관광지 데이터 컨트롤러
 *
 * @author giri
 */
@Controller
@RequestMapping("/spots*")
@Slf4j
public class SpotsController {
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    private final JSONParser jsonParser = new JSONParser();
    private SpotsServiceImpl spotService;

    @Autowired
    public SpotsController(SpotsServiceImpl spotService) {
        this.spotService = spotService;
    }

    // ----------------------------- Form 이동 ------------------------------

    @GetMapping("/insert")
    public String insertSpotForm() {
        return "/plan/insertSpotForm";
    }


    // ----------------------------- Spot CRUD -----------------------------

    /**
     * [R] title로 Spot 검색한 List와 Page객체 (검색어, 페이지번호, 결과 개수)를 JSON 타입으로 리턴
     */
    @PostMapping(value = "/search", produces = "application/json; charset=utf8")
    @ResponseBody
    public String getSearchSpotsByPage(String keyword, int pageNum) throws Exception {
        log.debug("Search Spots By Keyword & PageNum | Keyword : {}, PageNum : {}", keyword, pageNum);

        List<Spot> spots = spotService.searchSpots(keyword, pageNum);
        int searchCount = spotService.getSearchSpotsCount(keyword);
        PageInfo pageInfo = new PageInfo(8, keyword, pageNum, searchCount);

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(spots);
        String pageInfoString = mapper.writeValueAsString(pageInfo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        jsonObject.put("page_info", pageInfoString);

        System.out.println("pageInfoString = " + pageInfoString);

        return jsonObject.toJSONString();
    }

    @PostMapping(value = "/searchOne", produces = "application/json; charset=utf8")
    @ResponseBody
    public String getSearchSpotOne(String title) throws Exception {
        log.debug("Search Title Keyword : {}", title);
        Spot spot = spotService.searchSpotOne(title);

        String result = gson.toJson(spot);
        log.debug("Search Result : {}", result);
        return result;
    }

    @PostMapping(value = "/searchArray", produces = "application/json; charset=utf8")
    @ResponseBody
    public String searchSpotsByTitles(String titles) throws ParseException {
        log.debug("searchSpotsByTitles | Parameter : titles = {}", titles);
        if (titles.equals("[]")) {
            return "{\"response\":\"none\"}";
        }
        String jsonSpots = null;
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(titles);
            System.out.println("jsonArray = " + jsonArray);
            List<String> titleList = new ArrayList<String>();
            for (int i = 0; i < jsonArray.size(); i++) {
                titleList.add(jsonArray.get(i).toString());
            }

            List<Spot> spots = spotService.searchSpotsByTitles(titleList);
            jsonSpots = gson.toJson(spots);
            log.debug("searchSpotsByTitles | Response : {}", jsonSpots);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonSpots;
    }

    /**
     * [C] Spot 추가
     */
    @PostMapping("/insert")
    public String insertSpot(@RequestBody Spot spot) {
        int result = spotService.insertSpot(spot);

        return "/plan/spotsList";
    }


    /**
     * [D] id로 Spot 제거
     */
    @GetMapping("/id/{id}")
    public String deleteSpot(String id) {
        int result = spotService.deleteSpot(id);
        return "/plan/spotsList";
    }


    // ------------------------------ API 관련 ------------------------------


    /**
     * DB의 모든 Spot을 API 연결하여 리셋
     * Security 쪽에서 접근 제한 걸어줘야함
     */
    @GetMapping("/reset")
    public String resetAllSpotsFromApi() {
        int result = spotService.resetAllSpotsFromApi();
        System.out.println("Reset All Spots From API >> Result = " + result + " Spots Exist");
        return "/plan/resetAllSpotsResult";
    }

}
