package space.debian.odeliajobs.utils.storage;

import space.debian.odeliajobs.utils.storage.Persist;

public interface Saver {
    public void save(Persist var1);

    public void load(Persist var1);
}

