/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.friend;

import java.util.ArrayList;
import java.util.List;
import rip.autumn.friend.Friend;

public final class FriendManager {
    private static final List<Friend> friends = new ArrayList<Friend>();

    public void add(String name) {
        this.add(name, name);
    }

    public void add(String name, String alias) {
        friends.add(new Friend(name, alias));
    }

    public static boolean isFriend(String name) {
        for (Friend friend : friends) {
            if (!friend.getUsername().equals(name)) continue;
            return true;
        }
        return false;
    }
}

