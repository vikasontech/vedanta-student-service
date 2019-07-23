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

package org.vedanta.vidiyalay.student_service.account_service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vedanta.vidiyalay.student_service.account_service.AccountServiceHelper;
import org.vedanta.vidiyalay.student_service.config.ApplicationConfig;
import org.vedanta.vidiyalay.student_service.web.rest.vm.AccountMasterVM;
import org.vedanta.vidiyalay.utils.BasicResponse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceHelperImpl implements AccountServiceHelper {
    private final RestTemplate restTemplate;
    private final ApplicationConfig applicationConfig;
    AccountServiceHelperImpl(RestTemplate restTemplate, ApplicationConfig applicationConfig) {
        this.restTemplate = restTemplate;
        this.applicationConfig = applicationConfig;
    }

    @Override
    public List<AccountMasterVM> findAllThatHaveFeeDue() {
        final ApplicationConfig.Path path = applicationConfig.getServices().get("account-service");
        final String queryAllAccountDataUri = path.getPath() + path.getUris().get("QUERY_ALL_ACCOUNT_DATA");

        ParameterizedTypeReference<BasicResponse<List<AccountMasterVM>>> typeReference
                = new ParameterizedTypeReference<BasicResponse<List<AccountMasterVM>>>() {};

        ResponseEntity<BasicResponse<List<AccountMasterVM>>> responseEntity = restTemplate.exchange(
                queryAllAccountDataUri, HttpMethod.GET, null, typeReference);

        if(!(isSuccess(responseEntity.getStatusCode()))){
            log.error("Cannot get student account data : {}",responseEntity.getStatusCode() +":"+
                    Optional.ofNullable(responseEntity.getBody()).map(BasicResponse::getMessage).orElse(Strings.EMPTY));
            return Collections.emptyList();
        }

        return responseEntity.getBody().getData().get(0);
    }

    private boolean isSuccess(HttpStatus statusCode) {
        return statusCode == HttpStatus.OK;
    }
}

@Configuration
class WebConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
