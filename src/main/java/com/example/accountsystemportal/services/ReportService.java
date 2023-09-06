package com.example.accountsystemportal.services;

import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface ReportService {

    public byte[] encryptPdf(byte[] pdfBytes, String password) throws IOException;

    public byte[] generateUserReport() throws IOException, JRException;
}
