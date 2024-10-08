package net.hypixel.skyblock.command;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.hypixel.skyblock.SkyBlock;
import net.hypixel.skyblock.command.CommandParameters;
import net.hypixel.skyblock.command.CommandSource;
import net.hypixel.skyblock.command.SCommand;
import net.hypixel.skyblock.features.ranks.PlayerRank;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

@CommandParameters(description = "mute player", aliases = "mute", permission = PlayerRank.HELPER)

public class Mute extends SCommand {
    private static final Pattern periodPattern = Pattern.compile("([0-9]+)([hdwmy])");

    @Override
    public void run(CommandSource sender, String[] args) {
            if (args.length >= 3) {
                String reason = "";
                for (int i = 2; i < args.length; i++) {
                    reason = String.valueOf(reason) + args[i] + " ";
                }
                String reason2 = reason.substring(0, reason.length() - 1);
                Player target = Bukkit.getPlayerExact(args[0]);
                File playerfile = new File(((SkyBlock) SkyBlock.getPlugin(SkyBlock.class)).getDataFolder() + File.separator, "punishments.yml");
                YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(playerfile);
                String uuid = null;
                if (target != null) {
                    uuid = target.getPlayer().getUniqueId().toString();
                }
                if (uuid == null) {
                    for (String key : loadConfiguration.getKeys(false)) {
                        if (loadConfiguration.getString(String.valueOf(key) + ".name").equalsIgnoreCase(args[0])) {
                            uuid = key;
                        }
                    }
                }
                if (uuid == null) {
                    sender.send("§cPlayer does not exist.");
                }
                long unixTime = System.currentTimeMillis() / 1000;
                long muteTime = (parsePeriod(args[1]).longValue() / 1000) - 1;
                if (muteTime < 59) {
                    sender.send("§cYou can not mute someone for less than 1 minute.");

                } else if (loadConfiguration.contains(uuid)) {
                    if (!loadConfiguration.getBoolean(String.valueOf(uuid) + ".mute.ismuted")) {
                        try {
                            loadConfiguration.set(String.valueOf(uuid) + ".mute.ismuted", true);
                            loadConfiguration.set(String.valueOf(uuid) + ".mute.reason", reason2);
                            loadConfiguration.set(String.valueOf(uuid) + ".mute.length", Long.valueOf(unixTime + muteTime));
                            String pwd = RandomStringUtils.random(8, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
                            loadConfiguration.set(String.valueOf(uuid) + ".mute.id", pwd);
                            loadConfiguration.save(playerfile);
                            if (target != null) {
                                sender.send("§aMuted " + Bukkit.getPlayer(args[0]).getName() + " for " + args[1] + " for " + reason2);
                                target.sendMessage("§c§l§m---------------------------------------------");
                                target.sendMessage("§cYou are currently muted for " + reason2 + ".");
                                target.sendMessage("§7Your mute will expire in §c" + calculateTime(loadConfiguration.getInt(String.valueOf(uuid) + ".mute.length") - unixTime));
                                target.sendMessage("");
                                target.sendMessage("§7Find out more here: §e" + "https://discord.gg/godspunky");
                                target.sendMessage("§7Mute ID: §f#" + loadConfiguration.getString(String.valueOf(uuid) + ".mute.id"));
                                target.sendMessage("§c§l§m---------------------------------------------");
                            } else {
                                sender.send("§aMuted " + args[0] + " for " + args[1] + " for " + reason2);
                            }

                        } catch (IOException exception) {
                            exception.printStackTrace();

                        }
                    }
                    sender.send("§cPlayer is already muted!");

                }
            }
            sender.send("§cInvalid syntax. Correct: /mute <name> <length> <reason>");

    }

    public static String calculateTime(long seconds) {
        int days = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (days * 24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
        String time = (" " + days + "d " + hours + "h " + minute + "m " + second + "s").toString().replace(" 0d", "").replace(" 0h", "").replace(" 0m", "").replace(" 0s", "").replaceFirst(" ", "");
        return time;
    }

    public static Long parsePeriod(String period) {
        if (period == null) {
            return null;
        }
        Matcher matcher = periodPattern.matcher(period.toLowerCase(Locale.ENGLISH));
        Instant instant = Instant.EPOCH;
        while (matcher.find()) {
            int num = Integer.parseInt(matcher.group(1));
            String typ = matcher.group(2);
            switch (typ.hashCode()) {
                case 100:
                    if (!typ.equals("d")) {
                        break;
                    } else {
                        instant = instant.plus((TemporalAmount) Duration.ofDays(num));
                        break;
                    }
                case 104:
                    if (!typ.equals("h")) {
                        break;
                    } else {
                        instant = instant.plus((TemporalAmount) Duration.ofHours(num));
                        break;
                    }
                case 109:
                    if (!typ.equals("m")) {
                        break;
                    } else {
                        instant = instant.plus((TemporalAmount) Duration.ofMinutes(num));
                        break;
                    }
            }
        }
        return Long.valueOf(instant.toEpochMilli());
    }
}