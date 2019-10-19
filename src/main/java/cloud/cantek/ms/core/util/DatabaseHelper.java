package cloud.cantek.ms.core.util;

import java.util.UUID;

/**
 * @author Emre Sen, 07.08.2019
 * @contact maemresen07@gmail.com
 */
public class DatabaseHelper {


    public static String randomUuidGenerator() {
        return UUID.randomUUID().toString();
    }

}
