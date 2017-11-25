package com.homeoffice.dvla.scanfiles.service;

import com.homeoffice.dvla.scanfiles.pojo.VehicleDetail;

import java.io.File;
import java.util.List;

public interface VehicleDetailScanner {
    public List<VehicleDetail> getVehicleDetails(String dirPath);

    public List<VehicleDetail> getVehicleDetails(File file);
}
