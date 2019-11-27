package me.alexandra.mcmusicplus;

import me.alexandra.mcmusicplus.SongPlayer.PlaySong;
import me.alexandra.mcmusicplus.commands.MCMusicPlusCommand;
import me.alexandra.mcmusicplus.listeners.SongListeners;
import me.alexandra.mcmusicplus.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class MCMusicPlus extends JavaPlugin {

    private static MCMusicPlus instance;
    PluginDescriptionFile pdf = this.getDescription();
    FileConfiguration config = this.getConfig();
    private final PlaySong playsong = new PlaySong();

    @Override
    public void onEnable() {
        instance = this;
        boolean NoteBlockAPI = true;
        registerCommands(new MCMusicPlusCommand());
        registerEvents(new SongListeners());
        this.saveDefaultConfig();
        File songFiles;
        try {
            songFiles = new File(getDataFolder() + File.separator + "songs");
            if(!songFiles.exists()){
                songFiles.mkdirs();
            }
        } catch(SecurityException e) {
            // do something...
            return;
        }
        playsong.registerList();
    }

    @Override
    public void onDisable() {
        instance = null;
        PlaySong.music.clear();
    }

    public static MCMusicPlus getInstance() {
        return instance;
    }

    public void registerCommands(Command... cmd) {
        for (Command command : cmd) {
            Utilities.registerCommand(command);
        }
    }

    public void registerEvents(Listener... listener) {
        PluginManager pm = getServer().getPluginManager();

        for (Listener l : listener) {
            pm.registerEvents(l, this);
        }
    }

}
