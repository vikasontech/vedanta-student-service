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
import org.springframework.web.bind.annotation.*;
import org.vedanta.vidiyalay.Utility;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.facade.QueryStudentFacade;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;
import org.vedanta.vidiyalay.utils.BasicResponse;
import org.vedanta.vidiyalay.utils.ResponseUtils;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/query-student/details")
@RestController
public class QueryStudentDetailResource {
    private final QueryStudentFacade queryStudentFacade;

    public QueryStudentDetailResource(QueryStudentFacade queryStudentFacade) {
        this.queryStudentFacade = queryStudentFacade;
    }

    @GetMapping("/students/{enrolmentNo}")
    public ResponseEntity<BasicResponse> getStudentDetails(@PathVariable(name = "enrolmentNo") final Long enrolmentNo) {
        return queryStudentFacade.findByEnrolmentNoAndValidateAdmissionStatus(enrolmentNo)
                .map(e -> ResponseEntity.ok().body(ResponseUtils.getBasicResponse(e, HttpStatus.OK)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentNewAdmissionVM>> getStudentDetails(
            @RequestParam(name = "enrolmentNo", required = false) final Long enrolmentNo,
            @RequestParam(name = "standard", required = false) final Integer standard,
            @RequestParam(name = "year", required = false) final Integer year,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "status", required = false) final AdmissionStatus admissionStatus,
            @RequestParam(name = "fatherName", required = false) final String fatherName,
            @RequestParam(name = "motherName", required = false) final String motherName
    ) {
        return Optional.ofNullable(queryStudentFacade.findStudentData(enrolmentNo
                , standard, year, Utility.emptyValueCheck(name), admissionStatus
                , Utility.emptyValueCheck(fatherName), Utility.emptyValueCheck(motherName)))
                .map(e -> ResponseEntity.ok().body(e))
                .orElse(ResponseEntity.notFound().build());
    }


}
