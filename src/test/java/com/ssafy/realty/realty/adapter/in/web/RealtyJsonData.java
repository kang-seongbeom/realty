package com.ssafy.realty.realty.adapter.in.web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RealtyJsonData {

    protected JSONObject realtyInfoJsonBody() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("lat", 36.0019);
        json.put("lng", 127.6686);
        json.put("address", "전라북도 무주군 무주읍 당산리");

        JSONObject filter = new JSONObject();

        JSONObject day = new JSONObject();
        day.put("lower", "2015.7.15");
        day.put("upper", "2020.12.31");
        filter.put("date", day);

        JSONObject dealAmount = new JSONObject();
        dealAmount.put("lower", 100000000);
        dealAmount.put("upper", 1000000000);
        filter.put("dealAmount", dealAmount);

        JSONObject area = new JSONObject();
        area.put("lower", 10.0);
        area.put("upper", 200.0);
        filter.put("area", area);

        JSONArray transportations = new JSONArray();

        JSONObject walk = new JSONObject();
        walk.put("type", "walk");
        walk.put("time", 5);
        transportations.put(walk);

        JSONObject anotherWalk = new JSONObject();
        anotherWalk.put("type", "walk");
        anotherWalk.put("time", 10);
        transportations.put(anotherWalk);

        JSONObject bicycle = new JSONObject();
        bicycle.put("type", "bicycle");
        bicycle.put("time", 5);
        transportations.put(bicycle);

        filter.put("transportations", transportations);

        json.put("filter", filter);

        return json;
    }
}
