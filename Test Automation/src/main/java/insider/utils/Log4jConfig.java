package insider.utils;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log4jConfig {
    public static void configureLogger() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String logFileName = "logs/insider-automation_ui-test_" + date + ".log";

        PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");

        try {
            FileAppender fileAppender = new FileAppender(layout, logFileName, true);
            Logger.getRootLogger().addAppender(fileAppender);
            Logger.getRootLogger().info("Logger yapılandırıldı. Log dosyası: " + logFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
