package net.rendicahya.commons.utils;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;

public class ApplicationUtils {

    public static void restartApplication(Runnable runBeforeRestart) throws IOException {
        String java = System.getProperty("java.home") + "/bin/java";
        List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        StringBuffer vmArgsOneLine = new StringBuffer();

        for (String arg : vmArguments) {
            if (!arg.contains("-agentlib")) {
                vmArgsOneLine.append(arg);
                vmArgsOneLine.append(" ");
            }
        }

        final StringBuilder cmd = new StringBuilder("\"" + java + "\" " + vmArgsOneLine);

        String javaCommand = System.getProperty("sun.java.command");

        if (javaCommand.endsWith(".jar")) {
            cmd.append("-jar \"").append(javaCommand).append("\"");
        } else {
            cmd.append("-cp \"").append(System.getProperty("java.class.path")).append("\" ").append(javaCommand);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    Runtime.getRuntime().exec(cmd.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        if (runBeforeRestart != null) {
            runBeforeRestart.run();
        }

        System.exit(0);
    }
}
