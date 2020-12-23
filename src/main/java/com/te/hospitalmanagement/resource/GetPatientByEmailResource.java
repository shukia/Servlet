package com.te.hospitalmanagement.resource;


import com.te.hospitalmanagement.dto.PatientDto;
import com.te.hospitalmanagement.service.PatientService;
import com.te.hospitalmanagement.service.serviceImpl.PatientServiceImpl;
import org.apache.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getpatientbyemail")
public class GetPatientByEmailResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        PatientService patientService = new PatientServiceImpl();
        PatientDto patientDto = patientService.getPatientByEmail(email);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write(patientDto.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
