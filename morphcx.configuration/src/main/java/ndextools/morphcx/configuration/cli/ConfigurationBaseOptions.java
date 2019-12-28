package ndextools.morphcx.configuration.cli;

import java.util.Arrays;
import java.util.Optional;

/**
 * Implementation class for instantiating a base options type configuration object.
 */
public class ConfigurationBaseOptions extends ConfigurationAbstraction {

    public ConfigurationBaseOptions(
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
     * @return Configuration object properties as a formatted string
     */
    @Override
    public String toString() {
        return "ConfigurationBaseOptions{" +
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