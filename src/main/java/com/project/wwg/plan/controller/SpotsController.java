package com.project.wwg.plan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.wwg.plan.dto.Page;
import com.project.wwg.plan.dto.Spot;
import com.project.wwg.plan.service.SpotsServiceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 아이템 컨트롤러
 *
 * @author giri
 */
@Controller
@RequestMapping("/spots*")
public class SpotsController {

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
     * title로 Spot 검색
     */
    @GetMapping(value = "/title/{keyword}/page/{pageNum}", produces = "application/json; charset=utf8")
    @ResponseBody
    public String getSearchSpotsByPage(@PathVariable String keyword, @PathVariable int pageNum, Model model) throws JsonProcessingException {
        List<Spot> spots = spotService.searchSpots(keyword, pageNum);
        int searchCount = spotService.getSearchSpotsCount(keyword);
        Page page = new Page(keyword, pageNum, searchCount);

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(spots);
        String pageInfo = mapper.writeValueAsString(page);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        jsonObject.put("page_info", pageInfo);

        System.out.println("검색어 : " + keyword);
        System.out.println("Page : " + pageNum);
        return jsonObject.toJSONString();
    }

    @PostMapping("/insert")
    public String insertSpot(@RequestBody Spot spot) {
        spotService.insertSpot(spot);
        return "/plan/spotsList";
    }

    @GetMapping("/id/{id}")
    public String deleteSpot(String id) {
        spotService.deleteSpot(id);
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
