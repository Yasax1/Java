/*
 * $Id: Storage.java 418530 2006-07-01 23:58:13Z mrdon $
 *
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts2.showcase.application;

import org.apache.struts2.showcase.model.IdEntity;
import org.apache.struts2.showcase.exception.CreateException;
import org.apache.struts2.showcase.exception.UpdateException;
import org.apache.struts2.showcase.exception.StorageException;

import java.io.Serializable;
import java.util.Collection;

/**
 * Storage. Interface.
 *
 */

public interface Storage extends Serializable {
    IdEntity get( Class entityClass, Serializable id );

    Serializable create ( IdEntity object ) throws CreateException;

    IdEntity update ( IdEntity object ) throws UpdateException;

    Serializable merge ( IdEntity object ) throws StorageException;

    int delete( Class entityClass, Serializable id ) throws CreateException;

    int delete( IdEntity object ) throws CreateException;

    Collection findAll( Class entityClass );
}
