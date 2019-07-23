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

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.vedanta.vidiyalay.Utility;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.mapper.StudentAdmissionDetailMapper;
import org.vedanta.vidiyalay.student_service.message.broker.MessagePublisherHelper;
import org.vedanta.vidiyalay.student_service.service.CreateUpdateStudentService;
import org.vedanta.vidiyalay.student_service.service.StudentNewAdmissionService;
import org.vedanta.vidiyalay.student_service.service.mock.EmailVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import java.util.Calendar;
import java.util.Optional;


@Slf4j
@Component
public class CreateUpdateStudentServiceImpl implements CreateUpdateStudentService {


    private final StudentNewAdmissionService studentService;
    private final StudentAdmissionDetailMapper mapper;
    private final MessagePublisherHelper messagePublisherHelper;

    public CreateUpdateStudentServiceImpl(StudentNewAdmissionService studentService,
                                          StudentAdmissionDetailMapper mapper,
                                          MessagePublisherHelper messagePublisherHelper) {
        this.studentService = studentService;
        this.mapper = mapper;
        this.messagePublisherHelper = messagePublisherHelper;
    }

    @Override
    @Transactional
    public StudentNewAdmissionVM newAdmission(final StudentNewAdmissionVM s) {

        final StudentNewAdmissionEntity studentNewAdmissionEntity = mapper.toEntity(s);

        studentNewAdmissionEntity.setFeeDue(true);
        studentNewAdmissionEntity.setWithdrawal(true);
        studentNewAdmissionEntity.setAdmissionStatus(AdmissionStatus.PENDING_FOR_APPROVAL);

        studentNewAdmissionEntity.setDateOfAdmission(Calendar.getInstance().getTime());

        final StudentNewAdmissionVM studentNewAdmissionVM =
                Optional.ofNullable(studentService.newAdmission(studentNewAdmissionEntity))
                        .map(mapper::toVm)
                        .orElseThrow(() -> new RuntimeException("System cannot process request now."));

        log.trace("publish event student created {}", studentNewAdmissionVM);
        messagePublisherHelper.publishToStudentTopic(studentNewAdmissionVM);

        final EmailVM emailVM = EmailVM.builder()
                .subject("New admission")
                .to(studentNewAdmissionEntity.getEmail())
                .text("Congratulation! Your application is submitted for review.").build();
        log.trace("publish event to send email {}", emailVM);
        messagePublisherHelper.publishToMailTopic(emailVM);
        return studentNewAdmissionVM;
    }
}

