/*
 *     Copyright (C) 2019  Vikas Kumar Verma
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.vedanta.vidiyalay.service.impl;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.Assert;
import org.vedanta.vidiyalay.student_service.account_service.AccountServiceHelper;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.repository.QueryFeeDueRepository;
import org.vedanta.vidiyalay.student_service.repository.StudentNewAdmissionEntityRepository;
import org.vedanta.vidiyalay.student_service.service.impl.QueryFeeDueServiceImpl;
import org.vedanta.vidiyalay.student_service.web.rest.vm.AccountMasterVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.DueFeesDetailsVM;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class QueryFeeDueServiceImplTest {


  @Mock
  private QueryFeeDueRepository queryFeeDueRepository;

  @InjectMocks
  private QueryFeeDueServiceImpl queryFeeDueService;

  @Mock
  private StudentNewAdmissionEntityRepository studentNewAdmissionEntityRepository;
  @Mock
  private AccountServiceHelper accountServiceHelper;

  @Test
  public void findDueFee() {
    final Long enrolmentNo = 1L;
    final Integer standard = 1;
    final String name = "dummy";
    final AdmissionStatus admissionStatus = AdmissionStatus.APPROVED;
    final BigDecimal amount = BigDecimal.ONE;
    final String fatherName = "dummy father";

    final List<StudentNewAdmissionEntity> studentNewAdmissionEntities =
        EnhancedRandom.randomListOf(10, StudentNewAdmissionEntity.class);

    when(studentNewAdmissionEntityRepository.findStudentDetailsByQueryFindByEnrolmentNo(enrolmentNo,
        standard, null, name, admissionStatus, fatherName, null))
        .thenReturn(studentNewAdmissionEntities);

    when(accountServiceHelper.findAllThatHaveFeeDue())
        .thenReturn(EnhancedRandom.randomListOf(10, AccountMasterVM.class));

    final List<DueFeesDetailsVM> result = queryFeeDueService.findDueFee(enrolmentNo, standard, name, admissionStatus, amount, fatherName);

    Assert.notNull(result, "result is null");
  }
}
