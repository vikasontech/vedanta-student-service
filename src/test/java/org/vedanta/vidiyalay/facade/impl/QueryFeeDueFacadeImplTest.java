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

package org.vedanta.vidiyalay.facade.impl;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.facade.impl.QueryFeeDueFacadeImpl;
import org.vedanta.vidiyalay.student_service.service.QueryFeeDueService;
import org.vedanta.vidiyalay.student_service.web.rest.vm.DueFeesDetailsVM;

import java.math.BigDecimal;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class QueryFeeDueFacadeImplTest {

    @Mock
    private QueryFeeDueService queryFeeDueService;

    @InjectMocks
    private QueryFeeDueFacadeImpl queryFeeDueFacade;

    @Test
    public void findDueFee() {
        final Long enrolmentNo = 1L;
        final Integer standard = 1;
        final String name = "temp";
        final AdmissionStatus admissionStatus = AdmissionStatus.APPROVED;
        final BigDecimal amount = BigDecimal.ONE;
        final String fatherName = "temp father";

        List<DueFeesDetailsVM> expected = EnhancedRandom.randomListOf(10, DueFeesDetailsVM.class);

        Mockito.when(queryFeeDueService.findDueFee(enrolmentNo, standard, name, admissionStatus,amount, fatherName))
                .thenReturn(expected);

      queryFeeDueService.findDueFee(enrolmentNo, standard, name, admissionStatus,
                amount, fatherName);

        Mockito.verify(queryFeeDueService).findDueFee(enrolmentNo, standard, name, admissionStatus,amount, fatherName);

    }
}