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

package org.vedanta.vidiyalay.utils.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class SpringSecurityAuditorAware  {
    @Autowired
    HttpServletRequest request;
    @Bean
    AuditorAware<String> auditorProvider () {
        return () -> {
            final Optional<String> s = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                    .map(e -> (ServletRequestAttributes) e)
                    .map(ServletRequestAttributes::getRequest)
                    .map(e -> e.getHeader("user-info"))
                    .map(String::valueOf);

            if(s.isPresent()) {
                return s;
            }else {
                return Optional.of("System");
            }
        };
    }

}

