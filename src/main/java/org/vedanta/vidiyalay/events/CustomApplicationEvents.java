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

package org.vedanta.vidiyalay.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CustomApplicationEvents extends ApplicationEvent {
    private final EventType eventType;

    public CustomApplicationEvents(Object source, EventType eventType) {
        super(source);
        this.eventType = eventType;
    }

    public <T> T getSourceFromEvent(EventType eventType, Class<T> type) {

        if (this.eventType != eventType) {
            return null;
        }
        final Object source = getSource();
        return (type.isInstance(source)) ? type.cast(source): null;
    }
}

