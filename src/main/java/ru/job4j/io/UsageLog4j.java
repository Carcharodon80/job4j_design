package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1. Log4j. Логирование системы. [#95335]
 * 2. Simple Loggin Facade 4 Java. [#268849]
 * 3. Slf4j - вывод переменных. [#268852]
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte first = 1;
        short second = 2000;
        int third = 33;
        long fourth = 44L;
        float fifth = 55.5f;
        double sixth = 66.6;
        char seventh = '7';
        boolean eighth = true;

        LOG.debug("User info one : {}, two : {}, three : {}, four : {}, "
                + "five : {}, six : {}, seven : {}, eight : {}", first, second,
                third, fourth, fifth, sixth, seventh, eighth);
    }
}
