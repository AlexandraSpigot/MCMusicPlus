package me.alexandra.mcmusicplus.listeners;

import com.xxmicloxx.NoteBlockAPI.event.SongEndEvent;
import com.xxmicloxx.NoteBlockAPI.event.SongStoppedEvent;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import me.alexandra.mcmusicplus.SongPlayer.PlaySong;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class SongListeners implements Listener {

    @EventHandler
    public void onSongEnd(SongEndEvent e){
        SongPlayer sp = e.getSongPlayer(); //Gives you SongPlayer
        Song s = sp.getSong();             //Gives you player Song
        for (UUID id : sp.getPlayerUUIDs()) {
            Player p = Bukkit.getPlayer(id);
            PlaySong.music.remove(p);
        }
    }

    @EventHandler
    public void onSongStopped(SongStoppedEvent e){
        SongPlayer sp = e.getSongPlayer(); //Gives you SongPlayer
        Song s = sp.getSong();             //Gives you player Song
        for (UUID id : sp.getPlayerUUIDs()) {
            Player p = Bukkit.getPlayer(id);
            PlaySong.music.remove(p);
        }
    }

}
