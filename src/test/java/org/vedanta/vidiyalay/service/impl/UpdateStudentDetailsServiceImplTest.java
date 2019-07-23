package org.vedanta.vidiyalay.service.impl;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.mapper.StudentAdmissionDetailMapper;
import org.vedanta.vidiyalay.student_service.mapper.StudentAdmissionDetailMapperImpl;
import org.vedanta.vidiyalay.student_service.service.QueryStudentService;
import org.vedanta.vidiyalay.student_service.service.StudentNewAdmissionService;
import org.vedanta.vidiyalay.student_service.service.impl.UpdateStudentDetailsServiceImpl;
import org.vedanta.vidiyalay.student_service.service.mock.SendEmailNotification;
import org.vedanta.vidiyalay.student_service.web.rest.vm.AdmissionStatusVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateStudentDetailsServiceImplTest {
    @Mock
    private StudentAdmissionDetailMapper mapper;
    @Mock
    private StudentNewAdmissionService studentNewAdmissionService;
    @Mock
    private SendEmailNotification sendEmailNotification;

    @Mock
    private QueryStudentService queryStudentService;

    @InjectMocks
    private UpdateStudentDetailsServiceImpl updateStudentDetailsService;

//    @Test
//    public void update() {
//
//        final StudentNewAdmissionVM studentNewAdmissionVM = EnhancedRandom.random(StudentNewAdmissionVM.class);
//        final StudentNewAdmissionEntity studentNewAdmissionEntity = new StudentAdmissionDetailMapperImpl().toEntity(studentNewAdmissionVM);
//
//        when(mapper.toEntity(studentNewAdmissionVM))
//            .thenReturn(studentNewAdmissionEntity);
//
//        when(studentNewAdmissionService.updateStudentDetails(any()))
//            .thenReturn(studentNewAdmissionEntity);
//
//        when(mapper.toVm(studentNewAdmissionEntity))
//            .thenReturn(studentNewAdmissionVM);
//
//        doNothing().when(sendEmailNotification).sendEmail(any());
//
//        //when
//        final StudentNewAdmissionVM result = updateStudentDetailsService.update(studentNewAdmissionVM);
//
//        Assert.notNull(result, "result is null");
//        Assert.isTrue(result.equals(studentNewAdmissionVM), "result is not match");
//
//    }


    @Test
    public void updateAdmissionStatus() {
        final AdmissionStatusVM admissionStatusVM = EnhancedRandom.random(AdmissionStatusVM.class);
        final Optional<StudentNewAdmissionVM> studentNewAdmissionVMOptional =
            Optional.of(EnhancedRandom.random(StudentNewAdmissionVM.class));

        when(queryStudentService.findByEnrolmentNoAndValidateAdmissionStatus(admissionStatusVM.getEnrolmentNo()))
            .thenReturn(studentNewAdmissionVMOptional);

        final StudentNewAdmissionVM studentNewAdmissionVM = studentNewAdmissionVMOptional.get();
        final StudentNewAdmissionEntity studentNewAdmissionEntity = new StudentAdmissionDetailMapperImpl().toEntity(studentNewAdmissionVM);

        when(mapper.toEntity(studentNewAdmissionVM))
            .thenReturn(studentNewAdmissionEntity);

        when(studentNewAdmissionService.updateStudentDetails(any()))
            .thenReturn(studentNewAdmissionEntity);

        when(mapper.toVm(studentNewAdmissionEntity))
            .thenReturn(studentNewAdmissionVM);

        doNothing().when(sendEmailNotification).sendEmail(any());

        //when
        updateStudentDetailsService.updateAdmissionStatus(admissionStatusVM);
    }

//    @Mock
//    private AccountService accountService;

//
//    @InjectMocks
//    private UpdateStudentDetailsServiceImpl updateStudentDetailsService;
//
//    @Test(expected = IllegalArgumentException.class)
//    public void update_unable_to_save_student_data() {
//        //Mock
//        final StudentNewAdmissionVM studentNewAdmissionVM = EnhancedRandom.random(StudentNewAdmissionVM.class);
//        final StudentNewAdmissionEntity studentNewAdmissionEntity = new StudentAdmissionDetailMapperImpl().toEntity(studentNewAdmissionVM);
//
//        mockStudentNewAdmissionVMtoEntity(studentNewAdmissionVM, studentNewAdmissionEntity);
//
//        Mockito.when(studentNewAdmissionService.updateStudentDetails(studentNewAdmissionEntity))
//                .thenReturn(null);
//
//        //when
//        updateStudentDetailsService.update(studentNewAdmissionVM);
//
//        //verify
//        Mockito.verify(mapper).toEntity(studentNewAdmissionVM);
//        Mockito.verify(studentNewAdmissionService).updateStudentDetails(studentNewAdmissionEntity);
//    }
//
//    private void mockStudentNewAdmissionVMtoEntity(StudentNewAdmissionVM studentNewAdmissionVM, StudentNewAdmissionEntity studentNewAdmissionEntity) {
//        Mockito.when(mapper.toEntity(studentNewAdmissionVM))
//                .thenReturn(studentNewAdmissionEntity);
//    }
//
//    @Test
//    public void update() {
//        //Mock
//        final StudentNewAdmissionVM studentNewAdmissionVM = EnhancedRandom.random(StudentNewAdmissionVM.class);
//        final StudentNewAdmissionEntity studentNewAdmissionEntity = new StudentAdmissionDetailMapperImpl().toEntity(studentNewAdmissionVM);
//
//        mockStudentNewAdmissionVMtoEntity(studentNewAdmissionVM, studentNewAdmissionEntity);
//        mockStudentNewAdmissionEntityToVM(studentNewAdmissionEntity, studentNewAdmissionVM);
//        Mockito.when(studentNewAdmissionService.updateStudentDetails(studentNewAdmissionEntity))
//                .thenReturn(studentNewAdmissionEntity);
//
//        //when
//        StudentNewAdmissionVM result = updateStudentDetailsService.update(studentNewAdmissionVM);
//
//        //verify
//        Mockito.verify(mapper).toEntity(studentNewAdmissionVM);
//        Mockito.verify(mapper).toVm(studentNewAdmissionEntity);
//        Mockito.verify(studentNewAdmissionService).updateStudentDetails(studentNewAdmissionEntity);
//
//        Assertions.assertThat(result).isNotNull().isEqualTo(studentNewAdmissionVM);
//    }
//
//    private void mockStudentNewAdmissionEntityToVM(StudentNewAdmissionEntity studentNewAdmissionEntity, StudentNewAdmissionVM studentNewAdmissionVM) {
//        Mockito.when(mapper.toVm(studentNewAdmissionEntity))
//                .thenReturn(studentNewAdmissionVM);
//    }
//
//    @Test(expected = StudentNotFoundException.class)
//    public void terminate_student_not_found() {
//        //mock
//        final TerminateStudentVM terminateStudentVM = EnhancedRandom.random(TerminateStudentVM.class);
//        Mockito.when(queryStudentService.findByEnrolmentNoAndValidateAdmissionStatus(terminateStudentVM.getEnrolmentNo()))
//                .thenReturn(Optional.empty());
//        //when
//        updateStudentDetailsService.terminate(terminateStudentVM, EnhancedRandom.random(AccountMasterVM.class));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void terminate_student_account_not_found_in_master() {
//        //mock
//        final TerminateStudentVM terminateStudentVM = EnhancedRandom.random(TerminateStudentVM.class);
//        final StudentNewAdmissionVM studentNewAdmissionVM = EnhancedRandom.random(StudentNewAdmissionVM.class);
//        studentNewAdmissionVM.setId(terminateStudentVM.getEnrolmentNo());
//
//        mockQueryStudent(terminateStudentVM.getEnrolmentNo(), studentNewAdmissionVM);
//
////        Mockito.when(accountService.getAccountDetails(terminateStudentVM.getEnrolmentNo()))
////                .thenReturn(null);
//
//        //when
//        updateStudentDetailsService.terminate(terminateStudentVM, EnhancedRandom.random(AccountMasterVM.class));
//
//        Mockito.verify(queryStudentService).findByEnrolmentNoAndValidateAdmissionStatus(terminateStudentVM.getEnrolmentNo());
//        Mockito.verify(accountService).getAccountDetails(terminateStudentVM.getEnrolmentNo());
//
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void terminate_fee_due() {
//        //mock
//        final TerminateStudentVM terminateStudentVM = EnhancedRandom.random(TerminateStudentVM.class);
//        final StudentNewAdmissionVM studentNewAdmissionVM = EnhancedRandom.random(StudentNewAdmissionVM.class);
//        studentNewAdmissionVM.setId(terminateStudentVM.getEnrolmentNo());
//
//        mockQueryStudent(terminateStudentVM.getEnrolmentNo(), studentNewAdmissionVM);
//
//        mockAccountFacadeAccountDetails(terminateStudentVM, BigDecimal.ONE);
//        //when
//        updateStudentDetailsService.terminate(terminateStudentVM, EnhancedRandom.random(AccountMasterVM.class));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void terminate_unable_to_process_request() {
//        //mock
//        final TerminateStudentVM terminateStudentVM = EnhancedRandom.random(TerminateStudentVM.class);
//        final StudentNewAdmissionVM studentNewAdmissionVM = EnhancedRandom.random(StudentNewAdmissionVM.class);
//        studentNewAdmissionVM.setId(terminateStudentVM.getEnrolmentNo());
//
//        mockQueryStudent(terminateStudentVM.getEnrolmentNo(), studentNewAdmissionVM);
//
//        mockAccountFacadeAccountDetails(terminateStudentVM, BigDecimal.ONE);
//
//        //when
//        updateStudentDetailsService.terminate(terminateStudentVM, EnhancedRandom.random(AccountMasterVM.class));
//    }
//
//    @Test
//    public void terminate() throws InstantiationException, IllegalAccessException {
//        //mock
//        final TerminateStudentVM terminateStudentVM = EnhancedRandom.random(TerminateStudentVM.class);
//        final StudentNewAdmissionVM studentNewAdmissionVM = EnhancedRandom.random(StudentNewAdmissionVM.class);
//
//        studentNewAdmissionVM.setId(terminateStudentVM.getEnrolmentNo());
//
//        mockQueryStudent(terminateStudentVM.getEnrolmentNo(), studentNewAdmissionVM);
//
//        mockAccountFacadeAccountDetails(terminateStudentVM, BigDecimal.ZERO);
//
//        mockStudentAdmissionProcess(terminateStudentVM, studentNewAdmissionVM);
//
//        mockStudentNewAdmissionEntityToVM(Mockito.any(StudentNewAdmissionEntity.class), studentNewAdmissionVM);
//        mockEmailNotification();
////        final AccountMasterVM random
////                = CloneObjects.clone(EnhancedRandom.random(AccountMasterVM.class),
////                AccountMasterVM.class, new CloneObjects.ParameterMap()
////                        .put("dueAmount", BigDecimal.ZERO).build());
//
//        //when
//        CloneObjects.clone(EnhancedRandom.random(AccountMasterVM.class),
//                AccountMasterVM.class, new CloneObjects.ParameterMap()
//                        .put("dueAmount", BigDecimal.ZERO).build())
//                .ifPresent(e -> updateStudentDetailsService.terminate(terminateStudentVM, e));
//
//        //verify
//        Mockito.verify(queryStudentService).findByEnrolmentNoAndValidateAdmissionStatus(terminateStudentVM.getEnrolmentNo());
//        Mockito.verify(studentNewAdmissionService).updateStudentDetails(Mockito.any(StudentNewAdmissionEntity.class));
//        Mockito.verify(mapper).toVm(Mockito.any(StudentNewAdmissionEntity.class));
//    }
//
//    @Test
//    public void updateAdmissionStatus() {
//        final AdmissionStatusVM admissionStatusVM = EnhancedRandom.random(AdmissionStatusVM.class);
//        final StudentNewAdmissionVM mockStudentNewAdmissionVM = EnhancedRandom.random(StudentNewAdmissionVM.class);
//        final StudentNewAdmissionEntity mockStudentNewAdmissionEntity = EnhancedRandom.random(StudentNewAdmissionEntity.class);
//        //given
//        mockQueryStudent(admissionStatusVM.getEnrolmentNo(), mockStudentNewAdmissionVM);
//        mockEmailNotification();
//        mockStudentNewAdmissionVMtoEntity(mockStudentNewAdmissionVM, mockStudentNewAdmissionEntity);
//        mockStudentNewAdmissionEntityToVM(mockStudentNewAdmissionEntity, mockStudentNewAdmissionVM);
//        mockUpdateStudentAdmissionService(mockStudentNewAdmissionEntity);
//        //when
//        final StudentNewAdmissionVM studentNewAdmissionVM = updateStudentDetailsService.updateAdmissionStatus(admissionStatusVM);
//        //verify
//        Assertions.assertThat(studentNewAdmissionVM).isNotNull().isEqualTo(mockStudentNewAdmissionVM);
//        Mockito.verify(studentNewAdmissionService).updateStudentDetails(Mockito.any(StudentNewAdmissionEntity.class));
//    }
//
//    private void mockAccountFacadeAccountDetails(final TerminateStudentVM terminateStudentVM, final BigDecimal dueAmount) {
//        final AccountMasterVM random = EnhancedRandom.random(AccountMasterVM.class);
//
//        final Map<String, Object> parameters = new CloneObjects.ParameterMap()
//                .put("enrolmentNo", terminateStudentVM.getEnrolmentNo())
//                .put("dueAmount", ObjectUtils.isEmpty(dueAmount) ? BigDecimal.ONE : dueAmount).build();
//
//    }
//
//    private void mockQueryStudent(final Long enrolmentNo, final StudentNewAdmissionVM studentNewAdmissionVM) {
//        Mockito.when(queryStudentService.findByEnrolmentNoAndValidateAdmissionStatus(enrolmentNo))
//                .thenReturn(Optional.of(ObjectUtils.isEmpty(studentNewAdmissionVM) ? EnhancedRandom.random(StudentNewAdmissionVM.class) : studentNewAdmissionVM));
//    }
//
//    private void mockStudentAdmissionProcess(TerminateStudentVM terminateStudentVM, StudentNewAdmissionVM studentNewAdmissionVM) {
//        final StudentNewAdmissionEntity studentNewAdmissionEntity = EnhancedRandom.random(StudentNewAdmissionEntity.class);
//        mockStudentNewAdmissionVMtoEntity(studentNewAdmissionVM, studentNewAdmissionEntity);
//
//        CloneObjects.clone(studentNewAdmissionEntity, studentNewAdmissionEntity.getClass()
//                , new CloneObjects.ParameterMap()
//                        .put("id", terminateStudentVM.getEnrolmentNo()).build())
//                .ifPresent(this::mockUpdateStudentAdmissionService);
//    }
//
//    private void mockUpdateStudentAdmissionService(StudentNewAdmissionEntity studentNewAdmissionEntity) {
//        Mockito.when(studentNewAdmissionService.updateStudentDetails(Mockito.any(StudentNewAdmissionEntity.class)))
//                .thenReturn(studentNewAdmissionEntity);
//    }
//
//    private void mockEmailNotification() {
//        Mockito.doNothing().when(sendEmailNotification).sendEmail(Mockito.any(EmailVM.class));
//    }

}
