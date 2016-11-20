package ru.pincats.jpt.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import ru.pincats.jpt.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by PinCatS on 20.11.2016.
 */
public class DataGenerator<G> {

    @Parameter(names = "-d", description = "Data format")
    protected String format;

    @Parameter(names = "-f", description = "Target file")
    protected String file;

    protected void saveAsXml(List<G> list, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(new TypeToken<List<G>>(){}.getType().getClass());
        String xml = xstream.toXML(list);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    protected void saveAsJson(List<G> list, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(list);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    protected void save(List<G> list, String file, String format) throws IOException {
        if (format.equals("xml")) {
            saveAsXml(list, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(list, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
            return;
        }
    }
}
