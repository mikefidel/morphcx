package ndextools.morphcx.processors.csv.configuration;

import ndextools.morphcx.configuration.cli.AbstractTemplate;
import ndextools.morphcx.configuration.cli.Builder;
import ndextools.morphcx.configuration.cli.Template;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Implementation class for creating CSV-type Configuration objects/
 */
public final class CSVTemplateImpl extends AbstractTemplate implements Template {
    private String fileType;
    private String newline;

    public CSVTemplateImpl(final String[] commandline, final String appName) {
        super(commandline, appName);
        this.fileType = CSVOptionConstants.TAB;
        this.newline = CSVOptionConstants.SYSTEM;
    }

    /**
     * This method is the 1st of 3 steps that Apache CLI uses to process command-line options. CSV-specific
     * options are defined here. Shared Configuration options are defined in the Template object superclass.
     *
     * @return an Options object containing valid options and associated parameters.
     */
    @Override
    public final Options defineExtendedOptions(final Options options) {
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

    /**
     * This method interrogates and resolves CSV-specific options found on the command-line
     * when the program is invoked.
     *
     * @return Builder object containing setter properties and methods.
     */
    @Override
    public final Builder resolveExtendedOptions(final CommandLine parsedCommandline, final Builder builder) {
        Builder build = processNewline((CSVBuilder) builder, parsedCommandline);
        processSeparator((CSVBuilder) builder, parsedCommandline);
        return build;
    }

    private Builder processNewline(CSVBuilder builder, CommandLine parsedCommandline) {

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

    private Builder processSeparator(CSVBuilder builder, CommandLine parsedCommandline) {
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