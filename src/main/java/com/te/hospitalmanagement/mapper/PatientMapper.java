package com.te.hospitalmanagement.mapper;

import com.te.hospitalmanagement.dto.PatientDto;
import com.te.hospitalmanagement.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper MAPPER = Mappers.getMapper( PatientMapper.class );

    PatientDto toDto(Patient patient);

    Patient toEntity(PatientDto patientDto);
}
