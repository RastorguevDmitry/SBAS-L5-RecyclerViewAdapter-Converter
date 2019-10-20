package com.rdi.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.rdi.converter.Unit.LENGTH_CANTYMETR;
import static com.rdi.converter.Unit.LENGTH_DETMETR;
import static com.rdi.converter.Unit.LENGTH_KILLOMETR;
import static com.rdi.converter.Unit.LENGTH_METR;
import static com.rdi.converter.Unit.LENGTH_MILLTMETR;
import static com.rdi.converter.Unit.SQ_KILOMETRS;
import static com.rdi.converter.Unit.SQ_METR;

public enum Conversion {

    LENGTH("Длина", Arrays.asList(LENGTH_MILLTMETR, LENGTH_CANTYMETR, LENGTH_DETMETR, LENGTH_METR, LENGTH_KILLOMETR)),
    AREA("Площадь", Arrays.asList(SQ_KILOMETRS, SQ_METR)),
    POWER("Мощность", Arrays.asList(LENGTH_CANTYMETR, LENGTH_METR, LENGTH_KILLOMETR)),
    PRESSURE("Давление", Arrays.asList(LENGTH_CANTYMETR, LENGTH_METR, LENGTH_KILLOMETR)),
    ANOTHER_ONE("Масса", Arrays.asList(LENGTH_CANTYMETR, LENGTH_METR, LENGTH_KILLOMETR)),
    ANOTHER_TWO("Время", Arrays.asList(LENGTH_CANTYMETR, LENGTH_METR, LENGTH_KILLOMETR)),
    ANOTHER_THREE("Температура", Arrays.asList(LENGTH_CANTYMETR, LENGTH_METR, LENGTH_KILLOMETR)),
    ANOTHER_FOUR("Сила тока", Arrays.asList(LENGTH_CANTYMETR, LENGTH_METR, LENGTH_KILLOMETR)),
    ANOTHER_FIVE("Сила света", Arrays.asList(LENGTH_CANTYMETR, LENGTH_METR, LENGTH_KILLOMETR)),
    VELOCITY("Скорость", Arrays.asList(LENGTH_CANTYMETR, LENGTH_METR, LENGTH_KILLOMETR));


    private String lable;
    private List<Unit> unitList;

    Conversion(String lable, List<Unit> unitList) {
        this.lable = lable;
        this.unitList = unitList;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public List<String> getLableUnitList() {
        List<String> lableUnitList = new ArrayList<>();
        for (Unit unit :
                unitList) {
            lableUnitList.add(unit.getLable());
        }
        return lableUnitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }
}
