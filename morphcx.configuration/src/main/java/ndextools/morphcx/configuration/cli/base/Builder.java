package ndextools.morphcx.configuration.cli.base;

import java.util.Optional;

/**
 * Builder is an interface associated with Configuration objects. It defines:
 * - abstract setter methods shared by all Configuration types
 * - an abstract method used for instantiating Configuration objects
 * <p>
 * This interface may be extended by sub-interfaces having Configuration
 * type-specific properties.
 */
public interface Builder {

    Configuration getInstance();

    void setCommandline(String[] commandline);

    void setAppName(String appName);

    void setProcessId(String processId);

    void setIsDebugMode(boolean debugMode);

    void setShowHelpPrompt(boolean isShowHelpPrompt);

    void setUsesInputFile(boolean usesInputFile);

    void setUsesOutputFile(boolean usesOutputFile);

    void setInputFilename(Optional<String> inputFilename);

    void setOutputFilename(Optional<String> outputFilename);

    String toString();

}