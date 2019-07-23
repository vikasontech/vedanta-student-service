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

package org.vedanta.vidiyalay.student_service.facade.impl;


import org.springframework.stereotype.Component;
import org.vedanta.vidiyalay.student_service.domain.enums.ComboType;
import org.vedanta.vidiyalay.student_service.facade.QueryComboMasterDataFacade;
import org.vedanta.vidiyalay.student_service.mapper.ComboMapper;
import org.vedanta.vidiyalay.student_service.service.QueryComboMasterDataService;
import org.vedanta.vidiyalay.student_service.web.rest.vm.AbstractComboVm;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryComboMasterDataFacadeImpl implements QueryComboMasterDataFacade {
  private final ComboMapper mapper;
  private final QueryComboMasterDataService service;

  public QueryComboMasterDataFacadeImpl(ComboMapper mapper, QueryComboMasterDataService service) {
    this.mapper = mapper;
    this.service = service;
  }

  @Override
  public List<AbstractComboVm> findAllWithBlankValue(final ComboType comboType) {

    List<AbstractComboVm> collect = service.findAll(comboType).stream()
        .map(mapper::toAbstractComboVm)
        .collect(Collectors.toList());
    collect.add(0, AbstractComboVm.builder().viewValue("< Select >").build());
    return collect;
  }

  @Override
  public List<AbstractComboVm> findAll(final ComboType comboType, boolean isOptional) {

    List<AbstractComboVm> collect =  service.findAll(comboType).stream()
        .map(mapper::toAbstractComboVm)
        .collect(Collectors.toList());
    if(isOptional) {
      collect.add(0, AbstractComboVm.builder().viewValue("< Select >").build());
    }
    return collect;
  }
}