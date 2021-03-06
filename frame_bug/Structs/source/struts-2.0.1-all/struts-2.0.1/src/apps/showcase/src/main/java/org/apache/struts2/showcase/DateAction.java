/*
 * $Id: DateAction.java 420385 2006-07-10 00:57:05Z mrdon $
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
package org.apache.struts2.showcase;

import com.opensymphony.xwork2.ActionSupport;

import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <code>DateAction</code>
 *
 */
public class DateAction extends ActionSupport {

    private static DateFormat DF = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);

    private Date now;
    private Date past;
    private Date future;
    private Date after;
    private Date before;


    public String getDate() {
        return DF.format(new Date());
    }


    /**
     * @return Returns the future.
     */
    public Date getFuture() {
        return future;
    }

    /**
     * @return Returns the now.
     */
    public Date getNow() {
        return now;
    }

    /**
     * @return Returns the past.
     */
    public Date getPast() {
        return past;
    }

    /**
     *
     * @return Returns the before date.
     */
    public Date getBefore() {
        return before;
    }

    /**
     *
     * @return Returns the after date.
     */
    public Date getAfter() {
        return after;
    }

    /**
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute() throws Exception {
        Calendar cal = GregorianCalendar.getInstance();
        now = cal.getTime();
        cal.roll(Calendar.DATE, -1);
        cal.roll(Calendar.HOUR, -3);
        past = cal.getTime();
        cal.roll(Calendar.DATE, 2);
        future = cal.getTime();

        cal.roll(Calendar.YEAR, -1);
        before = cal.getTime();

        cal.roll(Calendar.YEAR, 2);
        after = cal.getTime();
        return SUCCESS;
    }

}
