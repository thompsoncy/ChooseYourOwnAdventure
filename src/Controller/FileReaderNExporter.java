package Controller;

import Model.CYOAModelOperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This interface represents a file reader and exporter for a CYOA.
 */
public interface FileReaderNExporter {

    /**
     * save the animation file in an intermediary form.
     *
     * @param filepath the path of the file to be read
     * @return the model in the form of a string
     * @throws FileNotFoundException if the specified file cannot be read
     * @throws IllegalStateException if the format of the file is invalid
     */
    String readFile(String filepath)
            throws FileNotFoundException, IllegalStateException;

    /**
     * saves the given file in an intermediary form at specfied location
     *
     * @param filepath the path of the file to be read
     * @param model the model being saved
     * @throws FileNotFoundException if the specified file location does not exist
     */
    void saveFile(String filepath, CYOAModelOperations model)
            throws FileNotFoundException;

    /**
     * exports the given file in a text booklike form at specfied location
     *
     * @param filepath the path of the file to be read
     * @param model the model being exported
     * @throws FileNotFoundException if the specified file location does not exist
     */
    void exportFile(String filepath, CYOAModelOperations model)
            throws FileNotFoundException;

}
