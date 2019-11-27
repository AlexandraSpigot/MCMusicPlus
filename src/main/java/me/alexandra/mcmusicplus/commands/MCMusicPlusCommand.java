package me.alexandra.mcmusicplus.commands;

import me.alexandra.mcmusicplus.MusicUI;
import me.alexandra.mcmusicplus.SongPlayer.PlaySong;
import me.alexandra.mcmusicplus.utils.Utilities;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MCMusicPlusCommand extends BukkitCommand {

    private final PlaySong playsong = new PlaySong();

    public MCMusicPlusCommand() {
        super("music");
        setAliases(Arrays.asList("mcmusic", "mcm"));
        setUsage("/music <command>");
        setDescription("Default command for MCMusic+");
    }

    public boolean execute(CommandSender s, String arg, String[] args) {
        Player p = (Player) s;
        if (!(s instanceof Player)) {
            s.sendMessage("You must be a player to run this command");
            return true;
        }
        if (args.length == 0) {
            MusicUI.openMusicUI(p);
            Utilities.tell(p, "Size: " + PlaySong.songList.size());
            return true;
        }
        if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {

        }
        if (args[0].equalsIgnoreCase("play")) {
            StringBuilder sb = new StringBuilder();
            if (args.length >= 3) {
                for (int i = 1; i < args.length;) {
                    sb.append(args[i]).append(" ");
                    i++;
                }
                String song = sb.toString().replaceAll("\\s+$", "");
                if (song.contains(".nbs")) {
                    playsong.playSong(p, song);
                } else {
                    playsong.playSong(p,song + ".nbs");
                }
            } else {

                if (args[1].contains(".nbs")) {
                    playsong.playSong(p, args[1]);
                } else {
                    playsong.playSong(p, args[1] + ".nbs");
                }
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("list")) {
            
        }
        return true;
    }

}
