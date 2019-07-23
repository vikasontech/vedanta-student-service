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

package org.vedanta.vidiyalay.student_service.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vedanta.vidiyalay.student_service.Endpoints;
import org.vedanta.vidiyalay.student_service.facade.CreateUpdateStudentFacade;
import org.vedanta.vidiyalay.student_service.facade.UpdateStudentDetailsFacade;
import org.vedanta.vidiyalay.student_service.web.rest.vm.AdmissionStatusVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;
import org.vedanta.vidiyalay.utils.BasicResponse;
import org.vedanta.vidiyalay.utils.ResponseUtils;

import javax.validation.Valid;
import java.util.Optional;


@RequestMapping(Endpoints.studentResource)
@RestController
public class StudentNewAdmissionResource {

    private final CreateUpdateStudentFacade createUpdateStudentFacade;
    private final UpdateStudentDetailsFacade updateStudentDetailsFacade;

    public StudentNewAdmissionResource(CreateUpdateStudentFacade createUpdateStudentFacade, UpdateStudentDetailsFacade updateStudentDetailsFacade) {
        this.createUpdateStudentFacade = createUpdateStudentFacade;
        this.updateStudentDetailsFacade = updateStudentDetailsFacade;
    }

    @PostMapping(Endpoints.newAdmissionPost)
    public ResponseEntity<StudentNewAdmissionVM> newAdmission(@Valid @RequestBody StudentNewAdmissionVM studentNewAdmissionVM) {

        return Optional.ofNullable(createUpdateStudentFacade.newAdmission(studentNewAdmissionVM))
                .map(e -> ResponseEntity.ok().body(e))
                .orElseThrow(() -> new RuntimeException("student not admitted"));

    }

    @PostMapping(Endpoints.updateStudentPost)
    public ResponseEntity<BasicResponse> updateAdmissionStatus(@RequestBody @Valid AdmissionStatusVM admissionStatusVM) {

        return Optional.ofNullable(ResponseUtils.getBasicResponse(updateStudentDetailsFacade.updateAdmissionStatus(admissionStatusVM),
                HttpStatus.OK))
                .map(e -> ResponseEntity.ok().body(e))
                .orElseThrow(() -> new RuntimeException("student not admitted"));
    }


}


