package ndextools.morphcx.configuration.cli;

import java.util.Optional;

/**
 * Configuration objects are immutable and possess the configuration properties
 * required by the application. The getter methods defined in this interface
 * specify these properties.
 */
public interface Configuration {

    String[] getCommandline();

    String getAppName();

    String getProcessId();

    boolean isDebugMode();

    boolean isShowHelpPrompt();

    boolean usesInputFile();

    boolean usesOutputFile();

    Optional<String> getInputFilename();

    Optional<String> getOutputFilename();

    String toString();

}