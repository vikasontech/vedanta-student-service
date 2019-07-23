package org.vedanta.vidiyalay.student_service.service;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.domain.enums.Gender;
import org.vedanta.vidiyalay.student_service.mapper.StudentAdmissionDetailMapper;
import org.vedanta.vidiyalay.student_service.repository.StudentNewAdmissionEntityRepository;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class StudentNewAdmissionServiceImplTest {
  @Mock
  StudentAdmissionDetailMapper mapper;
  @Mock
  StudentNewAdmissionEntityRepository studentNewAdmissionEntityRepository;
  @InjectMocks
  StudentNewAdmissionServiceImpl studentNewAdmissionService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void newAdmission() {

    final StudentNewAdmissionEntity expected = EnhancedRandom.random(StudentNewAdmissionEntity.class);

    when(studentNewAdmissionEntityRepository.save(expected))
        .thenReturn(expected);

    //when
    final StudentNewAdmissionEntity actual = studentNewAdmissionService.newAdmission(expected);

    Assert.assertNotNull(actual);
    Assert.assertTrue(actual.equals(expected));
  }

  @Test
  public void updateStudentDetails() {
    final StudentNewAdmissionEntity expected = EnhancedRandom.random(StudentNewAdmissionEntity.class);

    when(studentNewAdmissionEntityRepository.save(expected))
        .thenReturn(expected);

    //when
    final StudentNewAdmissionEntity actual = studentNewAdmissionService.updateStudentDetails(expected);

    Assert.assertNotNull(actual);
    Assert.assertTrue(actual.equals(expected));

  }
}

