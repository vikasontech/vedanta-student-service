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

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.exception.StudentNotFoundException;
import org.vedanta.vidiyalay.student_service.mapper.StudentAdmissionDetailMapper;
import org.vedanta.vidiyalay.student_service.service.QueryStudentService;
import org.vedanta.vidiyalay.student_service.service.StudentNewAdmissionService;
import org.vedanta.vidiyalay.student_service.service.UpdateStudentDetailsService;
import org.vedanta.vidiyalay.student_service.service.mock.EmailVM;
import org.vedanta.vidiyalay.student_service.service.mock.SendEmailNotification;
import org.vedanta.vidiyalay.student_service.web.rest.vm.AdmissionStatusVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Component
public class UpdateStudentDetailsServiceImpl implements UpdateStudentDetailsService {

    private final StudentAdmissionDetailMapper mapper;
    private final StudentNewAdmissionService studentNewAdmissionService;
    private final SendEmailNotification sendEmailNotification;
    private final QueryStudentService queryStudentService;

    public UpdateStudentDetailsServiceImpl(StudentAdmissionDetailMapper mapper, StudentNewAdmissionService studentNewAdmissionService,
                                           SendEmailNotification sendEmailNotification, QueryStudentService queryStudentService) {
        this.mapper = mapper;
        this.studentNewAdmissionService = studentNewAdmissionService;
        this.sendEmailNotification = sendEmailNotification;
        this.queryStudentService = queryStudentService;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public StudentNewAdmissionVM update(final StudentNewAdmissionVM studentNewAdmissionVM) {
        Assert.notNull(studentNewAdmissionVM, "studentDetailsStreams IS NULL!");
        Assert.notNull(studentNewAdmissionVM.getId(), "id is blank cannot update the student data.");

        final StudentNewAdmissionEntity studentNewAdmissionEntity = mapper.toEntity(studentNewAdmissionVM);

        //update student details
        final StudentNewAdmissionVM resultVM = Optional
                .ofNullable(studentNewAdmissionService.updateStudentDetails(studentNewAdmissionEntity))
                .map(mapper::toVm)
                .orElseThrow(() -> new IllegalArgumentException("System cannot press request now."));

        //send email notification
        sendEmailNotification.sendEmail(EmailVM.builder()
                .subject("Student information updated in System.")
                .text(String.format("Student id: %d Name: %s", studentNewAdmissionEntity.getId(), studentNewAdmissionEntity.getName()))
                .to(studentNewAdmissionVM.getEmail())
                .build());

        return resultVM;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public StudentNewAdmissionVM updateAdmissionStatus(@NotNull AdmissionStatusVM admissionStatusVM) {

        final StudentNewAdmissionVM studentNewAdmissionVM = getStudentNewAdmissionDetails(admissionStatusVM.getEnrolmentNo());

        studentNewAdmissionVM.setAdmissionStatus(admissionStatusVM.getAdmissionStatus());
        final StudentNewAdmissionVM updatedStudentNewAdmissionVM = update(studentNewAdmissionVM);

//        sending email notification
        sendEmailNotification.sendEmail(EmailVM.builder()
                .subject("Student Admission Status Change to " + updatedStudentNewAdmissionVM.getAdmissionStatus().name())
                .text("Student Admission Status Change to " + updatedStudentNewAdmissionVM.getAdmissionStatus().name())
                .to(studentNewAdmissionVM.getEmail())
                .build());

        return updatedStudentNewAdmissionVM;
    }
//
//    @Override
//    public void terminate_V2(AccountMasterVM accountMasterVM) {
//        final StudentNewAdmissionVM studentNewAdmissionVM = getStudentNewAdmissionDetails(accountMasterVM.getEnrolmentNo());
//
//
//        // create a service that will check if the student have any fee due
//        if (accountMasterVM.getDueAmount().compareTo(BigDecimal.ZERO) > 0) {
//            throw new IllegalArgumentException("This student as total Fee Due: " + accountMasterVM.getDueAmount());
//        }
//
////        update student details
//        studentNewAdmissionVM.setAdmissionStatus(AdmissionStatus.TERMINATED);
//        studentNewAdmissionVM.setDateOfTermination(Calendar.getInstance().getTime());
//
//        StudentNewAdmissionVM result = Optional.ofNullable(studentNewAdmissionService.updateStudentDetails(mapper.toEntity(studentNewAdmissionVM)))
//                .map(mapper::toVm)
//                .orElseThrow(() -> new IllegalArgumentException("Cannot process now, please try later"));
//
//        //send email notification
//        sendEmailNotification.sendEmail(EmailVM.builder()
//                .subject("Student Terminated!")
//                .text("Student " + result.getId() + " Terminated from school")
//                .to(studentNewAdmissionVM.getEmail())
//                .build());
//
//
//    }

    private StudentNewAdmissionVM getStudentNewAdmissionDetails(Long enrolmentNo) {
        return queryStudentService.findByEnrolmentNoAndValidateAdmissionStatus(enrolmentNo)
                .orElseThrow(() -> new StudentNotFoundException(enrolmentNo));
    }

}
