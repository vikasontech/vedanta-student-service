package org.vedanta.vidiyalay.facade.impl;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.facade.impl.QueryStudentFacadeImpl;
import org.vedanta.vidiyalay.student_service.service.QueryStudentService;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class QueryStudentFacadeImplTest {

    @Mock
    private QueryStudentService queryStudentService;

    @InjectMocks
    private QueryStudentFacadeImpl queryStudentFacadeImpl;

    @Test
    public void findByEnrolmentNoAndValidateAdmissionStatus() {
        final Long enrolmentNo = 1L;
        final Optional<StudentNewAdmissionVM> expected = Optional.of(EnhancedRandom.random(StudentNewAdmissionVM.class));

        Mockito.when(queryStudentService.findByEnrolmentNoAndValidateAdmissionStatus(enrolmentNo))
                .thenReturn(expected);

        final Optional<StudentNewAdmissionVM> actual
                = queryStudentFacadeImpl.findByEnrolmentNoAndValidateAdmissionStatus(enrolmentNo);

        Assertions.assertThat(actual).isEqualTo(expected);

        Mockito.verify(queryStudentService).findByEnrolmentNoAndValidateAdmissionStatus(enrolmentNo);
    }

    @Test
    public void findStudentData() {

        final List<StudentNewAdmissionVM> expected
                = EnhancedRandom.randomListOf(10, StudentNewAdmissionVM.class);
        final Long enrolmentNo = 1L;
        final Integer standard = 1;
        final Integer year=2019;
        final String name="temp";
        final AdmissionStatus admissionStatus=AdmissionStatus.APPROVED;
        final String fatherName="temp father";
        final String motherName="temp mother";

        Mockito.when(queryStudentService.findStudentData(
                enrolmentNo, standard, year, name, admissionStatus, fatherName, motherName))
                .thenReturn(expected);

        queryStudentFacadeImpl.findStudentData(
                enrolmentNo, standard, year, name, admissionStatus, fatherName, motherName);

        Mockito.verify(queryStudentService).findStudentData(
                enrolmentNo, standard, year, name, admissionStatus, fatherName, motherName);
    }
}