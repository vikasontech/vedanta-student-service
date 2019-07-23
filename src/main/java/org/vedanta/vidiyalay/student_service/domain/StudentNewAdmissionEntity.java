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

package org.vedanta.vidiyalay.student_service.domain;

import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;
import org.vedanta.vidiyalay.student_service.domain.enums.AdmissionStatus;
import org.vedanta.vidiyalay.student_service.domain.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "student_detail")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class StudentNewAdmissionEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "{error.field.mandatory}")
    private String name;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @NotNull(message = "{error.field.mandatory}")
    private Date dateOfBirth;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @NotNull(message = "{error.field.mandatory}")
    private Date dateOfAdmission;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private Date dateOfTermination;
    @NotNull(message = "{error.field.mandatory}")
    private String religion;
    @NotNull(message = "{error.field.mandatory}")
    private String caste;
    private String subcaste;
    @NotNull(message = "{error.field.mandatory}")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull(message = "{error.field.mandatory}")
    private String fatherName;
    private String motherName;
    private String occupation;
    private String relation;
    @NotNull(message = "{error.field.mandatory}")
    private String address1;
    @NotNull(message = "{error.field.mandatory}")
    private String pin1;
    private String address2;
    private String pin2;
    private String phone1;
    private String phone2;
    private String mobile;
    private String email;
    private String bloodGroup;
    private String chechak;
    private String nationality;
    private String motherTongue;
    private String areaOfInterest;
    private Integer numberOfSiblings;
    private String lastSchoolName;
    private Integer lastRank;
    private Integer lastClassYear;
    private Integer lastClass;
    private String board;
    private Integer scored;
    private Integer totalMarks;
    private String reasonForLeave;
    private String transferCertificate;
    private String lastSchoolMarkSheet;
    private String conduct;
    @NotNull(message = "{error.field.mandatory}")
    private Integer admissionClass;
    private Integer currentClass;
    @NotNull
    private boolean isWithdrawal;
    @NotNull
    private boolean isFeeDue;
    @NotNull(message = "{error.field.mandatory}")
    private String instrumentNo;
    @Enumerated(EnumType.STRING)
    @NotNull
    private AdmissionStatus admissionStatus;
}
