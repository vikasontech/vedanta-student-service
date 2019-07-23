package org.vedanta.vidiyalay.service.impl;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.exception.StudentNotFoundException;
import org.vedanta.vidiyalay.student_service.exception.StudentWithdrawalException;
import org.vedanta.vidiyalay.student_service.mapper.StudentAdmissionDetailMapper;
import org.vedanta.vidiyalay.student_service.mapper.StudentAdmissionDetailMapperImpl;
import org.vedanta.vidiyalay.student_service.service.StudentNewAdmissionService;
import org.vedanta.vidiyalay.student_service.service.impl.QueryStudentServiceImpl;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class QueryStudentServiceImplTest {

    @Mock
    private StudentNewAdmissionService studentService;

    @Mock
    private StudentAdmissionDetailMapper mapper;

    @InjectMocks
    private QueryStudentServiceImpl queryStudentFacade;

    @Test(expected = StudentNotFoundException.class)
    public void findByEnrolmentNoAndValidateAdmissionStatus_student_not_found() {

        final Long enrolmentNo = 1L;
        //mock
        Mockito.when(studentService.findByEnrolmentNo(enrolmentNo)).thenReturn(Optional.empty());

        //then
        queryStudentFacade.findByEnrolmentNoAndValidateAdmissionStatus(enrolmentNo);

    }


    @Test(expected = StudentWithdrawalException.class)
    public void findByEnrolmentNoAndValidateAdmissionStatus_student_quit() {

        final Long enrolmentNo = 1L;
        StudentNewAdmissionEntity mockStudentData = EnhancedRandom.random(StudentNewAdmissionEntity.class);
        mockStudentData.setAdmissionStatus(AdmissionStatus.TERMINATED);
        //mock
        Mockito.when(studentService.findByEnrolmentNo(enrolmentNo)).thenReturn(Optional.of(mockStudentData));

        //then
        queryStudentFacade.findByEnrolmentNoAndValidateAdmissionStatus(enrolmentNo);

    }

    @Test
    public void findByEnrolmentNoAndValidateAdmissionStatus_approved_student() {

        final Long enrolmentNo = 1L;
        final StudentNewAdmissionEntity mockStudentData = EnhancedRandom.random(StudentNewAdmissionEntity.class);
        mockStudentData.setAdmissionStatus(AdmissionStatus.APPROVED);
        //mock
        Mockito.when(studentService.findByEnrolmentNo(enrolmentNo)).thenReturn(Optional.of(mockStudentData));

        Mockito.when(mapper.toVm(mockStudentData))
                .thenAnswer(invocation -> new StudentAdmissionDetailMapperImpl().toVm(invocation.getArgument(0)));

        //then
        final StudentNewAdmissionVM result = queryStudentFacade.findByEnrolmentNoAndValidateAdmissionStatus(enrolmentNo)
                .orElse(null);

        //verify
        Assertions.assertThat(result).isNotNull().isEqualTo(mapper.toVm(mockStudentData));
    }

    @Test
    public void findStudentData() {
        final Long enrolmentNo=1L;
        final Integer standard=1;
        final Integer year=2019;
        final String name="dummy";
        final AdmissionStatus admissionStatus= AdmissionStatus.APPROVED;
        final String fatherName="dummy Father";
        final String motherName="dummy Mother";

        //mock
        Mockito.when(studentService.findByParameters(enrolmentNo
                ,standard, year, name, admissionStatus, fatherName, motherName))
                .thenReturn(EnhancedRandom.randomListOf(2, StudentNewAdmissionVM.class));

        //when
        queryStudentFacade.findStudentData(enrolmentNo
                , standard, year, name, admissionStatus, fatherName, motherName);

        //verify
        Mockito.verify(studentService).findByParameters(enrolmentNo
                ,standard, year, name, admissionStatus, fatherName, motherName);
    }
}