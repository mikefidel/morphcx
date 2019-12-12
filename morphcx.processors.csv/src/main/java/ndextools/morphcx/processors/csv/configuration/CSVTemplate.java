package ndextools.morphcx.processors.csv.configuration;

import ndextools.morphcx.configuration.cli.AbstractTemplate;
import ndextools.morphcx.configuration.cli.Builder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public final class CSVTemplate extends AbstractTemplate {
    private char delimiter;
    private String newline;

    public CSVTemplate(final String[] commandline, final String appName) {
        super(commandline, appName);
        this.delimiter = CSVOptionConstants.TAB;
        this.newline = System.getProperty("line.separator");
    }

    /**
     * This method is the 1st of 3 steps that Apache CLI uses to process command-line options. CSV-specific
     * options are defined here. Shared Configuration options are defined in the Template object superclass.
     *
     * @return an Options object containing valid options and associated parameters.
     */
    @Override
    public Options defineExtendedOptions(Options options) {
        options.addOption(
                Option.builder(CSVOptionConstants.OPT_NEWLINE)
                        .longOpt(CSVOptionConstants.LONG_OPT_NEWLINE)
                        .hasArg()
                        .desc("Platform-dependent newline characters. < WINDOWS | LINUX | OSX | MAC | SYSTEM > Default: SYSTEM.")
                        .build()
        );

        return options;
    }

    /**
     * This method interrogates and resolves CSV-specific options found on the command-line
     * when the program is invoked.
     */
    @Override
    public void resolveExtendedOptions(CommandLine parsedCommandline, Builder builder) {

//        if (parsedCommandline.hasOption(CSVOptionConstants.OPT_NEWLINE)) {
//            String nl = parsedCommandline.getOptionValue(CSVOptionConstants.OPT_NEWLINE).toUpperCase();
//            switch (nl) {
//                case CSVOptionConstants.WINDOWS:
//                    builder.set = Newline.WINDOWS;
//                    newlineAsString = Newline.WINDOWS.getNewlineValueOf();
//                    break;
//                case CSVOptionConstants.LINUX:
//                    newline = Newline.LINUX;
//                    newlineAsString = Newline.LINUX.getNewlineValueOf();
//                    break;
//                case CSVOptionConstants.OSX:
//                    newline = Newline.OSX;
//                    newlineAsString = Newline.OSX.getNewlineValueOf();
//                    break;
//                case CSVOptionConstants.MAC:
//                    newline = Newline.OLDMAC;
//                    newlineAsString = Newline.OLDMAC.getNewlineValueOf();
//                    break;
//                case CSVOptionConstants.SYSTEM:
//                    newline = Newline.SYSTEM;
//                    newlineAsString = System.getProperty("line.separator");
//                default:
//                    newline = Newline.NOT_SPECIFIED;
//                    setNewlineAsString("");
//                    break;
//            }
//        } else {
//            newline = Newline.NOT_SPECIFIED;
//            setNewlineAsString("");
//        }

    }

    /**
     * Constants unique to processing CSV command-line options
     */
    public static class CSVOptionConstants {

        public static final String OPT_NEWLINE = "n";
        public static final String LONG_OPT_NEWLINE = "newline";

        public static final char TAB = '\t';
        public static final char COMMA = ',';

        public static final String WINDOWS = "WINDOWS";
        public static final String LINUX = "LINUX";
        public static final String OSX = "OSX";
        public static final String MAC = "MAC";
        public static final String SYSTEM = "SYSTEM";
        public static final String ESCAPE_N = "\n";
        public static final String ESCAPE_R = "\r";
        public static final String ESCAPE_R_ESCAPE_N = "\r\n";
    }

    enum Newline {
        WINDOWS("\r\n"),
        LINUX("\n"),
        OSX("\n"),
        OLDMAC("\r"),
        SYSTEM( System.getProperty("line.separator") ),
        NOT_SPECIFIED("not_specified");

        private String nl;

        private String getNewlineValueOf() {
            return this.nl;
        }

        Newline(String nl) {
            this.nl = nl;
        }
    }

}