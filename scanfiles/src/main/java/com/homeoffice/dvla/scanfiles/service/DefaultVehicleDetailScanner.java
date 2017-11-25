package com.homeoffice.dvla.scanfiles.service;

import com.homeoffice.dvla.scanfiles.pojo.VehicleDetail;
import com.homeoffice.dvla.scanfiles.util.FileScanUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Component
public class DefaultVehicleDetailScanner implements VehicleDetailScanner {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public List<VehicleDetail> getVehicleDetails(String dirPath) {
        List<VehicleDetail> vehicles = new ArrayList<VehicleDetail>();

        List<File> files = FileScanUtil.listFiles(dirPath, true);
        for(File file : files){
            vehicles.addAll(getVehicleDetails(file));
        }
        logger.info(vehicles.toString());

        return vehicles;
    }

    public List<VehicleDetail> getVehicleDetails(File file){

        String extension = FilenameUtils.getExtension(file.getName());
        String mimeStr = new MimetypesFileTypeMap().getContentType(file);
        if(mimeStr.equals("application/octet-stream") &&
                (extension.equals("xlsx") || extension.equals("csv"))) {

            List<VehicleDetail> vehicles = new ArrayList<VehicleDetail>();
            try {

                FileInputStream excelFile = new FileInputStream(file);
                Workbook workbook = new XSSFWorkbook(excelFile);
                Sheet datatypeSheet = workbook.getSheetAt(0);

                for (int i = datatypeSheet.getFirstRowNum() + 1; i <= datatypeSheet.getLastRowNum(); i++) {

                    Row currentRow = datatypeSheet.getRow(i);
                    VehicleDetail vehicle = new VehicleDetail();
                    vehicle.setVehicleRegNum(currentRow.getCell(0).getStringCellValue());
                    vehicle.setVehicleMake(currentRow.getCell(1).getStringCellValue());
                    vehicle.setVehicleColor(currentRow.getCell(2).getStringCellValue());

                    vehicles.add(vehicle);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info(vehicles.toString());

            return vehicles;
        }
        else {
            return Collections.EMPTY_LIST; // Not valid file format and mime type.
        }
    }
}
