package com.example.accountsystemportal.services.impl;

import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.repositories.UserRepository;
import com.example.accountsystemportal.services.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImplementation implements ReportService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public byte[] encryptPdf(byte[] pdfBytes, String password) throws IOException {
        PDDocument document = PDDocument.load(pdfBytes);
        AccessPermission accessPermission = new AccessPermission();

        StandardProtectionPolicy standardProtectionPolicy = new StandardProtectionPolicy(password, password, accessPermission);
        standardProtectionPolicy.setEncryptionKeyLength(128);

        document.protect(standardProtectionPolicy);

        ByteArrayOutputStream encryptedPdfStream = new ByteArrayOutputStream();
        document.save(encryptedPdfStream);
        document.close();

        return encryptedPdfStream.toByteArray();
    }

    @Override
    public byte[] generateUserReport() throws IOException, JRException {
        List<Map<String, Object>> combinedData = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users){
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", user.getId());
            dataMap.put("fname", user.getFname());
            dataMap.put("national_id",user.getNational_id());
            dataMap.put("balance", user.getBalance());
            combinedData.add(dataMap);
        }
        ClassPathResource jrxmlResource = new ClassPathResource("reports/users.jrxml");
        InputStream jrxmlInputStream = jrxmlResource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlInputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(combinedData);
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("createdBy", "HR Manager");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    }

