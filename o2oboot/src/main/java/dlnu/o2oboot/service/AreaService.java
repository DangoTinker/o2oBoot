package dlnu.o2oboot.service;

import dlnu.o2oboot.entity.Area;

import java.util.List;

public interface AreaService {
    public static final String AREALISTKEY="arealist";

    public List<Area> getAreaList();
}
