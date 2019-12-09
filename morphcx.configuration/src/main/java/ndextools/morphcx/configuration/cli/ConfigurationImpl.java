package ndextools.morphcx.configuration.cli;

import java.util.Arrays;
import java.util.Optional;

/**
 * ConfigurationImpl is the implementation class for a default configuration object.
 */
public class ConfigurationImpl extends AbstractConfiguration {

    public ConfigurationImpl(
            String[] commandline,
            String appName,
            String processId,
            boolean flagIsDebugMode,
            boolean flagShowHelpPrompt,
            boolean flagUsesInputFile,
            boolean flagUsesOutputFile,
            Optional<String> inputFilename,
            Optional<String> outputFilename)
    {
        super(commandline,
                appName,
                processId,
                flagIsDebugMode,
                flagShowHelpPrompt,
                flagUsesInputFile,
                flagUsesOutputFile,
                inputFilename,
                outputFilename);
    }

    /**
     * Overrides Object.toString()
     * @return state of the SimpleConfiguration object properties as a formatted string.
     */
    @Override
    public String toString() {
        return "ConfigurationImpl{" +
                "commandline=" + Arrays.toString(getCommandline()) +
                ", appName=" + getAppName() +
                ", processId=" + getProcessId() +
                ", flagIsDebugMode=" + isDebugMode() +
                ", flagShowHelpPrompt=" + isShowHelpPrompt() +
                ", flagUsesInputFile=" + usesInputFile() +
                ", flagUsesOutputFile=" + usesOutputFile() +
                ", inputFilename=" + getInputFilename() +
                ", outputFilename=" + getOutputFilename() +
                '}';
    }

}