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

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.exception.StudentNotFoundException;
import org.vedanta.vidiyalay.student_service.facade.QueryStudentFacade;
import org.vedanta.vidiyalay.student_service.facade.UpdateStudentDetailsFacade;
import org.vedanta.vidiyalay.student_service.service.UpdateStudentDetailsService;
import org.vedanta.vidiyalay.student_service.service.mock.EmailVM;
import org.vedanta.vidiyalay.student_service.service.mock.SendEmailNotification;
import org.vedanta.vidiyalay.student_service.web.rest.vm.AdmissionStatusVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.TerminateStudentVM;

import javax.validation.constraints.NotNull;

@Component
public class UpdateStudentDetailsFacadeImpl implements UpdateStudentDetailsFacade {
    private final UpdateStudentDetailsService updateStudentDetailsService;
    private final QueryStudentFacade queryStudentFacade;
    private final SendEmailNotification sendEmailNotification;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UpdateStudentDetailsFacadeImpl(UpdateStudentDetailsService updateStudentDetailsService, QueryStudentFacade queryStudentFacade, SendEmailNotification sendEmailNotification, ApplicationEventPublisher applicationEventPublisher) {
        this.updateStudentDetailsService = updateStudentDetailsService;
        this.queryStudentFacade = queryStudentFacade;
        this.sendEmailNotification = sendEmailNotification;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public StudentNewAdmissionVM update(final StudentNewAdmissionVM studentNewAdmissionVM){
        return updateStudentDetailsService.update(studentNewAdmissionVM);
    }

    @Override
    public StudentNewAdmissionVM terminate(final TerminateStudentVM terminateStudentVM) {
        // function that would get the details from account service
        // will check the student status if the student status is termination_in_process
        // then terminate the student and send mail about the termination
        // get student details
        final StudentNewAdmissionVM studentNewAdmissionVM = queryStudentFacade.findByEnrolmentNoAndValidateAdmissionStatus(terminateStudentVM.getEnrolmentNo())
                .orElseThrow(() -> new StudentNotFoundException(terminateStudentVM.getEnrolmentNo()));

        studentNewAdmissionVM.setAdmissionStatus(AdmissionStatus.TERMINATION_INITIATED);
        studentNewAdmissionVM.setReasonForLeave(terminateStudentVM.getReasonForLeave());
        updateStudentDetailsService.update(studentNewAdmissionVM);

        //send email notification
        sendEmailNotification.sendEmail(EmailVM.builder()
                .subject("Termination process starting!")
                .text(String.format("Student %s Father Name %s Enrolment No :%d process started for termination. ",
                        studentNewAdmissionVM.getName(), studentNewAdmissionVM.getFatherName(),
                        studentNewAdmissionVM.getId()))
                .to(studentNewAdmissionVM.getEmail())
                .build());

        return studentNewAdmissionVM;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public StudentNewAdmissionVM updateAdmissionStatus(@NotNull AdmissionStatusVM admissionStatusVM) {
        return updateStudentDetailsService.updateAdmissionStatus(admissionStatusVM);
    }

}
