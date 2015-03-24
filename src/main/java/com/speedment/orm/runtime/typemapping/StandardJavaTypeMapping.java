/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
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
package com.speedment.orm.runtime.typemapping;

import com.speedment.orm.config.model.Dbms;
import java.sql.Time;

/**
 *
 * @author pemi
 */
public enum StandardJavaTypeMapping implements JavaTypeMapping {

    INTEGER(Integer.class, "Int"),
    STRING(String.class, "String"),
    LONG(Long.class, "Long"),
    TIME(Time.class, "Time");

    private StandardJavaTypeMapping(Class<?> clazz, String resultSetMethodName) {
        this.clazz = clazz;
        this.resultSetMethodName = resultSetMethodName;
    }

    private final Class<?> clazz;
    private final String resultSetMethodName;

    @Override
    public Class<?> getJavaClass() {
        return clazz;
    }

    @Override
    public String getResultSetMethodName(Dbms dbms) {
        return resultSetMethodName;
    }

}
