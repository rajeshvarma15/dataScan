package com.homeoffice.dvla.scanfiles.pojo;

public class VehicleDetail {

    public String getVehicleRegNum() {
        return vehicleRegNum;
    }

    public void setVehicleRegNum(String vehicleRegNum) {
        this.vehicleRegNum = vehicleRegNum;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    @Override
    public String toString() {
        return "Vehicle Details-RegNum:" + getVehicleRegNum() + ", Make:" + getVehicleMake() + ", Color:" + getVehicleColor();
    }

    private String vehicleRegNum;
    private String vehicleMake;
    private String vehicleColor;
}
