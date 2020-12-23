package com.te.hospitalmanagement.resource;

import com.te.hospitalmanagement.dto.PatientDto;
import com.te.hospitalmanagement.service.PatientService;
import com.te.hospitalmanagement.service.serviceImpl.PatientServiceImpl;
import com.te.hospitalmanagement.util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addpatientssafe")
public class AddPatientsSafeResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> patients = Util.decodeQueryString(request.getQueryString());

        List<PatientDto> patientDtos = new ArrayList<>();

        AtomicReference<String> name = new AtomicReference<>();
        AtomicReference<String> email = new AtomicReference<>();

        patients.forEach((k, v) -> {
                if(k=="name") {
                    name.set(Util.sanitizeUntrustedHTML(v));
                }
                if(k=="email") {
                    email.set(Util.sanitizeUntrustedHTML(v));
                }
            });

        patientDtos.add(new PatientDto(name.toString(), email.toString()));
        patientDtos.add(new PatientDto("mary", "mary@gmail.com"));

        PatientService patientService = new PatientServiceImpl();

        patientService.saveAllPatient(patientDtos);

        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.write(patientService.getAllPatientsToString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
