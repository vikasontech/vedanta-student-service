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

package org.vedanta.vidiyalay.service.impl;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.mapper.StudentAdmissionDetailMapper;
import org.vedanta.vidiyalay.student_service.message.broker.MessagePublisherHelper;
import org.vedanta.vidiyalay.student_service.service.StudentNewAdmissionService;
import org.vedanta.vidiyalay.student_service.service.impl.CreateUpdateStudentServiceImpl;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

//import org.vedanta.vidiyalay.account_service.services.AccountService;
//import org.vedanta.vidiyalay.config.kafka.StudentDetailsStreams;

//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageChannel;

@RunWith(MockitoJUnitRunner.class)
public class CreateUpdateStudentServiceImplTest {
    @InjectMocks
    private CreateUpdateStudentServiceImpl createUpdateStudentService;
    @Mock
    private StudentNewAdmissionService studentService;
    @Mock
    private StudentAdmissionDetailMapper mapper;
    @Mock
    private MessagePublisherHelper messagePublisherHelper;

    @Test
    public void newAdmission() {
        final StudentNewAdmissionVM studentNewAdmissionVM = EnhancedRandom.random(StudentNewAdmissionVM.class);
        //when
        StudentNewAdmissionEntity random = EnhancedRandom.random(StudentNewAdmissionEntity.class);
        mockObjects(studentNewAdmissionVM, random);

        final StudentNewAdmissionVM result = createUpdateStudentService.newAdmission(studentNewAdmissionVM);

        messagePublisherHelper.publishToStudentTopic(studentNewAdmissionVM);

        Assertions.assertThat(result).isNotNull();
    }



    private void mockObjects(StudentNewAdmissionVM studentNewAdmissionVM, StudentNewAdmissionEntity random) {

        doNothing().when(messagePublisherHelper).publishToMailTopic(any());
        doNothing().when(messagePublisherHelper).publishToStudentTopic(any());

        when(studentService.newAdmission(any(StudentNewAdmissionEntity.class)))
                 .thenReturn(random);
        when(mapper.toEntity(any(StudentNewAdmissionVM.class)))
                .thenReturn(random);

        when(mapper.toVm(any(StudentNewAdmissionEntity.class)))
                .thenReturn(studentNewAdmissionVM);

    }
}
