/*
 * $Id: SkillAction.java 420385 2006-07-10 00:57:05Z mrdon $
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
package org.apache.struts2.showcase.action;

import org.apache.struts2.showcase.dao.Dao;
import org.apache.struts2.showcase.dao.SkillDao;
import org.apache.struts2.showcase.model.Skill;
import com.opensymphony.xwork2.Preparable;
import org.apache.log4j.Logger;

/**
 * SkillAction.
 *
 */

public class SkillAction extends AbstractCRUDAction implements Preparable {

    private static final Logger log = Logger.getLogger(SkillAction.class);

    private String skillName;
    protected SkillDao skillDao;
    private Skill currentSkill;

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    protected Dao getDao() {
        return skillDao;
    }

    public void setSkillDao(SkillDao skillDao) {
        if (log.isDebugEnabled()) {
            log.debug("SkillAction - [setSkillDao]: skillDao injected.");
        }
        this.skillDao = skillDao;
    }

    public Skill getCurrentSkill() {
        return currentSkill;
    }

    public void setCurrentSkill(Skill currentSkill) {
        this.currentSkill = currentSkill;
    }

    /**
     * This method is called to allow the action to prepare itself.
     *
     * @throws Exception thrown if a system level exception occurs.
     */
    public void prepare() throws Exception {
        Skill preFetched = (Skill) fetch(getSkillName(), getCurrentSkill());
        if (preFetched != null) {
            setCurrentSkill(preFetched);
        }
    }

    public String save() throws Exception {
        if (getCurrentSkill() != null) {
            setSkillName((String) skillDao.merge(getCurrentSkill()));
        }
        return SUCCESS;
    }

}
