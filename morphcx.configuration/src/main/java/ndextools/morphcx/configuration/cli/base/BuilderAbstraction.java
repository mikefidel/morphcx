package ndextools.morphcx.configuration.cli.base;

import java.util.Optional;

/**
 * AbstractBuilder is an abstract class associated with all Configuration objects. It defines:
 * - concrete setter methods for properties that are shared by all Configuration types
 * - setter abstract methods unique to specific Configuration types
 * - an abstract method used for instantiating Configuration objects
 * - an abstract toString() method definition
 * <p>
 * It will be extended by sub-interfaces having Configuration type-specific properties and methods.
 * The setter methods herein are called by methods in Template implementations.
 */
public abstract class BuilderAbstraction implements Builder {
    protected String[] commandline;
    protected String appName;
    protected String processId;
    protected boolean flagIsDebugMode;
    protected boolean flagShowHelpPrompt;
    protected boolean flagUsesInputFile;
    protected boolean flagUsesOutputFile;
    protected Optional<String> inputFilename;
    protected Optional<String> outputFilename;

    public abstract Configuration getInstance();

    public void setCommandline(String[] commandline) {
        this.commandline = commandline;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setProcessId(String pid) {
        this.processId = pid;
    }

    public void setIsDebugMode(boolean debugMode) {
        this.flagIsDebugMode = debugMode;
    }

    public void setShowHelpPrompt(boolean isShowHelpPrompt) {
        this.flagShowHelpPrompt = isShowHelpPrompt;
    }

    public void setUsesInputFile(boolean usesInputFile) {
        this.flagUsesInputFile = usesInputFile;
    }

    public void setUsesOutputFile(boolean usesOutputFile) {
        this.flagUsesOutputFile = usesOutputFile;
    }

    public void setInputFilename(Optional<String> filename) {
        this.inputFilename = filename;
    }

    public void setOutputFilename(Optional<String> filename) {
        this.outputFilename = filename;
    }

}