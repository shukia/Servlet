package com.te.hospitalmanagement.filter;

import com.te.hospitalmanagement.database.PatientList;
import com.te.hospitalmanagement.dto.PatientDto;
import com.te.hospitalmanagement.mapper.PatientMapper;
import com.te.hospitalmanagement.service.PatientService;
import com.te.hospitalmanagement.service.serviceImpl.PatientServiceImpl;
import com.te.hospitalmanagement.util.Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/filteraddpatientssafe"})
public class AddPatientsSafeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String name = Util.sanitizeUntrustedHTML(request.getParameter("name"));
        String email = Util.isValidEmailAddress(request.getParameter("email")) ? request.getParameter("email"):"";

        PatientService patientService = new PatientServiceImpl();


        patientService.savePatient(new PatientDto("mary", "mary@gmail.com"));
        patientService.savePatient(new PatientDto("ted", "ted@gmail.com"));

        patientService.savePatient(new PatientDto(name, email));

        PatientList.getPatients().add(PatientMapper.MAPPER.toEntity(new PatientDto("sarah", "sarah@gmail.com")));
        PatientList.getPatients().add(PatientMapper.MAPPER.toEntity(new PatientDto("bill", "bill@gmail.com")));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}