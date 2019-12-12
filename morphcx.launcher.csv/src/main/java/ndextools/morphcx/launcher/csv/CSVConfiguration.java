package ndextools.morphcx.launcher.csv;

import ndextools.morphcx.configuration.cli.AbstractConfiguration;

import java.util.Arrays;
import java.util.Optional;

/**
 * CSVConfiguration is the implementation class for the application configuration object.
 */
public final class CSVConfiguration extends AbstractConfiguration {
    private final char delimiter;
    private final String newline;

    public CSVConfiguration(
            String[] commandline,
            String appName,
            String processId,
            boolean flagIsDebugMode,
            boolean flagShowHelpPrompt,
            boolean flagUsesInputFile,
            boolean flagUsesOutputFile,
            Optional<String> inputFilename,
            Optional<String> outputFilename,
            char delimiter,
            String newline) {
        super(commandline,
                appName,
                processId,
                flagIsDebugMode,
                flagShowHelpPrompt,
                flagUsesInputFile,
                flagUsesOutputFile,
                inputFilename,
                outputFilename);

        this.delimiter = delimiter;
        this.newline = newline;
    }


    /**
     * Getter method
     *
     * @return character used to delimit individual fields, usually a tab or a comma character.
     */
    public char getDelimiter() {
        return delimiter;
    }


    /**
     * Getter method
     *
     * @return the sequence of characters denoting a "new line" and which vary among OS platforms.
     */
    public String getNewline() {
        return newline;
    }

    /**
     * Overrides Object.toString()
     *
     * @return state of the CSVConfiguration object properties as a formatted string.
     */
    @Override
    public String toString() {
        return "CSVConfiguration{" +
                "commandline=" + Arrays.toString(getCommandline()) +
                ", appName=" + getAppName() +
                ", processId=" + getProcessId() +
                ", flagIsDebugMode=" + isDebugMode() +
                ", flagShowHelpPrompt=" + isShowHelpPrompt() +
                ", flagUsesInputFile=" + usesInputFile() +
                ", flagUsesOutputFile=" + usesOutputFile() +
                ", inputFilename=" + getInputFilename() +
                ", outputFilename=" + getOutputFilename() +
                ", delimiter=" + getDelimiter() +
                ", newline=" + getNewline() +
                '}';
    }

}