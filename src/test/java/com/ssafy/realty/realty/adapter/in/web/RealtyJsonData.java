package com.ssafy.realty.realty.adapter.in.web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RealtyJsonData {

    protected JSONObject realtyInfoJsonBody() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("lat", 35.9768231457432);
        json.put("lng", 128.944137387794);
        json.put("address", "경상북도 영천시 야사동");

        JSONObject filter = new JSONObject();

        JSONObject day = new JSONObject();
        day.put("lower", "2015-07-15");
        day.put("upper", "2020-12-31");
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
