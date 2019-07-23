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

package org.vedanta.vidiyalay.student_service.web.rest.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.vedanta.vidiyalay.student_service.exception.StudentNotFoundException;
import org.vedanta.vidiyalay.student_service.exception.StudentWithdrawalException;
import org.vedanta.vidiyalay.utils.BasicResponse;

@ControllerAdvice
public class ExceptionTranslatorCustom extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { StudentWithdrawalException.class, IllegalStateException.class, RuntimeException.class,
            StudentNotFoundException.class,
            Exception.class, RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {

        BasicResponse basicResponse = BasicResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return handleExceptionInternal(ex, basicResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}

