package space.debian.odeliajobs.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TextBuilder {
    private static Class<?> _CRAFTPLAYER_CLASS;
    private static Constructor<?> _PACKET_PLAY_OUT_CHAT_CONSTRUCTOR;
    private static Method _A_METHOD;
    private static Method _GETHANDLE_METHOD;
    private static Method _SEND_PACKET_METHOD;
    private static Field _PLAYER_CONNECTION_FIELD;
    private static String text;
    private String hover;
    private String click;
    private HoverEventType hoverAction;
    private ClickEventType clickAction;
    private static String json;

    public TextBuilder(String text) {
        TextBuilder.text = text;
    }

    public TextBuilder setHoverEvent(HoverEventType type, String value) {
        Validate.notNull((Object)((Object)type), (String)"HoverActionType cannot be null");
        Validate.notNull((Object)value, (String)"JSON Value cannot be null");
        this.hover = value;
        this.hoverAction = type;
        return this;
    }

    public TextBuilder setClickEvent(ClickEventType type, String value) {
        Validate.notNull((Object)((Object)type), (String)"ClickActionType cannot be null");
        Validate.notNull((Object)value, (String)"JSON Value cannot be null");
        this.click = value;
        this.clickAction = type;
        return this;
    }

    public TextBuilder buildText() {
        if (!this.getClick().isPresent() && !this.getHover().isPresent()) {
            json = "{\"text\":\"" + text + "\"}";
        }
        if (!this.getClick().isPresent() && this.getHover().isPresent()) {
            json = this.hoverAction == HoverEventType.SHOW_ACHIEVEMENT ? "{\"text\":\"" + text + "\",\"hoverEvent\":{\"action\":\"" + this.hoverAction.getActionName() + "\",\"value\":\"achievement." + this.hover + "\"}}" : (this.hoverAction == HoverEventType.SHOW_STATISTIC ? "{\"text\":\"" + text + "\",\"hoverEvent\":{\"action\":\"" + this.hoverAction.getActionName() + "\",\"value\":\"stat." + this.hover + "\"}}" : "{\"text\":\"" + text + "\",\"hoverEvent\":{\"action\":\"" + this.hoverAction.getActionName() + "\",\"value\":\"" + this.hover + "\"}}");
        }
        if (this.getClick().isPresent() && this.getHover().isPresent()) {
            json = "{\"text\":\"" + text + "\",\"clickEvent\":{\"action\":\"" + this.clickAction.getActionName() + "\",\"value\":\"" + this.click + "\"},\"hoverEvent\":{\"action\":\"" + this.hoverAction.getActionName() + "\",\"value\":\"" + this.hover + "\"}}";
        }
        if (this.getClick().isPresent() && !this.getHover().isPresent()) {
            json = "{\"text\":\"" + text + "\",\"clickEvent\":{\"action\":\"" + this.clickAction.getActionName() + "\",\"value\":\"" + this.click + "\"}}";
        }
        return this;
    }

    public void sendMessage(Player player) {
        try {
            Object messageComponent = _A_METHOD.invoke(null, json);
            Object packet = _PACKET_PLAY_OUT_CHAT_CONSTRUCTOR.newInstance(messageComponent, (byte)1);
            Object craftPlayer = _CRAFTPLAYER_CLASS.cast((Object)player);
            Object entityPlayer = _GETHANDLE_METHOD.invoke(craftPlayer, new Object[0]);
            Object playerConnection = _PLAYER_CONNECTION_FIELD.get(entityPlayer);
            _SEND_PACKET_METHOD.invoke(playerConnection, packet);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getUnformattedText() {
        return text;
    }

    public String getJson() {
        return json;
    }

    private Optional<String> getHover() {
        return Optional.of(this.hover);
    }

    private Optional<String> getClick() {
        return Optional.of(this.click);
    }

    static {
        String name = Bukkit.getServer().getClass().getName();
        name = name.substring(name.indexOf("craftbukkit.") + "craftbukkit.".length());
        String _VERSION = name.substring(0, name.indexOf("."));
        try {
            Class<?> _ICHAT_BASE_COMPONENT_CLASS = Class.forName("net.minecraft.server." + _VERSION + ".IChatBaseComponent");
            Class<?> _PACKET_PLAY_OUT_CHAT_CLASS = Class.forName("net.minecraft.server." + _VERSION + ".PacketPlayOutChat");
            _CRAFTPLAYER_CLASS = Class.forName("org.bukkit.craftbukkit." + _VERSION + ".entity.CraftPlayer");
            Class<?> _ENTITYPLAYER_CLASS = Class.forName("net.minecraft.server." + _VERSION + ".EntityPlayer");
            Class<?> _PLAYER_CONNECTION_CLASS = Class.forName("net.minecraft.server." + _VERSION + ".PlayerConnection");
            _PACKET_PLAY_OUT_CHAT_CONSTRUCTOR = _PACKET_PLAY_OUT_CHAT_CLASS.getConstructor(_ICHAT_BASE_COMPONENT_CLASS, Byte.TYPE);
            _A_METHOD = Class.forName("net.minecraft.server." + _VERSION + ".IChatBaseComponent$ChatSerializer").getMethod("a", String.class);
            _GETHANDLE_METHOD = _CRAFTPLAYER_CLASS.getMethod("getHandle", new Class[0]);
            _SEND_PACKET_METHOD = _PLAYER_CONNECTION_CLASS.getMethod("sendPacket", Class.forName("net.minecraft.server." + _VERSION + ".Packet"));
            _PLAYER_CONNECTION_FIELD = _ENTITYPLAYER_CLASS.getDeclaredField("playerConnection");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        json = "{\"text\":\"" + text + "\"}";
    }

    public static enum HoverEventType {
        SHOW_TEXT("show_text"),
        SHOW_ITEM("show_item"),
        SHOW_ACHIEVEMENT("show_achievement"),
        SHOW_STATISTIC("show_achievement");

        private String actionName;

        private HoverEventType(String actionName) {
            this.actionName = actionName;
        }

        public String getActionName() {
            return this.actionName;
        }
    }

    public static enum ClickEventType {
        OPEN_URL("open_url"),
        RUN_COMMAND("run_command"),
        SUGGEST_TEXT("suggest_command");

        private String actionName;

        private ClickEventType(String actionName) {
            this.actionName = actionName;
        }

        public String getActionName() {
            return this.actionName;
        }
    }
}

