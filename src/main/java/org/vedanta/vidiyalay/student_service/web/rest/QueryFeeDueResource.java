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

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vedanta.vidiyalay.Utility;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.facade.QueryFeeDueFacade;
import org.vedanta.vidiyalay.student_service.web.rest.vm.DueFeesDetailsVM;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/fee_due")
@RestController
public class QueryFeeDueResource {

    private final QueryFeeDueFacade queryFeeDueFacade;

    public QueryFeeDueResource(QueryFeeDueFacade queryFeeDueFacade) {
        this.queryFeeDueFacade = queryFeeDueFacade;
    }


    @GetMapping("/details")
    public ResponseEntity<List<DueFeesDetailsVM>> getDueFeeDetails(
            @RequestParam(name = "enrolmentNo", required = false) final Long enrolmentNo,
            @RequestParam(name = "standard", required = false) final Integer standard,
            @RequestParam(name="name", required = false) final String name,
            @RequestParam(name = "status", required = false) final AdmissionStatus admissionStatus,
            @RequestParam(name = "amount", required = false) final BigDecimal amount,
            @RequestParam(name = "fatherName", required = false) final String fatherName
    ) {
        return Optional.ofNullable(queryFeeDueFacade.findDueFee(enrolmentNo, standard, Utility.emptyValueCheck(name),
                admissionStatus, amount, Utility.emptyValueCheck(fatherName)))
                .map(e -> ResponseEntity.ok().body(e))
                .orElse(ResponseEntity.notFound().build());
    }


    private String sanitizeString(String stringParam) {
        return Optional.ofNullable(stringParam)
                .orElse(Strings.EMPTY);
    }

    private String sanitizeInteger(Integer intParam) {
        return Optional.ofNullable(intParam)
                .map(String::valueOf)
                .orElse(Strings.EMPTY);
    }

    private String sanitizeLong(Long longParam) {
        return Optional.ofNullable(longParam)
                .map(String::valueOf)
                .orElse(Strings.EMPTY);
    }
}

