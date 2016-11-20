package ru.pincats.jpt.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.pincats.jpt.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PinCatS on 19.11.2016.
 */
public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Groups count")
    private int count;

    @Parameter(names = "-f", description = "Target file")
    private String file;

    @Parameter(names = "-d", description = "Data format")
    private String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generate(count);
        if (format.equals("csv")) {
            saveAsCsv(groups, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(groups, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
            return;
        }
    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    private static List<GroupData> generate(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            GroupData group = new GroupData().withName("test " + i).withHeader("test header " + i)
                    .withFooter("test footer " + i);
            groups.add(group);
        }
        return groups;
    }
}
