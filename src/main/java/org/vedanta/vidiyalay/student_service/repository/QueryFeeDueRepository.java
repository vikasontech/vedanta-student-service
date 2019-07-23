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

package org.vedanta.vidiyalay.student_service.repository;


import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.web.rest.vm.DueFeesDetailsVM;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class QueryFeeDueRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public QueryFeeDueRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DueFeesDetailsVM> findDueFee(Long enrolmentNo, Integer standard, String name,
                                             AdmissionStatus admissionStatus, BigDecimal amount, String fatherName) {

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("due_amount", amount)
                        .addValue("name", name)
                        .addValue("enrolment_no", enrolmentNo)
                        .addValue("admission_class", standard)
                        .addValue("admission_status", getAdmissionStatus(admissionStatus))
                        .addValue("father_name", fatherName);

        return jdbcTemplate.query(SQL_FIND_USER_DATA, mapSqlParameterSource, (r, i) -> DueFeesDetailsVM.builder()
                .amount(r.getBigDecimal("due_amount"))
                .email(r.getString("email"))
                .fatherName(r.getString("father_name"))
                .admissionStatus(AdmissionStatus.valueOf(r.getString("admission_status")))
                .name(r.getString("name"))
                .mobile(r.getString("mobile"))
                .phone(r.getString("phone1"))
                .standard(r.getInt("admission_class"))
                .totalFine(r.getBigDecimal("total_fine"))
                .id(r.getLong("id"))
                .build());
    }

    private static String getAdmissionStatus(AdmissionStatus admissionStatus) {
        return ObjectUtils.isEmpty(admissionStatus) ? null : admissionStatus.name();
    }



    private static final String SQL_FIND_USER_DATA = "SELECT sd.id, acc.due_amount, acc.total_fine," +
            "sd.admission_class, sd.name, sd.father_name," +
            " sd.phone1, sd.mobile, sd.email, sd.admission_status\n" +
            "FROM ACCOUNT_MASTER acc, student_detail sd \n" +
            "where (:due_amount is null or acc.due_amount > :due_amount)\n" +
            "and (acc.due_amount > 0 or acc.`total_fine` > 0) \n" +
            "and (sd.is_fee_due = 1)" +
            "and (:enrolment_no is null or sd.id = :enrolment_no)\n" +
            "and (:admission_class is null or sd.admission_class = :admission_class)\n" +
            "and (:admission_status is null or sd.admission_status = :admission_status)\n" +
            "and (:name is null or sd.name = :name)\n" +
            "and (:father_name is null or sd.father_name = :father_name)\n" +
            "and sd.id = acc.enrolment_no \n" +
            "order by due_amount desc";
}
