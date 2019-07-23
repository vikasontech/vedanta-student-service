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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vedanta.vidiyalay.student_service.Endpoints;
import org.vedanta.vidiyalay.student_service.facade.UpdateStudentDetailsFacade;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.TerminateStudentVM;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping(Endpoints.terminateStudentResource)
@RestController
class TerminateStudentResource {

    private final UpdateStudentDetailsFacade updateStudentDetailsFacade;

    TerminateStudentResource(UpdateStudentDetailsFacade updateStudentDetailsFacade) {
        this.updateStudentDetailsFacade = updateStudentDetailsFacade;
    }

    @PostMapping
    public ResponseEntity<StudentNewAdmissionVM> terminate(
            @RequestBody @Valid TerminateStudentVM terminateStudentVM
    ) {

        return Optional.ofNullable(updateStudentDetailsFacade.terminate(terminateStudentVM))
                .map(e -> ResponseEntity.ok().body(e))
                .orElse(ResponseEntity.notFound().build());
    }
}

