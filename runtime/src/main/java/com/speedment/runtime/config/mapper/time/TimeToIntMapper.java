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

import com.speedment.runtime.annotation.Api;
import com.speedment.runtime.config.mapper.TypeMapper;

import java.sql.Time;

/**
 *
 * @author  Maria Sparenberg
 * @author  Patrick Hobusch
 */
@Api(version = "3.0")
public class TimeToIntMapper implements TypeMapper<Time, Integer> {

    @Override
    public Class<Integer> getJavaType() {
        return Integer.class;
    }

    @Override
    public Class<Time> getDatabaseType() {
        return Time.class;
    }

    @Override
    public Integer toJavaType(Time value) {
        return value == null ? null : (int) (value.getTime() / 1000);
    }

    @Override
    public Time toDatabaseType(Integer value) {
        return value == null ? null : new Time(value * 1000);
    }

    @Override
    public boolean isIdentityMapper() {
        return false;
    }

    @Override
    public boolean isApproximation() {
        return true;
    }
}
