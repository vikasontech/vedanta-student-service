/*
 * Copyright (C) 2019  Vikas Kumar Verma
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.vedanta.vidiyalay.student_service.web.rest.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vedanta.vidiyalay.student_service.domain.BaseEntity;
import org.vedanta.vidiyalay.student_service.domain.enums.AccountType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AccountMasterVM extends BaseEntity implements Serializable {
    private Long id;

    private String name;
    @NotNull
    private Long enrolmentNo;
    private Date dateOfOpening;
    private BigDecimal totalFee;
    private BigDecimal dueAmount;
    private AccountType accountType;
    private BigDecimal totalFine;
}
