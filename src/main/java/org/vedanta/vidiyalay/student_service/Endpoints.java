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

package org.vedanta.vidiyalay.student_service;

public class Endpoints {
    //************************* start of student service endpoints **************************
    //    StudentNewAdmissionResource
    public static final String studentResource = "/api/student-resource";
    public static final String newAdmissionPost = "/newAdmission";
    public static final String updateStudentPost = "/update/student/admission/status";

    //    TerminateStudentResource
    public static final String terminateStudentResource = "/api/terminate-student";

    //    UpdateStudentDetailResource
    public static final String updateStudentDetailResource = "/api/update-student-resource";
    public static final String updateStudentResource = "/update";
    //*************************end  of student service endpoints **************************

    private Endpoints() {
        // do nothing
    }
}
