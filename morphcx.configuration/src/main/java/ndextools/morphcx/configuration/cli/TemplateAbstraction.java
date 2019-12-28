package ndextools.morphcx.configuration.cli;

import org.apache.commons.cli.*;

import java.lang.management.ManagementFactory;

/**
 * CLITemplate is an abstract class used for processing command-line options input
 * by the user when invoking the application from a command-line. It is based on
 * the software design Template pattern where a specific sequence of processing
 * steps must be followed, and whereby variations in these processing steps are
 * handled by concrete method implementations found in subclasses.
 * <p>
 * The processing order is tightly coupled to the Apache Commons CLI artifact,
 * information for which is found at https://commons.apache.org/proper/commons-cli/
 * <p>
 * It is used together with Builder objects which contain setter classes
 * to define Configuration object properties. The template method
 * configure(Builder builder)
 * provides the processing steps; it can not be overridden!
 * <p>
 * In addition to the template method, it also contains a number of helper methods.
 */
public abstract class TemplateAbstraction implements Template {
    protected final String[] commandline;
    protected final String appName;
    protected Options optionDefinitions;

    /**
     * Constructor
     * @param commandline command-line when application was invoked
     * @param appName class used to launch this application
     */
    public TemplateAbstraction(final String[] commandline, final String appName) {
        this.commandline = commandline;
        this.appName = appName;
    }

    public abstract Configuration configure(Builder bldr) throws ParseException;

    /**
     * This method is the 1st of 3 steps that Apache CLI uses to process command-line options.
     * All valid base options are defined here; a base option is a commandline option that can
     * be used at all times and circumstances. Additional options that are associated with a
     * specific process are extended options. Extended options cannot be used at all times
     * and circumstances -- they are unique to that particular process.
     *
     * @return an Options object containing valid options and associated parameters.
     */
    protected final Options defineBaseOptions() {
        Options options = new Options();

        options.addOption(
                Option.builder(CLIBaseOptionConstants.OPT_HELP)
                        .longOpt(CLIBaseOptionConstants.LONG_OPT_HELP)
                        .desc("Displays this help information.")
                        .build()
        );
        options.addOption(
                Option.builder(CLIBaseOptionConstants.OPT_DEBUG)
                        .longOpt(CLIBaseOptionConstants.LONG_OPT_DEBUG)
                        .desc("A flag used for debugging and development purposes. < -X | --debug >")
                        .build()
        );
        options.addOption(
                Option.builder(CLIBaseOptionConstants.OPT_INPUT)
                        .longOpt(CLIBaseOptionConstants.LONG_OPT_INPUT)
                        .hasArg()
                        .desc("Full input path and file specification. Default: input comes from STDIN rather than a file.")
                        .build()
        );
        options.addOption(
                Option.builder(CLIBaseOptionConstants.OPT_OUTPUT)
                        .longOpt(CLIBaseOptionConstants.LONG_OPT_OUTPUT)
                        .hasArg()
                        .desc("Full output path and file specification. Default: output sent to STDOUT rather than a file.")
                        .build()
        );

        return options;
    }

    /**
     * This method is the 2nd of 3 steps that Apache CLI uses to process command-line options. It takes as input
     * all basic and extended option definitions and the command-line used when the application was launched.  It
     * then produces an intermediary object from which to query the existence and values found on the command-line.
     *
     * @param options Options type defined in Apache Commons CLI artifact
     * @param cmdlineArgs command-line when application was invoked
     * @return Commandline intermediary object created by Apache Commons CLI from which to query the
     *         existence of defined options and their respective values
     * @throws ParseException type defined in Apache Commons CLI artifact
     */
    protected final CommandLine parseCommandline(final Options options, final String[] cmdlineArgs) throws ParseException {
        try {
            return new DefaultParser().parse(options, cmdlineArgs);
        } catch (ParseException e) {
            String errMsg = appName + ": " + e.getMessage();
            throw new ParseException(errMsg);
        }
    }

    /**
     * This method is the final step that Apache CLI uses to process command-line options. It accepts
     * the intermediary Commandline intermediary object created by Apache Commons CLI and uses a Builder subtype
     * to set Configuration properties that correspond to the options defined in step 1.
     *
     * @param parsedCommandline Commandline intermediary object created by Apache Commons CLI
     * @param builder Builder subclass used for setting Configuration properties
     * @return Updated Builder object used to create a Configuration object
     */
    protected final Builder resolveBaseOptions(final CommandLine parsedCommandline, final Builder builder) {
        processCommandline(builder);
        processAppName(builder);
        processPID(builder);
        processShowHelpFlag(builder, parsedCommandline);
        processDebugModeFlag(builder, parsedCommandline);
        processInputFilename(builder, parsedCommandline);
        processOutputFilename(builder, parsedCommandline);

        // TODO resolve other shared options

        return builder;
    }

    private void processDebugModeFlag(final Builder builder, final CommandLine parsedCommandline) {
        builder.setIsDebugMode(parsedCommandline.hasOption(CLIBaseOptionConstants.LONG_OPT_DEBUG));
    }

    private void processShowHelpFlag(final Builder builder, final CommandLine parsedCommandline) {
        if (parsedCommandline.hasOption((CLIBaseOptionConstants.LONG_OPT_HELP))) {
            builder.setShowHelpPrompt(true);
            printHelpText();
        } else {
            builder.setShowHelpPrompt(false);
        }
    }

    private void printHelpText() {
        String prefix = "java -jar " + appName + ".jar";
        String header = "where parameter options are:";
        String footer = "";
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(132, prefix, header, optionDefinitions, footer, true);
    }

    private void processAppName(final Builder builder) {
        builder.setAppName(appName);
    }

    private void processPID(final Builder builder) {
        String pid = Long.toString(ManagementFactory.getRuntimeMXBean().getPid());
        builder.setProcessId(pid);
    }

    private void processCommandline(final Builder builder) {
        builder.setCommandline(commandline);
    }

    private void processInputFilename(final Builder builder, final CommandLine parsedCommandline) {
        if (parsedCommandline.hasOption(CLIBaseOptionConstants.OPT_INPUT)) {
            builder.setUsesInputFile(true);
            builder.setInputFilename(java.util.Optional.ofNullable(parsedCommandline.getOptionValue(CLIBaseOptionConstants.OPT_INPUT)));
        } else {
            builder.setInputFilename(java.util.Optional.empty());
        }
    }

    private void processOutputFilename(final Builder builder, final CommandLine parsedCommandline) {
        if (parsedCommandline.hasOption(CLIBaseOptionConstants.OPT_OUTPUT)) {
            builder.setUsesOutputFile(true);
            builder.setOutputFilename(java.util.Optional.ofNullable(parsedCommandline.getOptionValue(CLIBaseOptionConstants.OPT_OUTPUT)));
        } else {
            builder.setOutputFilename(java.util.Optional.empty());
        }
    }

    /**
     * Constants shared when processing all command-line options
     */
    public static class CLIBaseOptionConstants {

        public static final String OPT_HELP = "h";
        public static final String LONG_OPT_HELP = "help";

        public static final String OPT_DEBUG = "X";
        public static final String LONG_OPT_DEBUG = "debug";

        public static final String OPT_INPUT = "i";
        public static final String LONG_OPT_INPUT =  "input";

        public static final String OPT_OUTPUT = "o";
        public static final String LONG_OPT_OUTPUT = "output";

    }

}