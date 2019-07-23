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

package org.vedanta.vidiyalay.student_service.service.impl;

import org.springframework.stereotype.Service;
import org.vedanta.vidiyalay.student_service.domain.ComboMasterEntity;
import org.vedanta.vidiyalay.student_service.domain.enums.ComboType;
import org.vedanta.vidiyalay.student_service.repository.ComboMasterEntityRepository;
import org.vedanta.vidiyalay.student_service.service.QueryComboMasterDataService;

import java.util.List;

@Service
public class QueryComboMasterDataServiceImpl implements QueryComboMasterDataService {

  private final ComboMasterEntityRepository comboMasterEntityRepository;

  public QueryComboMasterDataServiceImpl(ComboMasterEntityRepository comboMasterEntityRepository) {
    this.comboMasterEntityRepository = comboMasterEntityRepository;
  }

  @Override
  public List<ComboMasterEntity> findAll(final ComboType comboType) {
    return comboMasterEntityRepository.findAllByComboNameOrderById(comboType.name());
  }
}
