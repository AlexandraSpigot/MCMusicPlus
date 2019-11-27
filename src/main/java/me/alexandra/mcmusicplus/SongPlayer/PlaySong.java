package me.alexandra.mcmusicplus.SongPlayer;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import me.alexandra.mcmusicplus.MCMusicPlus;
import me.alexandra.mcmusicplus.MusicUI;
import me.alexandra.mcmusicplus.utils.Utilities;
import org.bukkit.Note;
import org.bukkit.Utility;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class PlaySong {

    public static Map<Player, Song> music = new HashMap<>();
    public static List<String> songList = new ArrayList<>();

    public void playSong(Player player, String s) {
        try {
            NoteBlockAPI.stopPlaying(player);
            Song song = NBSDecoder.parse(MusicUI.musicFinder(s));
            RadioSongPlayer rp = new RadioSongPlayer(song);
            rp.addPlayer(player);
            rp.setPlaying(true);
            music.put(player, song);
        } catch (Exception e) {
            Utilities.logConsole(Level.SEVERE,"Track Not Found!");
            Utilities.tell(player, "&cTrack Not Found!");
        }
    }

    public void registerList() {
        File f = new File(MCMusicPlus.getInstance().getDataFolder() + File.separator + "songs");

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".nbs");
            }
        };

        int songNumb = 0;

        File[] files = f.listFiles(textFilter);
        Utilities.logConsole(Level.INFO, "+============[SongLoader]============+");
        for (File file : files) {
            if (file.isDirectory()) {
                //NOTHING
            } else {
                Utilities.logConsole(Level.INFO, "Song Found: " + file.getName());
                songList.add(file.getName());
                songNumb++;
            }

        }
        Utilities.logConsole(Level.INFO, "Total of songs: " + songNumb);
        Utilities.logConsole(Level.INFO, "+============[SongLoader]============+");

    }

}
