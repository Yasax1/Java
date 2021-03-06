/*
 * $Id: SkillDao.java 418530 2006-07-01 23:58:13Z mrdon $
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
package org.apache.struts2.showcase.dao;

import org.apache.struts2.showcase.model.Skill;

/**
 * SkillDao.
 *
 */

public class SkillDao extends AbstractDao {

	private static final long serialVersionUID = -8160406514074630866L;

    public Class getFeaturedClass() {
        return Skill.class;
    }

    public Skill getSkill( String name ) {
        return (Skill) get(name);
    }
}
