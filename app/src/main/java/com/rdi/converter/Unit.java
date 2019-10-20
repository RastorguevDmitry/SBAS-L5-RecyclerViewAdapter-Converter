package com.rdi.converter;

public enum Unit {

    LENGTH_MILLTMETR("Милимер", 1, 1),
    LENGTH_CANTYMETR("Сантиметр", 10, 0.1),
    LENGTH_DETMETR("Дециметр", 100, 0.01),
    LENGTH_METR("Метр", 1000, 0.001),
    LENGTH_KILLOMETR("Километр", 1000000, 0.000001),


    SQ_KILOMETRS("Кв. километр", 100000, 0.00001),
    SQ_METR("Кв. метр", 100, 0.001);

    private String lable;
    private int convertToBasic;
    private double convertFromBasic;

    Unit(String lable, int convertToBasic, double convertFromBasic) {
        this.lable = lable;
        this.convertToBasic = convertToBasic;
        this.convertFromBasic = convertFromBasic;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public int getConvertToBasic() {
        return convertToBasic;
    }

    public void setConvertToBasic(int convertToBasic) {
        this.convertToBasic = convertToBasic;
    }

    public double getConvertFromBasic() {
        return convertFromBasic;
    }

    public void setConvertFromBasic(double convertFromBasic) {
        this.convertFromBasic = convertFromBasic;
    }
}
