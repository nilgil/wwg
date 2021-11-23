package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.SpotsDao;
import com.project.wwg.plan.dto.Page;
import com.project.wwg.plan.dto.Spot;
import com.project.wwg.plan.exceptions.NotAvailableDataException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpotsServiceImpl {

    private final SpotsDao spotsDao;
    private final JSONParser parser = new JSONParser();
    private List<Spot> spots;

    public SpotsServiceImpl(SpotsDao spotsDao) {
        this.spotsDao = spotsDao;
    }

    // ----------------------------- Spot CRUD -----------------------------

    /**
     * [C] Spot 1개 등록
     */
    public void insertSpot(Spot spot) {
        spotsDao.insertSpot(spot);
    }

    /**
     * [C] Spot 여러 개 등록
     */
    public int insertSpots(List<Spot> spots) {
        return spotsDao.insertSpots(spots);
    }

    /**
     * [R] 검색어로 Spot 검색하여 list로 반환
     */
    public List<Spot> searchSpots(String keyword, int pageNum) {
        Page page = new Page(keyword,pageNum);
        return spotsDao.searchSpots(page);
    }

    public int getSearchSpotsCount(String keyword) {
        return spotsDao.getSearchSpotsCount(keyword);
    }

    /**
     * [D] id로 Spot 1개 삭제
     */
    public void deleteSpot(String id) {
        spotsDao.deleteSpot(id);
    }

    /**
     * [D] 모든 Spot 삭제
     */
    public int deleteAllSpots() {
        return spotsDao.deleteAllSpots();
    }

    /**
     * [ETC] API의 모든 Spots를 파싱하여 DB에 저장
     *
     * @return 저장된 Spot의 개수 리턴
     */
    public int resetAllSpotsFromApi() {
        spots = new ArrayList<Spot>();
        int result = 0;
        try {
            int pageCount = getPageCountFromApi();
            if (pageCount == 0) {
                throw new NotAvailableDataException("데이터가 존재하지 않습니다.");
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

    // ------------------------------ API 관련 ------------------------------

    /**
     * API의 1페이지 Spots 조회
     */
    public String searchSpotsFromApi() {
        return searchSpotsFromApi(1);
    }

    /**
     * API의 n페이지 Spots 조회
     *
     * @return JSON 형식의 String response 리턴
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
     * @return json형식의 String 리턴
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
     * JSON 타입의 String response를 파싱하여 List에 담기
     */
    public void jsonToList(String response) {
        try {
            JSONObject responseObj = (JSONObject) parser.parse(response);
            JSONArray jsonArray = (JSONArray) responseObj.get("items");

            for (Object obj : jsonArray) {
                JSONObject currentObj = (JSONObject) obj;

                /* Title */
                String title = (String) currentObj.get("title");
                if (title == null) {
                    title = "--";
                }

                /* Info */
                String info = (String) currentObj.get("introduction");
                if (info == null)
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
                if (address == null)
                    address = "--";

                /* Photo */
                String photo = "";
                JSONObject photo1 = (JSONObject) currentObj.get("repPhoto");
                if (photo1 != null) {
                    JSONObject photo2 = (JSONObject) photo1.get("photoid");
                    if (photo2 != null) {
                        photo = (String) photo2.get("imgpath");
                    }
                }
                if (photo == null) {
                    photo = "--";
                }

                /* Phone */
                String phone = (String) currentObj.get("phoneno");
                if (phone == null) {
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
