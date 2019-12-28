package ndextools.morphcx.configuration.cli;

import java.util.Optional;

/**
 * AbstractConfiguration is a superclass object which type-specific configuration subclasses
 * may extend with additional properties and getter methods.  All properties defined
 * in this superclass are mandatory and shared among all the other configuration subtypes.
 *
 * Restricting its public interface to getter methods insures it's properties are immutable.
 */
public abstract class ConfigurationAbstraction implements Configuration {
    protected final String[] commandline;
    protected final String appName;
    protected final String processId;
    protected final boolean flagIsDebugMode;
    protected final boolean flagShowHelpPrompt;
    protected final boolean flagUsesInputFile;
    protected final boolean flagUsesOutputFile;
    protected final Optional<String> inputFilename;
    protected final Optional<String> outputFilename;

    /**
     * Superclass Constructor
     *
     * @param commandline command-line options specified when the executable program was invoked
     * @param appName class name of invoking executable program
     * @param processId process id (pid) of invoking executable program
     * @param flagIsDebugMode a flag denoting whether the application is running in debug mode
     * @param flagShowHelpPrompt a flag indicating whether help text is to be displayed
     * @param flagUsesInputFile a flag denoting whether the input is a file (otherwise StdIn)
     * @param flagUsesOutputFile a flag denoting whether the output is a file (otherwise StdOut)
     * @param inputFilename the complete input file specification (path + filename + extension)
     * @param outputFilename the complete output file specification (path + filename + extension)
     */
    public ConfigurationAbstraction(
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
        this.commandline = commandline;
        this.appName = appName;
        this.processId = processId;
        this.flagIsDebugMode = flagIsDebugMode;
        this.flagShowHelpPrompt = flagShowHelpPrompt;
        this.flagUsesInputFile = flagUsesInputFile;
        this.flagUsesOutputFile = flagUsesOutputFile;
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
    }

    /**
     * Getter method
     * @return command-line options specified when the executable program was invoked.
     */
    public String[] getCommandline() {
        return commandline;
    }

    /**
     * Getter method
     * @return class name of invoking executable program.
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Getter method
     * @return process id (pid) of invoking executable program.
     */
    public String getProcessId() {
        return processId;
    }

    /**
     * Getter method
     * @return a flag denoting whether the application is running in debug mode.
     */
    public boolean isDebugMode() {
        return flagIsDebugMode;
    }

    /**
     * Getter method
     * @return a flag indicating whether help text is to be displayed.
     */
    public boolean isShowHelpPrompt() {
        return flagShowHelpPrompt;
    }

    /**
     * Getter method
     * @return a flag denoting whether the input is a file (otherwise StdIn).
     */
    public boolean usesInputFile() {
        return flagUsesInputFile;
    }

    /**
     * Getter method
     * @return a flag denoting whether the output is a file (otherwise StdOut).
     */
    public boolean usesOutputFile() {
        return flagUsesOutputFile;
    }

    /**
     * Getter method
     * @return the complete input file specification (path + filename + extension).
     */
    public Optional<String> getInputFilename() {
        return Optional.ofNullable(inputFilename).orElse(Optional.of(""));
    }

    /**
     * Getter method
     * @return the complete output file specification (path + filename + extension).
     */
    public Optional<String> getOutputFilename() {
        return Optional.ofNullable(outputFilename).orElse(Optional.of(""));
    }
    /**
     * Abstract getter method inherited from Object toString().
     */
    public abstract String toString();

}