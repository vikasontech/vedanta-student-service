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
import org.vedanta.vidiyalay.student_service.account_service.AccountServiceHelper;
import org.vedanta.vidiyalay.student_service.domain.StudentNewAdmissionEntity;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.mapper.StudentVMToDueFeeDetailsVMMapper;
import org.vedanta.vidiyalay.student_service.repository.StudentNewAdmissionEntityRepository;
import org.vedanta.vidiyalay.student_service.service.QueryFeeDueService;
import org.vedanta.vidiyalay.student_service.web.rest.vm.AccountMasterVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.DueFeesDetailsVM;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class QueryFeeDueServiceImpl implements QueryFeeDueService {
    private final StudentNewAdmissionEntityRepository studentNewAdmissionEntityRepository;
    private final AccountServiceHelper accountServiceHelper;
    private final StudentVMToDueFeeDetailsVMMapper studentVMToDueFeeDetailsVMMapper;

    public QueryFeeDueServiceImpl(StudentNewAdmissionEntityRepository studentNewAdmissionEntityRepository,
                                  AccountServiceHelper accountServiceHelper,
                                  StudentVMToDueFeeDetailsVMMapper studentVMToDueFeeDetailsVMMapper) {

        this.studentNewAdmissionEntityRepository = studentNewAdmissionEntityRepository;
        this.accountServiceHelper = accountServiceHelper;
        this.studentVMToDueFeeDetailsVMMapper = studentVMToDueFeeDetailsVMMapper;
    }

    @Override
    public List<DueFeesDetailsVM> findDueFee(Long enrolmentNo, Integer standard, String name, AdmissionStatus admissionStatus,
                                             BigDecimal amount, String fatherName) {

        final List<StudentNewAdmissionEntity> studentDetailsByQueryFindByEnrolmentNo = studentNewAdmissionEntityRepository.findStudentDetailsByQueryFindByEnrolmentNo(enrolmentNo,
                standard, null, name, admissionStatus, fatherName, null);

        final Map<Long, AccountMasterVM> accountMasterDatas = accountServiceHelper.findAllThatHaveFeeDue().stream()
                .collect(Collectors.toMap(AccountMasterVM::getEnrolmentNo, Function.identity()));

        return studentDetailsByQueryFindByEnrolmentNo.stream()
                .filter(StudentNewAdmissionEntity::isFeeDue)
                .filter(e -> accountMasterDatas.containsKey(e.getId()))
                .map(e -> createDueFeeDetailsVM(e, accountMasterDatas.get(e.getId())))
                .collect(Collectors.toList());
    }

    private DueFeesDetailsVM createDueFeeDetailsVM(StudentNewAdmissionEntity e, AccountMasterVM accountMasterVM) {
        final DueFeesDetailsVM dueFeesDetailsVM = studentVMToDueFeeDetailsVMMapper.toDueFeeDetails(e);
        dueFeesDetailsVM.setAmount(accountMasterVM.getDueAmount());
        dueFeesDetailsVM.setTotalFine(accountMasterVM.getTotalFine());
        return dueFeesDetailsVM;
    }
}


