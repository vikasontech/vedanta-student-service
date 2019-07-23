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

package org.vedanta.vidiyalay.student_service.web.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vedanta.vidiyalay.student_service.domain.enums.ComboType;
import org.vedanta.vidiyalay.student_service.facade.QueryComboMasterDataFacade;
import org.vedanta.vidiyalay.utils.BasicResponse;
import org.vedanta.vidiyalay.utils.ResponseUtils;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/combo")
public class ComboDataResource {

  private final QueryComboMasterDataFacade queryComboMasterDataFacade;

  public ComboDataResource(QueryComboMasterDataFacade queryComboMasterDataFacade) {
    this.queryComboMasterDataFacade = queryComboMasterDataFacade;
  }

  @GetMapping
  public ResponseEntity<BasicResponse> comboValue (@NotNull  @RequestParam Boolean isOptional,
                                                   @NotNull @RequestParam ComboType comboType) {
    return ResponseEntity.ok(ResponseUtils.getBasicResponse(Optional.of(queryComboMasterDataFacade.findAll(comboType, isOptional))
        .orElse(Collections.emptyList()), HttpStatus.OK));
  }


}


