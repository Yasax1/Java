/*
 * $Id: Login.java 440597 2006-09-06 03:34:39Z wsmoak $
 *
 * Copyright 2000-2004 Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mailreader2;

import org.apache.struts.apps.mailreader.dao.User;
import org.apache.struts.apps.mailreader.dao.ExpiredPasswordException;

/**
 * <p> Validate a user login. </p>
 */
public final class Login extends MailreaderSupport {

    public String execute() throws ExpiredPasswordException  {

        User user = findUser(getUsername(), getPassword());

        if (user != null) {
            setUser(user);
        }

        if (hasErrors()) {
            return INPUT;
        }

       return SUCCESS;

    }

}
