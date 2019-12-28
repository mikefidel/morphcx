package ndextools.morphcx.configuration.cli.csv;

import ndextools.morphcx.configuration.cli.base.Builder;
import ndextools.morphcx.configuration.cli.base.Configuration;
import ndextools.morphcx.configuration.cli.base.Template;
import ndextools.morphcx.configuration.cli.base.TemplateAbstraction;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Implementation class for creating CSV-type Configuration objects.
 */
public final class CSVTemplate extends TemplateAbstraction implements Template {
    private String fileType;
    private String newline;

    /**
     * Constructor
     * @param commandline command-line when application was invoked
     * @param appName class used to launch this application
     */
    public CSVTemplate(final String[] commandline, final String appName) {
        super(commandline, appName);
        this.fileType = CSVOptionConstants.TAB;
        this.newline = CSVOptionConstants.SYSTEM;
    }

    @Override
    public Configuration configure(Builder bldr) throws ParseException {

        // Apache Commons CLI Step 1
        optionDefinitions = defineBaseOptions();
        optionDefinitions = defineExtendedOptions(optionDefinitions);

        // Apache Commons CLI Step 2
        CommandLine parsedCommandline = parseCommandline(optionDefinitions, commandline);

        // Apache Commons CLI Step 3
        bldr = resolveBaseOptions(parsedCommandline, bldr);
        Builder builder = resolveExtendedOptions(parsedCommandline, (CSVBuilder) bldr);

        // Create instance of Configuration
        return builder.getInstance();
    }

    public Options defineExtendedOptions(final Options options) throws ParseException {
        options.addOption(
                Option.builder(CSVOptionConstants.OPT_NEWLINE)
                        .longOpt(CSVOptionConstants.LONG_OPT_NEWLINE)
                        .hasArg()
                        .desc("Platform-dependent newline characters. < WINDOWS | LINUX | OSX | MAC | SYSTEM > Default: SYSTEM.")
                        .build()
        );

        options.addOption(
                Option.builder(CSVOptionConstants.OPT_FILETYPE)
                        .longOpt(CSVOptionConstants.LONG_OPT_FILETYPE)
                        .hasArg()
                        .desc("Output file type. < CSV | TSV >  Default: 'TSV'.")
                        .build()
        );

        return options;
    }

    public CSVBuilder resolveExtendedOptions(CommandLine parsedCommandline, CSVBuilder bldr) {
        bldr = processNewlineX(bldr, parsedCommandline);
        bldr = processSeparatorX(bldr, parsedCommandline);
        return bldr;
    }

    private CSVBuilder processNewlineX(CSVBuilder builder, CommandLine parsedCommandline) {
        if (parsedCommandline.hasOption(CSVOptionConstants.OPT_NEWLINE)) {
            String nl = parsedCommandline.getOptionValue(CSVOptionConstants.OPT_NEWLINE).toUpperCase();
            switch (nl) {
                case CSVOptionConstants.WINDOWS:
                    builder.setNewline(CSVOptionConstants.WINDOWS);
                    break;
                case CSVOptionConstants.LINUX:
                    builder.setNewline(CSVOptionConstants.LINUX);
                    break;
                case CSVOptionConstants.OSX:
                    builder.setNewline(CSVOptionConstants.OSX);
                    break;
                case CSVOptionConstants.MAC:
                    builder.setNewline(CSVOptionConstants.MAC);
                    break;
                case CSVOptionConstants.SYSTEM:
                    builder.setNewline(CSVOptionConstants.SYSTEM);
                default:
                    builder.setNewline(CSVOptionConstants.SYSTEM);
                    break;
            }
        } else {
            builder.setNewline(CSVOptionConstants.SYSTEM);
        }

        return builder;
    }

    private CSVBuilder processSeparatorX(CSVBuilder builder, CommandLine parsedCommandline) {
        if (parsedCommandline.hasOption(CSVOptionConstants.OPT_FILETYPE)) {
            String ft = parsedCommandline.getOptionValue(CSVOptionConstants.OPT_FILETYPE).toUpperCase();
            switch (ft) {
                case CSVOptionConstants.TAB:
                    builder.setFileType(CSVOptionConstants.TAB);
                    break;
                case CSVOptionConstants.COMMA:
                    builder.setFileType(CSVOptionConstants.COMMA);
                    break;
            }
        } else {
            builder.setFileType(CSVOptionConstants.TAB);
        }

        return builder;
    }

    /**
     * Constants unique to processing CSV command-line options
     */
    public static class CSVOptionConstants {

        public static final String OPT_NEWLINE = "n";
        public static final String LONG_OPT_NEWLINE = "newline";
        public static final String OPT_FILETYPE = "t";
        public static final String LONG_OPT_FILETYPE = "filetype";

        public static final String WINDOWS = "WINDOWS";
        public static final String LINUX = "LINUX";
        public static final String OSX = "OSX";
        public static final String MAC = "MAC";
        public static final String SYSTEM = "SYSTEM";
        public static final String TAB = "TAB";
        public static final String COMMA = "COMMA";

//        public static final char ESCAPE_T = '\t';
//        public static final char ESCAPE_COMMA = ',';
//        public static final String ESCAPE_N = "\n";
//        public static final String ESCAPE_R = "\r";
//        public static final String ESCAPE_R_ESCAPE_N = "\r\n";
    }
}
