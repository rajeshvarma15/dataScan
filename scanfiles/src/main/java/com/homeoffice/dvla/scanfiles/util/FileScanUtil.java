package com.homeoffice.dvla.scanfiles.util;

import com.homeoffice.dvla.scanfiles.pojo.VehicleDetail;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileScanUtil {

    public static List<File> listFiles (String directoryName, boolean recursive) {

        List<File> files = new ArrayList<File>();
        File directory = new File(directoryName);
        File[] filesList = directory.listFiles();

        for (File file : filesList) {
            if (file.isFile()) {
                files.add(file);

                printFileInfo(file);
            }
            else if(recursive && file.isDirectory()){
                files.addAll(listFiles(directoryName, true));
            }
        }
        System.out.println("No. of files :"+ files.size());
        return files;
    }

    private static void printFileInfo(File file) {

        String fullFileName = file.getName();
        String extension = FilenameUtils.getExtension(file.getName());
        String mimeStr = new MimetypesFileTypeMap().getContentType(file);
        long length = file.length()/1000; // Convert into KB from Bytes

        System.out.print("File Name : " + file.getName());
        System.out.println("Extension: "+extension);
        System.out.println("MimeType is : "  + mimeStr);
        System.out.println("File size in KB :"+ length);
    }

}

