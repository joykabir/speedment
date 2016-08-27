/**
 *
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.runtime.config.mapper.time;

import com.speedment.runtime.config.Column;
import com.speedment.runtime.config.mapper.TypeMapper;
import java.lang.reflect.Type;
import java.sql.Time;
import java.time.LocalTime;

/**
 * A mapping from SQL's Time to Java's LocalTime.
 * <p>
 * The mapping is naive, and will not include or consider timezone or the similar.
 * Instead, the mapping will be direct: what is written in the database will
 * be directly mapped into Java.
 * <p>
 * Example: <br>
 * In database: 19:22:10<br>
 * In Java: 19:22:10
 *
 * @author Simon Jonasson
 */
public class TimeToLocalTimeMapper implements TypeMapper<Time, LocalTime> {

    @Override
    public String getLabel() {
        return "Time to LocalTime";
    }

    @Override
    public Type getJavaType(Column column) {
        return LocalTime.class;
    }

    @Override
    public LocalTime toJavaType(Column column, Class<?> entityType, Time value) {
        return value.toLocalTime();
    }

    @Override
    public Time toDatabaseType(LocalTime value) {
        return Time.valueOf(value);
    }

}