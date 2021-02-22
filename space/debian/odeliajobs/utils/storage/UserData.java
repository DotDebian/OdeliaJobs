package space.debian.odeliajobs.utils.storage;

import java.util.HashMap;
import space.debian.odeliajobs.objects.UserProfile;
import space.debian.odeliajobs.utils.storage.Persist;
import space.debian.odeliajobs.utils.storage.Saver;

public class UserData
implements Saver {
    private static transient UserData i = new UserData();
    private static HashMap<String, UserProfile> profileMap = new HashMap();

    public static HashMap<String, UserProfile> getProfileMap() {
        return profileMap;
    }

    @Override
    public void save(Persist persist) {
        persist.save(i);
    }

    @Override
    public void load(Persist persist) {
        persist.loadOrSaveDefault(i, UserData.class, this.getClass().getSimpleName().toLowerCase());
    }
}

