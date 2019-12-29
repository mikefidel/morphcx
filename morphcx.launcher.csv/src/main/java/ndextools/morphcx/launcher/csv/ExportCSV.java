package ndextools.morphcx.launcher.csv;

import ndextools.morphcx.configuration.cli.base.Configuration;
import ndextools.morphcx.configuration.cli.base.Template;
import ndextools.morphcx.configuration.cli.csv.CSVBuilder;
import ndextools.morphcx.configuration.cli.csv.CSVTemplate;
import ndextools.morphcx.readers.nicecx.ToNiceCX;
import org.ndexbio.model.cx.NiceCXNetwork;

import java.io.IOException;

/**
 * Root program containing entry point to launch application by commandline.
 */
public class ExportCSV {

    public static void main(final String[] args) {
        String[] cmdline = (args == null) ? new String[0] : args;
        String appName = ExportCSV.class.getSimpleName().toLowerCase();

        try {
            Configuration cfg = configureByCLI(cmdline, appName);
            dispatchByOperation(cfg);
        } catch (org.apache.commons.cli.ParseException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Configuration configureByCLI(final String[] commandline, final String appName)
            throws org.apache.commons.cli.ParseException {
        Template template = new CSVTemplate(commandline, appName);
        CSVBuilder builder = new CSVBuilder();
        return template.configure(builder);
    }

    private static void dispatchByOperation(final Configuration cfg) throws IOException {
        if (!cfg.isShowHelpPrompt()) {
            ToNiceCX cxReader = new ToNiceCX(cfg);
            NiceCXNetwork cx = cxReader.makeNiceCX();
            // TODO add some test
        }
    }

}