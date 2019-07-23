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

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;

import java.util.List;

public interface StudentNewAdmissionEntityRepository extends CrudRepository<StudentNewAdmissionEntity, Long> {
    
    @Query(value = QUERY, nativeQuery = true)
    List<StudentNewAdmissionEntity> findStudentDetailsByQueryFindByEnrolmentNo(
            @Param(value = "enrolmentNo") Long enrolmentNo,
            @Param(value = "standard") Integer standard,
            @Param(value = "year") Integer year,
            @Param(value = "name") String name,
            @Param(value = "admissionStatus") AdmissionStatus admissionStatus,
            @Param(value = "fatherName") String fatherName,
            @Param(value = "motherName") String motherName);
    
    String  QUERY = "select id,\n" +
            "created_by, created_date, last_modified_by, last_modified_date," +
            "address1,\n" +
            "address2,\n" +
            "admission_class,\n" +
            "admission_status,\n" +
            "area_of_interest,\n" +
            "blood_group,\n" +
            "board,\n" +
            "caste,\n" +
            "chechak,\n" +
            "conduct,\n" +
            "current_class,\n" +
            "date_format(date_of_birth,'%Y-%m-%d') as date_of_birth,\n" +
            "date_format(date_of_termination,'%Y-%m-%d') as date_of_termination,\n" +
            "email,\n" +
            "father_name,\n" +
            "gender,\n" +
            "instrument_no,\n" +
            "is_fee_due,\n" +
            "is_withdrawal,\n" +
            "last_class,\n" +
            "last_class_year,\n" +
            "last_rank,\n" +
            "last_school_mark_sheet,\n" +
            "last_school_name,\n" +
            "mobile,\n" +
            "mother_name,\n" +
            "mother_tongue,\n" +
            "name,\n" +
            "nationality,\n" +
            "number_of_siblings,\n" +
            "occupation,\n" +
            "phone1,\n" +
            "phone2,\n" +
            "pin1,\n" +
            "pin2,\n" +
            "reason_for_leave,\n" +
            "relation,\n" +
            "religion,\n" +
            "scored,\n" +
            "subcaste,\n" +
            "total_marks,\n" +
            "transfer_certificate,\n" +
            "date_format(date_of_admission,'%Y-%m-%d') as date_of_admission " +
            "from student_detail " +
            "where (:enrolmentNo is null or id = :enrolmentNo) " +
            "and (:standard is null or admission_class = :standard ) " +
            "and (:name is null or name like %:name% ) " +
            "and (:admissionStatus is null or admission_status = :admissionStatus) " +
            "and (:fatherName is null or father_name like %:fatherName%) " +
            "and (:motherName is null or mother_name like %:motherName%) " +
            "and (:year is null or date_format(date_of_admission, '%Y')= :year) " ;
}
