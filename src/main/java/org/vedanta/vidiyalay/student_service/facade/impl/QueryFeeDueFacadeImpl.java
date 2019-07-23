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

import org.springframework.stereotype.Component;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.facade.QueryFeeDueFacade;
import org.vedanta.vidiyalay.student_service.service.QueryFeeDueService;
import org.vedanta.vidiyalay.student_service.web.rest.vm.DueFeesDetailsVM;

import java.math.BigDecimal;
import java.util.List;

@Component
public class QueryFeeDueFacadeImpl implements QueryFeeDueFacade {
    private final QueryFeeDueService queryFeeDueService;

    public QueryFeeDueFacadeImpl(QueryFeeDueService queryFeeDueService) {
        this.queryFeeDueService = queryFeeDueService;
    }

    @Override
    public List<DueFeesDetailsVM> findDueFee(Long enrolmentNo, Integer standard, String name, AdmissionStatus admissionStatus,
                                             BigDecimal amount, String fatherName) {
        return queryFeeDueService.findDueFee(enrolmentNo, standard, name, admissionStatus,
                amount, fatherName);
    }
}
