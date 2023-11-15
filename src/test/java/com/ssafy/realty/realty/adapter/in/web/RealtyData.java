package com.ssafy.realty.realty.adapter.in.web;

import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.wrap.Markers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RealtyData {

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

    protected JSONObject realtyInfoJsonBodyVersion2() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("lat", 33.5076094855731);
        json.put("lng", 126.534698636335);
        json.put("address", "전라남도 함평균 대동면");

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
        walk.put("time", 15);
        transportations.put(walk);

        filter.put("transportations", transportations);

        json.put("filter", filter);

        return json;
    }

    protected static Markers customData() {
        List<String[]> ts = new ArrayList<>();
        ts.add(new String[]{"walk", "5"});
        ts.add(new String[]{"bicycle", "10"});

        Marker marker1 = Marker.init(12.0, 133.0,
                "address", "2017-03-23",
                "2020-04-04",
                10L, 20L,
                10.1, 11.1, ts);
        Marker marker2 = Marker.init(22.0, 233.0,
                "address", "2010-01-01",
                "2050-12-31",
                100L, 20123L,
                11230.1, 111233.1, ts);
        List<Marker> data = new ArrayList<>();
        data.add(marker1);
        data.add(marker2);

        return new Markers(data);
    }

    protected static Markers customData2() {
        List<String[]> ts = new ArrayList<>();
        ts.add(new String[]{"car", "20"});

        Marker marker1 = Marker.init(32.123123, 131.1234235,
                "주소",
                "2020-01-01", "2021-12-31",
                1_000_000L, 3_000_000_000L,
                30.1, 100.9, ts);
        List<Marker> data = new ArrayList<>();
        data.add(marker1);

        return new Markers(data);
    }
}
