package space.debian.odeliajobs.objects;

import space.debian.odeliajobs.objects.types.RewardType;

public class Reward {
    private RewardType type;
    private String data;
    private int data_int;
    private String message;

    public Reward(RewardType type, int data_int, String message) {
        this.type = type;
        this.data_int = data_int;
    }

    public Reward(RewardType type, String data, String message) {
        this.type = type;
        this.data = data;
    }

    public RewardType getType() {
        return this.type;
    }

    public String getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public int getData_int() {
        return this.data_int;
    }
}

