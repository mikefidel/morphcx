package ndextools.morphcx.processors.csv.configuration;

import ndextools.morphcx.configuration.cli.Configuration;
import ndextools.morphcx.configuration.cli.ConfigurationAbstraction;

import java.util.Arrays;
import java.util.Optional;

/**
 * Implementation class for CSVConfiguration objects.
 */
public final class CSVConfiguration extends ConfigurationAbstraction implements Configuration {
    private final String fileType;
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
            String fileType,
            String newline)
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

        this.fileType = fileType;
        this.newline = newline;
    }

    /**
     * Getter method
     * @return character used to delimit individual fields, usually a tab or a comma character. Closely
     *      associated with file type extensions such as .csv or .tsv
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Getter method
     * @return the sequence of characters denoting a "new line" and which vary among OS platforms.
     */
    public String getNewline() {
        return newline;
    }

    /**
     * Overrides Object.toString()
     * @return CSVConfiguration object properties as a formatted string.
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
                ", delimiter=" + getFileType() +
                ", newline=" + getNewline() +
                '}';
    }

}