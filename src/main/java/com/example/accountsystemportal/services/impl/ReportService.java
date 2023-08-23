package com.example.accountsystemportal.services.impl;

import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.repositories.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private UserRepository userRepository;

    public String exportReport(String reportFormat) throws IOException, JRException {
        String path = "/home/muriithi/JaspersoftWorkspace/MyReports";
        List<User> users = userRepository.findAll();
        //Load users.jrxml file and compile
        File file = ResourceUtils.getFile("classpath:users.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("createdBy","Admin");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,dataSource);
        
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path + "//users.html");
            return "HTML Report generated in path :" + path;
        }
        else if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path + "//users.pdf");
            File pdfFile = new File(path+"//users.pdf");
            PDDocument pdd = PDDocument.load(pdfFile);
            AccessPermission accessPermission = new AccessPermission();
            StandardProtectionPolicy standardProtectionPolicy = new StandardProtectionPolicy("owner_pass","user_pass",accessPermission);
            standardProtectionPolicy.setEncryptionKeyLength(128);
            standardProtectionPolicy.setPermissions(accessPermission);
            pdd.protect(standardProtectionPolicy);
            pdd.save(path+"//users.pdf");
            pdd.close();

            return "PDF PDF Generated and Encrypted in path :" + path;
        }else {
            return "Format is unrecognized. Change to PDF or HTML";
        }
    }
}
