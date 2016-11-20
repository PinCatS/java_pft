package ru.pincats.jpt.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.pincats.jpt.addressbook.model.GroupData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PinCatS on 19.11.2016.
 */
public class GroupDataGenerator extends DataGenerator<GroupData> {

    @Parameter(names = "-c", description = "Groups count")
    private int count;

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
        save(groups, file, format);
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
