package ndextools.morphcx.configuration.cli.base;

import java.util.Arrays;

/**
 * BuilderImpl is an implementation class for a default configuration object.
 */
public class BuilderBaseOptions extends BuilderAbstraction {

    @Override
    public final Configuration getInstance() {
        return new ConfigurationBaseOptions(
                commandline,
                appName,
                processId,
                flagIsDebugMode,
                flagShowHelpPrompt,
                flagUsesInputFile,
                flagUsesOutputFile,
                inputFilename,
                outputFilename);
    }

    @Override
    public final String toString() {
        return "BuilderImpl{" +
                "commandline=" + Arrays.toString(commandline) +
                ", appName=" + appName +
                ", processId=" + processId +
                ", flagIsDebugMode=" + flagIsDebugMode +
                ", flagShowHelpPrompt=" + flagShowHelpPrompt +
                ", flagUsesInputFile=" + flagUsesInputFile +
                ", flagUsesOutputFile=" + flagUsesOutputFile +
                ", inputFilename=" + inputFilename +
                ", outputFilename=" + outputFilename +
                '}';
    }

}