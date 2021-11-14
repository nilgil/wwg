package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.ItemsDao;
import com.project.wwg.plan.dto.Item;
import com.project.wwg.plan.exceptions.NotAvailableDataException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * API 아이템 업데이트 서비스
 *
 * @author giri
 */
@Service
public class ItemsResetFromApiService {
    private final String host = "https://api.visitjeju.net";
    private final String path = "/vsjApi/contents/searchList";
    private final String apiKey = "sh2krg8tnt28ayuk";
    private final String locale = "kr";
    private final String params = "?apiKey=" + apiKey + "&locale=" + locale + "&page=";
    private final JSONParser parser = new JSONParser();

    private List<Item> items;
    private Item item;
    private JSONObject responseObj;
    private JSONArray jsonArray;
    private JSONObject currentObj;
    private Object dummy = new JSONObject();
    private StringBuilder stringBuilder;
    private ItemsDao itemsDao;
    private URL url;
    private HttpURLConnection connection;
    private BufferedReader br;
    private String line;

    @Autowired
    public ItemsResetFromApiService(ItemsDao itemsDao) {
        this.itemsDao = itemsDao;
    }

    /**
     * API의 모든 Items를 DB에 저장
     *
     * @return 저장된 Item의 개수 리턴
     */
    public int itemsUpdateFromApi() {
        int result = 0;
        try {
            int pageCount = getPageCount();
            if (pageCount == 0) {
                throw new NotAvailableDataException("데이터가 존재하지 않습니다.");
            }
            items = new ArrayList<Item>();

            for (int i = 1; i <= pageCount; i++) {
                itemsToList(searchApi(i));
            }

            System.out.println("Delete Items : " + deleteAllItems());
            System.out.println("New Items : " + items.size());
            result = itemsDao.insertItems(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * API에서 Items 1페이지 조회
     *
     * @return
     */
    public String searchApi() {
        return searchApi(1);
    }


    /**
     * API에서 Items n페이지 조회
     *
     * @param page
     * @return json형식의 String 리턴
     */
    public String searchApi(int page) {
        stringBuilder = new StringBuilder();

        try {
            url = new URL(host + path + params + page);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * API에서 Items 1페이지 조회하여 pageCount값 찾기
     *
     * @return json형식의 String 리턴
     */
    public int getPageCount() {
        String response = searchApi();
        int pageCount = 0;
        try {
            responseObj = (JSONObject) parser.parse(response);
            pageCount = ((Long) responseObj.get("pageCount")).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageCount;
    }

    /**
     * 여러 Item들이 담긴 response를 파싱하여 List에 담기
     *
     * @param response
     * @return Item들이 담긴 List 객체
     */
    public void itemsToList(String response) {
        try {
            responseObj = (JSONObject) parser.parse(response);
            jsonArray = (JSONArray) responseObj.get("items");
            for (int i = 0; i < jsonArray.size(); i++) {
                currentObj = (JSONObject) jsonArray.get(i);

                String title = (String) currentObj.get("title");
                String info = (String) currentObj.getOrDefault("introduction", "");
                BigDecimal lat = new BigDecimal(0);
                BigDecimal lng = new BigDecimal(0);
                Double latD;
                Double lngD;
                if (currentObj.get("latitude") != null) {
                    latD = (Double) currentObj.get("latitude");
                    lat = new BigDecimal(latD);
                }
                if (currentObj.get("longitude") != null) {
                    lngD = (Double) currentObj.get("longitude");
                    lng = new BigDecimal(lngD);
                }
                String address = (String) currentObj.getOrDefault("roadaddress", "");

                JSONObject photo1 = null;
                JSONObject photo2 = null;
                String photo = "";
                if (currentObj.get("repPhoto") != null) {
                    photo1 = (JSONObject) currentObj.get("repPhoto");
                    if (photo1.get("photoid") != null) {
                        photo2 = (JSONObject) photo1.get("photoid");
                        if (photo2.get("imgpath") != null) {
                            photo = (String) photo2.get("imgpath");
                        }
                    }
                }
                String phone = (String) currentObj.getOrDefault("phoneno", "");

                item = new Item();
                item.setTitle(title);
                item.setInfo(info);
                item.setLat(lat);
                item.setLng(lng);
                item.setAddress(address);
                item.setPhoto(photo);
                item.setPhone(phone);
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int deleteAllItems() {
        return itemsDao.deleteAllItems();
    }

}
