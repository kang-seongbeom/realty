package com.ssafy.realty.realty.application.port.out.command;

import com.ssafy.realty.realty.domain.Save;
import com.ssafy.realty.realty.domain.SaveTemporary;
import com.ssafy.realty.realty.domain.wrap.Markers;

public interface SaveRealtyPort {

    void save(Save save);

    void saveTemporary(SaveTemporary saveTemporary);
}
