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
public abstract class AbstractTemplate implements Template {
    private final String[] commandline;
    private final String appName;
    private Options optionDefinitions;

    public AbstractTemplate(final String[] commandline, final String appName) {
        this.commandline = commandline;
        this.appName = appName;
    }

    /**
     * This is the template method for creating Apache CLI-based Configuration objects.
     *
     * @param builder Builder object that contains all setter methods for the Configuration type being
     *                built, as well as methods involved in determining its property values.
     * @return resulting immutable Configuration object
     * @throws ParseException when an invalid option is found in the command-line passed when the
     *                        application is invoked.
     */
    public final Configuration configure(Builder builder) throws ParseException {
        CommandLine parsedCommandline;

        optionDefinitions = defineSharedOptions();
        optionDefinitions = defineExtendedOptions(optionDefinitions);

        parsedCommandline = parseCommandline(optionDefinitions, commandline);

        builder = resolveSharedOptions(parsedCommandline, builder);
        builder = resolveExtendedOptions(parsedCommandline, builder);
        return builder.getInstance();
    }

    /**
     * This method is the 1st of 3 steps that Apache CLI uses to process command-line options.
     * All valid shared options are defined here; a shared option is an option that can be used
     * in all invoking command-lines. Extended options are defined in Template subclasses
     * associated with that type.
     *
     * @return an Options object containing valid options and associated parameters.
     */
    private Options defineSharedOptions() {
        Options options = new Options();

        options.addOption(
                Option.builder(SharedCLIOptionConstants.OPT_HELP)
                        .longOpt(SharedCLIOptionConstants.LONG_OPT_HELP)
                        .desc("Displays this help information.")
                        .build()
        );
        options.addOption(
                Option.builder(SharedCLIOptionConstants.OPT_DEBUG)
                        .longOpt(SharedCLIOptionConstants.LONG_OPT_DEBUG)
                        .desc("A flag used for debugging and development purposes. < -X | --debug >")
                        .build()
        );
        options.addOption(
                Option.builder(SharedCLIOptionConstants.OPT_INPUT)
                        .longOpt(SharedCLIOptionConstants.LONG_OPT_INPUT)
                        .hasArg()
                        .desc("Full input path and file specification. Default: input comes from STDIN rather than a file.")
                        .build()
        );
        options.addOption(
                Option.builder(SharedCLIOptionConstants.OPT_OUTPUT)
                        .longOpt(SharedCLIOptionConstants.LONG_OPT_OUTPUT)
                        .hasArg()
                        .desc("Full output path and file specification. Default: output sent to STDOUT rather than a file.")
                        .build()
        );

        return options;
    }

    private CommandLine parseCommandline(final Options options, final String[] cmdline) throws ParseException {

        // Apache Commons CLI - Step #2 of 3: Parse all command-line parameters
        CommandLine parsed;
        CommandLineParser parser = new DefaultParser();

        try {
            parsed = parser.parse(options, cmdline);
        } catch (ParseException e) {
//            String errMsg = this.getClass().getSimpleName() + ": " + e.getMessage();
            String errMsg = appName + ": " + e.getMessage();
            throw new ParseException(errMsg);
        }

        return parsed;
    }

    private Builder resolveSharedOptions(final CommandLine parsedCommandline, final Builder builder) {
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
        builder.setIsDebugMode(parsedCommandline.hasOption(SharedCLIOptionConstants.LONG_OPT_DEBUG));
    }

    private void processShowHelpFlag(final Builder builder, final CommandLine parsedCommandline) {
        if (parsedCommandline.hasOption((SharedCLIOptionConstants.LONG_OPT_HELP))) {
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
        if (parsedCommandline.hasOption(SharedCLIOptionConstants.OPT_INPUT)) {
            builder.setUsesInputFile(true);
            builder.setInputFilename(java.util.Optional.ofNullable(parsedCommandline.getOptionValue(SharedCLIOptionConstants.OPT_INPUT)));
        } else {
            builder.setInputFilename(java.util.Optional.empty());
        }
    }

    private void processOutputFilename(final Builder builder, final CommandLine parsedCommandline) {
        if (parsedCommandline.hasOption(SharedCLIOptionConstants.OPT_OUTPUT)) {
            builder.setUsesOutputFile(true);
            builder.setOutputFilename(java.util.Optional.ofNullable(parsedCommandline.getOptionValue(SharedCLIOptionConstants.OPT_OUTPUT)));
        } else {
            builder.setOutputFilename(java.util.Optional.empty());
        }
    }

    /**
     * Constants shared when processing all command-line options
     */
    public static class SharedCLIOptionConstants {

        public static final String OPT_HELP = "h";
        public static final String LONG_OPT_HELP = "help";

        public static final String OPT_DEBUG = "X";
        public static final String LONG_OPT_DEBUG = "debug";

        public static final String OPT_INPUT = "i";
        public static final String LONG_OPT_INPUT =  "input";

        public static final String OPT_OUTPUT = "o";
        public static final String LONG_OPT_OUTPUT = "output";

    }

    protected abstract Builder resolveExtendedOptions(CommandLine parsedCommandline, Builder builder);

}