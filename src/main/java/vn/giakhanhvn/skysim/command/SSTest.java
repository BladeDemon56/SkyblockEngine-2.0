package vn.giakhanhvn.skysim.command;

import vn.giakhanhvn.skysim.dimoon.DimoonLootItem;
import java.util.List;
import java.util.Iterator;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.util.SUtil;
import java.io.File;
import vn.giakhanhvn.skysim.gui.ConfirmWitherRuins;
import vn.giakhanhvn.skysim.dimoon.DimoonLootTable;
import vn.giakhanhvn.skysim.dimoon.Arena;
import vn.giakhanhvn.skysim.util.Sputnik;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.dungeons.Blessings;
import vn.giakhanhvn.skysim.dungeons.BlessingType;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.user.UserStash;
import org.bukkit.Material;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.extra.protocol.PacketInvoker;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.dungeons.ItemSerial;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.Watcher;
import org.bukkit.Location;
import org.bukkit.ChatColor;

@CommandParameters(description = "Modify your absorption amount.", permission = "spt.player")
public class SSTest extends SCommand
{
    @Override
    public void run(final CommandSource sender, final String[] args) {
        final Player player = sender.getPlayer();
        if (player.isOp()) {
            if (args[0].contains("sp")) {
                player.sendMessage(ChatColor.YELLOW + "Intizing Map...");
                final long s = System.currentTimeMillis();
                final Watcher w = new Watcher(new Location(player.getWorld(), 96.0, 99.0, 96.0), new Location(player.getWorld(), 126.0, 66.0, 126.0), 69);
                w.intitize();
                final long s_ = System.currentTimeMillis() - s;
                player.sendMessage(ChatColor.GREEN + "All actions completed (Loop, placing heads, spawn Watcher)! This took " + ChatColor.YELLOW + s_ + "ms");
            }
            else if (args[0].contains("pl")) {
                player.sendMessage(ChatColor.YELLOW + "Done!");
                for (final Entity e : player.getWorld().getEntities()) {
                    if (e instanceof ArmorStand && e.hasMetadata("WATCHER_ENTITY")) {
                        e.getLocation().add(0.0, 1.7, 0.0).getBlock().setTypeIdAndData(35, (byte)4, true);
                    }
                }
            }
            else if (args[0].contains("test")) {
                final ItemSerial is = ItemSerial.createBlank();
                if (player.getItemInHand() != null) {
                    final SItem sitem = SItem.find(player.getItemInHand());
                    is.saveTo(sitem);
                    sitem.setStarAmount(5);
                }
            }
            else if (args[0].contains("gtp")) {
                new SEntity((Entity)player, SEntityType.TEST_CHIMMY_OBJECT_T34, new Object[0]);
            }
            else if (!args[0].contains("wipe")) {
                if (args[0].contains("vlw")) {
                    PacketInvoker.dropVoidSpawner(player, player.getLocation());
                }
                else if (args[0].contains("bung")) {
                    SkySimEngine.getPlugin().updateServerName(player);
                }
                else if (args[0].contains("putitemstash")) {
                    if (player.getItemInHand() != null) {
                        if (player.getItemInHand().getType() == Material.AIR) {
                            return;
                        }
                        UserStash.getStash(player.getUniqueId()).addItemInStash(player.getItemInHand());
                        player.setItemInHand((ItemStack)null);
                    }
                }
                else if (args[0].contains("bs")) {
                    Blessings.dropBlessingPickable(player.getLocation().clone().add(2.0, 0.0, 2.0), new Blessings(BlessingType.valueOf(args[1]), Integer.parseInt(args[2]), player.getWorld()));
                }
                else if (args[0].contains("blessshow")) {
                    for (final Blessings b : Blessings.getFrom(player.getWorld())) {
                        player.sendMessage(ChatColor.YELLOW + b.toText());
                        final float[] n = b.getBlessingStats(User.getUser(player.getUniqueId())).getDefaultArray();
                        final StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < n.length; ++i) {
                            sb.append(n[i] + " ");
                        }
                        player.sendMessage(ChatColor.RED + sb.toString());
                    }
                }
                else if (args[0].contains("rs")) {
                    Blessings.resetForWorld(player.getWorld());
                }
                else if (args[0].contains("chest")) {
                    Sputnik.makeChestBlessings(player.getLocation(), new Blessings(BlessingType.valueOf(args[1]), Integer.parseInt(args[2]), player.getWorld()), Boolean.parseBoolean(args[3]), Byte.parseByte(args[4]));
                }
                else if (args[0].contains("citem")) {
                    Sputnik.makeChestItemLoot(player.getLocation(), player.getItemInHand(), Boolean.parseBoolean(args[3]), Byte.parseByte(args[4]));
                }
                else if (args[0].contains("post")) {
                    Arena.cleanArena();
                }
                else if (args[0].contains("cal")) {
                    new DimoonLootTable(player, Integer.parseInt(args[1]), Integer.parseInt(args[2])).roll().forEach(list -> list.forEach(item -> player.sendMessage(item.getItem().getType().toString())));
                }
                else if (args[0].contains("ruins")) {
                    new ConfirmWitherRuins().open(player);
                }
                else if (args[0].contains("musicbgm")) {
                    if (args[1].contains("play")) {
                        if (sender.getUser().isPlayingSong()) {
                            this.send("&d[♫] &cYou're currently playing a song already! Use /sstest musicbgm stop to stop it!");
                            return;
                        }
                        final File s2 = new File(SkySimEngine.getPlugin().getDataFolder() + File.separator + "/songs/" + args[2] + ".nbs");
                        if (!s2.exists()) {
                            this.send("&d[♫] &cThe specified BGM file does not exist!");
                            return;
                        }
                        this.send("&d[♫] &aPlaying BGM File &e" + args[2] + ".nbs &afrom disk&a for this world!");
                        SUtil.broadcastWorld(Sputnik.trans("&c"), player.getWorld());
                        SUtil.broadcastWorld(Sputnik.trans("&d[♫] &eYou're listening to &6" + args[2].replaceAll("_", " ") + " &efrom the &dSkySim Radio&e, requested by &d" + player.getName() + "&e, enjoy!"), player.getWorld());
                        SUtil.broadcastWorld(Sputnik.trans("&c"), player.getWorld());
                        Sputnik.playSound(s2, 1000, 10, true, player, player.getLocation());
                    }
                    else if (args[1].contains("stop")) {
                        this.send("&d[♫] &eStopped all music played by you!");
                        sender.getUser().setPlayingSong(false);
                    }
                    else {
                        this.send("&d♫ SkySim Radiowave Usage ♫");
                        this.send("&eCommand (Play): &6/sstest musicbgm play <song name>");
                        this.send("&eCommand (Stop): &6/sstest musicbgm stop");
                        this.send("&eAbout: &cSSMusicEngine-v0.1.0-ALPHA");
                    }
                }
            }
        }
        else {
            this.send(ChatColor.RED + "no, bad.");
        }
    }
}
