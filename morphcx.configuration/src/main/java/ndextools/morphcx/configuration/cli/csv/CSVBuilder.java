package ndextools.morphcx.configuration.cli.csv;

import ndextools.morphcx.configuration.cli.base.BuilderAbstraction;
import ndextools.morphcx.configuration.cli.base.ConfigurationAbstraction;
import ndextools.morphcx.configuration.cli.base.Builder;

import java.util.Arrays;

/**
 * Implementation class for CSVBuilder objects.
 */
public final class CSVBuilder extends BuilderAbstraction implements Builder {
    private String fileType;
    private String newline;

    @Override
    public ConfigurationAbstraction getInstance() {
        return new CSVConfiguration(
                commandline,
                appName,
                processId,
                flagIsDebugMode,
                flagShowHelpPrompt,
                flagUsesInputFile,
                flagUsesOutputFile,
                inputFilename,
                outputFilename,
                fileType,
                newline
        );
    }

    @Override
    public String toString() {
        return "CSVBuilder" +
                "commandline=" + Arrays.toString(commandline) +
                ", appName=" + appName +
                ", processId=" + processId +
                ", flagIsDebugMode=" + flagIsDebugMode +
                ", flagShowHelpPrompt=" + flagShowHelpPrompt +
                ", flagUsesInputFile=" + flagUsesInputFile +
                ", flagUsesOutputFile=" + flagUsesOutputFile +
                ", inputFilename=" + inputFilename +
                ", outputFilename=" + outputFilename +
                ", fileType=" + fileType +
                ", newline=" + newline +
                '}';
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setNewline(String newline) {
        this.newline = newline;
    }

}