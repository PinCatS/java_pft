package ru.pincats.jpt.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by PinCatS on 15.11.2016.
 */
public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;

    public Groups(Groups group) {
        this.delegate = new HashSet<GroupData>(group);
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded(GroupData group) {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without(GroupData group) {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }

    public Groups withModified(GroupData group) {
        Groups groups = new Groups(this);

        GroupData groupToDelete = null;
        for (GroupData g : groups) {
            if (g.getId() == group.getId())
                groupToDelete = g;
        }
        if (groupToDelete != null)
            groups.remove(groupToDelete);

        groups.add(group);
        return groups;
    }
}
