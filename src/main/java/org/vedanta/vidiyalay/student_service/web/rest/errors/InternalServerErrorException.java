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

//import org.zalando.problem.AbstractThrowableProblem;
//import org.zalando.problem.Status;

/**
 * Simple exception with a message, that returns an Internal Server Error code.
 */
public class InternalServerErrorException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public InternalServerErrorException(String message) {
        super(message);
    }
}
