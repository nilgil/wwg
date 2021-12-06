package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.SpotDao;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Spot;
import com.project.wwg.plan.exceptions.NotExistDataException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpotService {

    private final SpotDao spotDao;
    private final JSONParser parser = new JSONParser();
    private List<Spot> spots;

    public SpotService(SpotDao spotDao) {
        this.spotDao = spotDao;
    }

    public int insertSpots(List<Spot> spots) {
        return spotDao.insertSpots(spots);
    }

    public Spot searchSpotOne(String title) {
        return spotDao.searchSpotOne(title);
    }

    public List<Spot> searchSpots(String keyword, int pageNum) {
        PageInfo pageInfo = new PageInfo(8, pageNum, keyword);
        return spotDao.searchSpots(pageInfo);
    }

    public List<Spot> searchSpotsByTitles(String[] titleList) {
        List<Spot> list = new ArrayList<>();
        for (String title : titleList) {
            list.add(spotDao.searchSpotOne(title));
        }
        return list;
    }

    public int getSearchSpotsCount(String keyword) {
        return spotDao.getSearchSpotsCount(keyword);
    }

    public int deleteAllSpots() {
        return spotDao.deleteAllSpots();
    }


    // ---------------------------- API 연동 관련 ----------------------------

    /**
     * 외부 API로부터 관광지 데이터 받고, 기존 데이터 초기화 (종합)
     * 1. 외부 API 데이터 개수 확인
     * 2. JSON 형식 데이터를 파싱하여 List에 담기
     * 3. 기존 모든 관광지 데이터 삭제
     * 4. List에 담은 모든 관광지 데이터 등록
     */
    @Transactional
    public synchronized int resetAllSpots() {
        spots = new ArrayList<>();
        int result = 0;
        try {
            int pageCount = getPageCountFromApi();
            if (pageCount == 0) {
                throw new NotExistDataException("데이터가 존재하지 않습니다.");
            }
            for (int i = 1; i <= pageCount; i++) {
                jsonToList(searchSpotsFromApi(i));
            }
            deleteAllSpots();
            result = insertSpots(spots);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 외부 API 1페이지 데이터 조회
     */
    public String searchSpotsFromApi() {
        return searchSpotsFromApi(1);
    }

    /**
     * 외부 API의 n페이지 데이터 조회
     *
     * @Return JSON 타입 문자열
     */
    public String searchSpotsFromApi(int page) {
        StringBuilder sb = new StringBuilder();

        String host = "https://api.visitjeju.net";
        String path = "/vsjApi/contents/searchList";
        String apiKey = "sh2krg8tnt28ayuk";
        String locale = "kr";
        String params = "?apiKey=" + apiKey + "&locale=" + locale + "&page=";

        try {
            URL url = new URL(host + path + params + page);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * API의 1페이지 조회하여 pageCount값 찾기
     *
     * @Return 외부 API의 전체 페이지 개수
     */
    public int getPageCountFromApi() {
        String response = searchSpotsFromApi();
        int pageCount = 0;
        try {
            JSONObject responseObj = (JSONObject) parser.parse(response);
            pageCount = ((Long) responseObj.get("pageCount")).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageCount;
    }

    /**
     * JSON 타입 문자열 데이터를 파싱하여 List에 담기
     */
    public void jsonToList(String response) {
        try {
            JSONObject responseObj = (JSONObject) parser.parse(response);
            JSONArray jsonArray = (JSONArray) responseObj.get("items");

            for (Object obj : jsonArray) {
                JSONObject currentObj = (JSONObject) obj;

                /* Title */
                String title = (String) currentObj.get("title");
                if (title == null || title.equals("")) {
                    continue;
                }

                /* Info */
                String info = (String) currentObj.get("introduction");
                if (info == null || info.equals(""))
                    info = "--";

                /* Lat,Lng */
                Double latD = (Double) currentObj.get("latitude");
                Double lngD = (Double) currentObj.get("longitude");
                if (latD == null || latD == 0 || lngD == null || lngD == 0) {
                    continue;
                }
                double lat = latD;
                double lng = lngD;

                /* Address */
                String address = (String) currentObj.get("roadaddress");
                if (address == null || address.equals(""))
                    address = "--";

                /* Photo */
                String photo = "";
                JSONObject photo1 = (JSONObject) currentObj.get("repPhoto");
                if (photo1 != null && !photo1.equals("")) {
                    JSONObject photo2 = (JSONObject) photo1.get("photoid");
                    if (photo2 != null && !photo2.equals("")) {
                        photo = (String) photo2.get("imgpath");
                    }
                }
                if (photo == null || photo.equals("")) {
                    photo = "--";
                }

                /* Phone */
                String phone = (String) currentObj.get("phoneno");
                if (phone == null || phone.equals("")) {
                    phone = "--";
                }

                Spot spot = new Spot(title, info, lat, lng, address, photo, phone);
                spots.add(spot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
