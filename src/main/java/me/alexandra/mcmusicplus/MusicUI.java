package me.alexandra.mcmusicplus;

import me.alexandra.mcmusicplus.SongPlayer.PlaySong;
import me.alexandra.mcmusicplus.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Arrays;

public class MusicUI {

    static int amountOfPages = (int) (PlaySong.songList.size() / 45) + 1;

    public static File musicFinder(String song) {
        String s = song.toLowerCase();
        File songFile;
        for (String st : PlaySong.songList) {
            if (st.equalsIgnoreCase(s)) {
                songFile = new File(MCMusicPlus.getInstance().getDataFolder() + File.separator + "songs" + File.separator + st);
                return songFile;
            }
        }
        return null;
    }

    public static void openMusicUI(Player player) {

        int currentPage = 1;
        Inventory gui = Bukkit.createInventory(null, 9 * 6, Utilities.colorize("&3&lMusic Player (Page " + currentPage + ")"));
        /* ITEMSTACKS */
        /* CURRENTSONG */
        ItemStack status;
        if (PlaySong.music.containsKey(player)) {
            status = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta statusMeta = status.getItemMeta();
            statusMeta.setDisplayName(Utilities.colorize("&ePlaying..."));
            statusMeta.setLore(Arrays.asList(
                    "",
                    Utilities.colorize("&b&lClick here to stop the song"),
                    "",
                    Utilities.colorize("&aCurrent song: &e&l")
            ));
            status.setItemMeta(statusMeta);
        } else {
            status = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta statusMeta = status.getItemMeta();
            statusMeta.setDisplayName(Utilities.colorize("&c&lIdle"));
            statusMeta.setLore(Arrays.asList(
                    "",
                    Utilities.colorize("&b&lClick a song to play it"),
                    "",
                    Utilities.colorize("&aCurrent song: &c&lN/A")
            ));
            status.setItemMeta(statusMeta);
        }
        /* Next Page */
        ItemStack nextPage = new ItemStack(Material.ARROW);
        ItemMeta nextPageMeta = nextPage.getItemMeta();
        nextPageMeta.setDisplayName(Utilities.colorize("&eNext Page"));
        nextPage.setItemMeta(nextPageMeta);
        gui.setItem(8, nextPage);

        /* Last Page */
        ItemStack lastPage = new ItemStack(Material.ARROW);
        ItemMeta lastPageMeta = lastPage.getItemMeta();
        lastPageMeta.setDisplayName(Utilities.colorize("&eNext Page"));
        lastPage.setItemMeta(lastPageMeta);
        gui.setItem(0, lastPage);

        /* DISKS */
        ItemStack cd;
        for (int i = 46 * (currentPage - 1); i < (i + 46); i++) {
            Utilities.tell(player, "Loop Started " + i);
            if (i < PlaySong.songList.size()) {
                String song = PlaySong.songList.get(i);
                int pageAt = (int) i / 45 + 1;
                int position = ((i - (45 * (pageAt - 1)) + 9));
                cd = new ItemStack(Material.MUSIC_DISC_CAT);
                ItemMeta cdMeta = cd.getItemMeta();
                cdMeta.setDisplayName(Utilities.colorize("&b&l" + song.replace(".nbs", "")));
                cdMeta.setLore(Arrays.asList(Utilities.colorize("&aClick here to play &b" + song.replace(".nbs", ""))));
                cd.setItemMeta(cdMeta);
                if (pageAt < currentPage + 1) {
                    gui.setItem(position, cd);
                    Utilities.tell(player, "Added song: " + song + " to slot " + position);
                    Utilities.tell(player, "Page:" + pageAt + ", " + currentPage);
                } else {
                    Utilities.tell(player, "Loop Break " + i);
                    Utilities.tell(player, "Size: " + PlaySong.songList.size());
                    break;
                }
            } else {
                Utilities.tell(player, "Loop Break " + i);
                Utilities.tell(player, "Size: " + PlaySong.songList.size());
                break;
            }
        }


        /* SET */
        gui.setItem(4, status);


        /* FINAL */
        player.openInventory(gui);
    }
}
