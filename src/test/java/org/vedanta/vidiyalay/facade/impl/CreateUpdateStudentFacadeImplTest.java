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
import org.vedanta.vidiyalay.student_service.facade.impl.CreateUpdateStudentFacadeImpl;
import org.vedanta.vidiyalay.student_service.service.CreateUpdateStudentService;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

@RunWith(MockitoJUnitRunner.class)
public class CreateUpdateStudentFacadeImplTest {


    @Mock
    private CreateUpdateStudentService createUpdateStudentService;

    @InjectMocks
    private CreateUpdateStudentFacadeImpl createUpdateStudentFacade;

    @Test
    public void newAdmission() {
        final StudentNewAdmissionVM studentNewAdmissionVM =
                EnhancedRandom.random(StudentNewAdmissionVM.class);

        Mockito.when(createUpdateStudentService.newAdmission(studentNewAdmissionVM))
                .thenReturn(studentNewAdmissionVM);

        createUpdateStudentFacade.newAdmission(studentNewAdmissionVM);

        Mockito.verify(createUpdateStudentService).newAdmission(studentNewAdmissionVM);
    }
}