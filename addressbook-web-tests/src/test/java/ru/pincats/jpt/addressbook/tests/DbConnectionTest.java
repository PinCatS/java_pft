package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.GroupData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PinCatS on 24.11.2016.
 */
public class DbConnectionTest {

    @Test
    public void testDbConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            final ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            List<GroupData> groups = new ArrayList<>();
            while (rs.next()) {
                GroupData group = new GroupData()
                            .withId(rs.getInt("group_id"))
                            .withName(rs.getString("group_name"))
                            .withHeader(rs.getString("group_header"))
                            .withFooter(rs.getString("group_footer"));
                groups.add(group);
            }
            rs.close();
            st.close();
            conn.close();

            System.out.println(groups);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
