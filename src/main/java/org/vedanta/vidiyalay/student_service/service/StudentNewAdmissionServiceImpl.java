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

package org.vedanta.vidiyalay.student_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.mapper.StudentAdmissionDetailMapper;
import org.vedanta.vidiyalay.student_service.repository.StudentNewAdmissionEntityRepository;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentNewAdmissionServiceImpl implements StudentNewAdmissionService {

    private final StudentAdmissionDetailMapper mapper;
    private final StudentNewAdmissionEntityRepository studentNewAdmissionEntityRepository;

    public StudentNewAdmissionServiceImpl(StudentAdmissionDetailMapper mapper, StudentNewAdmissionEntityRepository studentNewAdmissionEntityRepository) {
        this.mapper = mapper;
        this.studentNewAdmissionEntityRepository = studentNewAdmissionEntityRepository;
    }

    @Override
    public StudentNewAdmissionEntity newAdmission(StudentNewAdmissionEntity studentNewAdmissionEntity) {

        return Optional.of(studentNewAdmissionEntityRepository.save(studentNewAdmissionEntity))
                .orElseThrow(() -> new RuntimeException("cannot newAdmission student Details"));
    }

    @Override
    public StudentNewAdmissionEntity updateStudentDetails(StudentNewAdmissionEntity studentNewAdmissionEntity) {
        Assert.notNull(studentNewAdmissionEntity.getId(), String.format("Id '%s' not exists, it will create new record. is student really exists!", studentNewAdmissionEntity.getId()));
        return Optional.of(studentNewAdmissionEntityRepository.save(studentNewAdmissionEntity))
                .orElseThrow(() -> new RuntimeException("Cannot update student details."));
    }


    @Override
    public Optional<StudentNewAdmissionEntity> findByEnrolmentNo(Long id) {
        return studentNewAdmissionEntityRepository.findById(id);
    }

    @Override
    public Iterable<StudentNewAdmissionEntity> findAll() {
        return studentNewAdmissionEntityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentNewAdmissionVM> findByParameters(Long enrolmentNo, Integer standard, Integer year, String name, AdmissionStatus admissionStatus, String fatherName, String motherName) {

        return Optional.ofNullable(studentNewAdmissionEntityRepository.findStudentDetailsByQueryFindByEnrolmentNo(enrolmentNo,
                standard, year, name, admissionStatus, fatherName, motherName))
                .map(e -> e.stream().map(mapper::toVm).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
