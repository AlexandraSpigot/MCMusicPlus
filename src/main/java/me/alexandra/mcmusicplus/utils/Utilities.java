package me.alexandra.mcmusicplus.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.logging.Level;

public class Utilities {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void logConsole(Level lvl, String msg) {
        Bukkit.getLogger().log(lvl, msg);
    }
    public static void logConsole(Level lvl, String... msg) {
        for (final String message : msg)
        Bukkit.getLogger().log(lvl, message);
    }

    public static void tell(Player p, String msg) {
        p.sendMessage(colorize(msg));
    }
    public static void tell(Player p, String... msg) {
        for (String message : msg) {
            p.sendMessage(colorize(message));
        }
    }

    public static void broadcast(String msg) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            tell(p, msg);
        }
    }

    public static void sendBar(Player p, String msg) {
        try {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(colorize(msg)));
        } catch (final Throwable t) {
            tell(p, msg);
        }
    }

    public static void registerCommand(Command command) {
        try {
            final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(command.getLabel(), command);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
