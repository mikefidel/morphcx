package ndextools.morphcx.processors.csv.configuration;

import ndextools.morphcx.configuration.cli.Builder;

/**
 * CSVBuilder is an interface associated with CSV Configuration objects. It defines abstract setter methods
 * unique to CSVConfiguration objects.
 */
public interface CSVBuilder extends Builder {

    void setFileType(String fileType);

    void setNewline(String newline);

}