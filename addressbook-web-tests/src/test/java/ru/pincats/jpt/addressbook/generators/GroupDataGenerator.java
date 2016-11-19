package ru.pincats.jpt.addressbook.generators;

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

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);
        List<GroupData> groups = generate(count);
        save(groups, file);
    }

    private static void save(List<GroupData> groups, File file) throws IOException {
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
