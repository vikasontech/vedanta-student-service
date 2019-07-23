package org.vedanta.vidiyalay.facade.impl;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.vedanta.vidiyalay.student_service.account_service.AccountServiceHelper;
import org.vedanta.vidiyalay.student_service.facade.QueryStudentFacade;
import org.vedanta.vidiyalay.student_service.facade.impl.UpdateStudentDetailsFacadeImpl;
import org.vedanta.vidiyalay.student_service.service.UpdateStudentDetailsService;
import org.vedanta.vidiyalay.student_service.service.mock.SendEmailNotification;
import org.vedanta.vidiyalay.student_service.web.rest.vm.AdmissionStatusVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateStudentDetailsFacadeImplTest {

    @Mock
    AccountServiceHelper accountServiceHelper;

    @Mock
    private UpdateStudentDetailsService updateStudentDetailsService;

    @Mock
    private QueryStudentFacade queryStudentFacade;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Mock
    private SendEmailNotification sendEmailNotification;

    @InjectMocks
    private UpdateStudentDetailsFacadeImpl updateStudentDetailsFacade;



    @Test
    public void update() {
        StudentNewAdmissionVM studentNewAdmissionVM
                = EnhancedRandom.random(StudentNewAdmissionVM.class);

        when(updateStudentDetailsService.update(studentNewAdmissionVM))
                .thenReturn(studentNewAdmissionVM);

        StudentNewAdmissionVM result = updateStudentDetailsFacade.update(studentNewAdmissionVM);

        Assertions.assertThat(result).isEqualToComparingFieldByField(studentNewAdmissionVM);

        Mockito.verify(updateStudentDetailsService).update(studentNewAdmissionVM);

    }
//
//    @Test
//    public void terminate() {
//        StudentNewAdmissionVM studentNewAdmissionVM
//                = EnhancedRandom.random(StudentNewAdmissionVM.class);
//        TerminateStudentVM terminateStudentVM
//                = EnhancedRandom.random(TerminateStudentVM.class);
//        final AccountMasterVM accountMasterVM = EnhancedRandom.random(AccountMasterVM.class);
//
//
//        when(queryStudentFacade.findByEnrolmentNoAndValidateAdmissionStatus(terminateStudentVM.getEnrolmentNo())
//        ).thenReturn(Optional.of(studentNewAdmissionVM));
//
//
////        doNothing().when(applicationEventPublisher).publishEvent(any());
//
//        doNothing().when(sendEmailNotification).sendEmail(any());
//
//        StudentNewAdmissionVM result = updateStudentDetailsFacade.terminate(terminateStudentVM);
//
//        Assertions.assertThat(result).isEqualToComparingFieldByField(studentNewAdmissionVM);
//
////        Mockito.verify(updateStudentDetailsService).terminate(terminateStudentVM, accountMasterVM);
//    }

    @Test
    public void updateAdmissionStatus() {
//        return updateStudentDetailsService.updateAdmissionStatus(admissionStatusVM);

        StudentNewAdmissionVM studentNewAdmissionVM
                = EnhancedRandom.random(StudentNewAdmissionVM.class);
        AdmissionStatusVM admissionStatusVM
                = EnhancedRandom.random(AdmissionStatusVM.class);

        when(updateStudentDetailsService.updateAdmissionStatus(admissionStatusVM))
                .thenReturn(studentNewAdmissionVM);

        StudentNewAdmissionVM result = updateStudentDetailsFacade.updateAdmissionStatus(admissionStatusVM);

        Assertions.assertThat(result).isEqualToComparingFieldByField(studentNewAdmissionVM);

        Mockito.verify(updateStudentDetailsService).updateAdmissionStatus(admissionStatusVM);

    }
}