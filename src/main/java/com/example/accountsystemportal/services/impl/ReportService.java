package com.example.accountsystemportal.services.impl;

import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.repositories.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private UserRepository userRepository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
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
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path +"//users.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path + "//users.pdf");
        }

        return "report generated in path :" + path;
    }
}
