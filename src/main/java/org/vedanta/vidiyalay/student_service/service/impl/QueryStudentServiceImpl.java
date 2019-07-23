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

package org.vedanta.vidiyalay.student_service.service.impl;

import org.springframework.stereotype.Service;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.exception.StudentNotFoundException;
import org.vedanta.vidiyalay.student_service.exception.StudentWithdrawalException;
import org.vedanta.vidiyalay.student_service.mapper.StudentAdmissionDetailMapper;
import org.vedanta.vidiyalay.student_service.service.QueryStudentService;
import org.vedanta.vidiyalay.student_service.service.StudentNewAdmissionService;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import java.util.List;
import java.util.Optional;

@Service
public class QueryStudentServiceImpl implements QueryStudentService {

    private final StudentNewAdmissionService studentService;
    private final StudentAdmissionDetailMapper mapper;

    public QueryStudentServiceImpl(StudentNewAdmissionService studentService, StudentAdmissionDetailMapper mapper) {
        this.studentService = studentService;
        this.mapper = mapper;
    }


    @Override
    public Optional<StudentNewAdmissionVM> findByEnrolmentNoAndValidateAdmissionStatus(Long enrolmentNo) {
        return getStudentAdmissionDetails(enrolmentNo);
    }

    @Override
    public List<StudentNewAdmissionVM> findStudentData(Long enrolmentNo, Integer standard, Integer year, String name, AdmissionStatus admissionStatus, String fatherName, String motherName) {

        return studentService.findByParameters(enrolmentNo
                ,standard, year, name, admissionStatus, fatherName, motherName);
    }

    private Optional<StudentNewAdmissionVM> getStudentAdmissionDetails(Long enrolmentNo) {

        StudentNewAdmissionEntity studentNewAdmissionEntity = studentService.findByEnrolmentNo(enrolmentNo)
                .orElseThrow(() -> new StudentNotFoundException(enrolmentNo));

        if (studentNewAdmissionEntity.getAdmissionStatus() == AdmissionStatus.TERMINATED) {
            throw new StudentWithdrawalException(enrolmentNo);
        }

        return Optional.ofNullable(mapper.toVm(studentNewAdmissionEntity));
    }
}
