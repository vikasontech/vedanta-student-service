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

package org.vedanta.vidiyalay.student_service.web.rest;

//import io.github.jhipster.web.util.ResponseUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for getting the audit events.
 */
@RestController
@RequestMapping("/management/audits")
public class AuditResource {
//
//    private final AuditEventService auditEventService;
//
//    public AuditResource(AuditEventService auditEventService) {
//        this.auditEventService = auditEventService;
//    }
//
//    /**
//     * GET /audits : get a page of AuditEvents.
//     *
//     * @param pageable the pagination information
//     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body
//     */
//    @GetMapping
//    public ResponseEntity<List<AuditEvent>> getAll(Pageable pageable) {
//        Page<AuditEvent> page = auditEventService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/management/audits");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }
//
//    /**
//     * GET  /audits : get a page of AuditEvents between the fromDate and toDate.
//     *
//     * @param fromDate the start of the time period of AuditEvents to get
//     * @param toDate the end of the time period of AuditEvents to get
//     * @param pageable the pagination information
//     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body
//     */
//    @GetMapping(params = {"fromDate", "toDate"})
//    public ResponseEntity<List<AuditEvent>> getByDates(
//        @RequestParam(value = "fromDate") LocalDate fromDate,
//        @RequestParam(value = "toDate") LocalDate toDate,
//        Pageable pageable) {
//
//        Page<AuditEvent> page = auditEventService.findByDates(
//            fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),
//            toDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant(),
//            pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/management/audits");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }
//
//    /**
//     * GET  /audits/:id : get an AuditEvent by id.
//     *
//     * @param id the id of the entity to get
//     * @return the ResponseEntity with status 200 (OK) and the AuditEvent in body, or status 404 (Not Found)
//     */
//    @GetMapping("/{id:.+}")
//    public ResponseEntity<AuditEvent> get(@PathVariable Long id) {
//        return ResponseUtil.wrapOrNotFound(auditEventService.find(id));
//    }
}
