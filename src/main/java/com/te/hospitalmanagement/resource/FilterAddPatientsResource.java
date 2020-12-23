package com.te.hospitalmanagement.resource;

import com.te.hospitalmanagement.dto.PatientDto;
import com.te.hospitalmanagement.service.PatientService;
import com.te.hospitalmanagement.service.serviceImpl.PatientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/filteraddpatients")
public class FilterAddPatientsResource extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PatientService patientService = new PatientServiceImpl();
        List<PatientDto> patients = patientService.getAllPatients();

        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            for(PatientDto patientDto: patients) {
                out.printf(patientDto.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
