package com.te.hospitalmanagement.resource;

import com.te.hospitalmanagement.dto.PatientDto;
import com.te.hospitalmanagement.service.PatientService;
import com.te.hospitalmanagement.service.serviceImpl.PatientServiceImpl;
import com.te.hospitalmanagement.util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addpatientjsongetpatientsunsafe")
public class AddPatientJsonGetPatientsResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String,String> patientsMap = Util.getHashMapFromJsonRequest(request);

        AtomicReference<String> name = new AtomicReference<>("");
        AtomicReference<String> email = new AtomicReference<>("");

        patientsMap.entrySet().forEach(patient -> {
            if(patient.getKey() == "name") {
                name.set(patient.getValue());
            }
            if(patient.getKey() == "email") {
                email.set(patient.getValue());
            }
        });

        List<PatientDto> patients = new ArrayList<>();
        patients.add(new PatientDto("mary", "mary@gmail.com"));
        patients.add(new PatientDto("ella", "elle@gmail.com"));
        patients.add(new PatientDto(name.toString(), email.toString()));
        patients.add(new PatientDto("jackson", "jackson@gmail.com"));

        PatientService patientService = new PatientServiceImpl();

        patientService.saveAllPatient(patients);

        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.printf(patientService.getAllPatientsToString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
