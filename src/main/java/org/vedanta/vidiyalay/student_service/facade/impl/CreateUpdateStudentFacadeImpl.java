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

package org.vedanta.vidiyalay.student_service.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.vedanta.vidiyalay.student_service.facade.CreateUpdateStudentFacade;
import org.vedanta.vidiyalay.student_service.service.CreateUpdateStudentService;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

@Slf4j
@Component
public class CreateUpdateStudentFacadeImpl implements CreateUpdateStudentFacade {
    private final CreateUpdateStudentService  createUpdateStudentService;

    public CreateUpdateStudentFacadeImpl(CreateUpdateStudentService createUpdateStudentService) {
        this.createUpdateStudentService = createUpdateStudentService;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public StudentNewAdmissionVM newAdmission(final StudentNewAdmissionVM studentNewAdmissionVM) {
        return createUpdateStudentService.newAdmission(studentNewAdmissionVM);
    }
}
