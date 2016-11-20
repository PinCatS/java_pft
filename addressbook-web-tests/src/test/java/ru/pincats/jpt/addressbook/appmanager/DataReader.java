package ru.pincats.jpt.addressbook.appmanager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by PinCatS on 20.11.2016.
 */
public class DataReader {

    public String readTestDataFrom(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        String data = "";
        String line = reader.readLine();
        while (line != null) {
            data += line;
            line = reader.readLine();
        }
        reader.close();
        return data;
    }

    public Object fromXML(String xml, Class inClass) {
        XStream xstream = new XStream();
        xstream.processAnnotations(inClass);
        return xstream.fromXML(xml);
    }

    public Object fromJson(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}
