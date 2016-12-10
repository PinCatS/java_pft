package ru.pincats.jpt.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by PinCatS on 11.12.2016.
 */

@Entity
@Table(name = "mantis_user_table")
public class MantisUser {
    @Id
    private int id;

    private String username;
    private String realname;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public MantisUser withId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MantisUser withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRealname() {
        return realname;
    }

    public MantisUser withRealname(String realname) {
        this.realname = realname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MantisUser withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MantisUser withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "MantisUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MantisUser that = (MantisUser) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (realname != null ? !realname.equals(that.realname) : that.realname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
