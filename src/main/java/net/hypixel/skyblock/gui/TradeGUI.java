package net.hypixel.skyblock.gui;

import net.hypixel.skyblock.SkyBlock;
import net.hypixel.skyblock.item.SItem;
import net.hypixel.skyblock.item.Untradeable;
import net.hypixel.skyblock.user.User;
import net.hypixel.skyblock.util.SUtil;
import net.hypixel.skyblock.util.Sputnik;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagLong;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class TradeGUI extends GUI {
    private final UUID tradeUUID;
    public static final Map<UUID, List<ItemStack>> itemOfferP1 = new HashMap<>();
    public static final Map<UUID, List<ItemStack>> itemOfferP2 = new HashMap<>();
    public static final Map<UUID, Player> player1 = new HashMap<>();
    public static final Map<UUID, Player> player2 = new HashMap<>();
    public static final Map<UUID, Integer> tradeCountdown = new HashMap<>();
    private final int[] ls;
    private final int[] rs;

    public void fillFrom(final Inventory i, final int startFromSlot, final int height, final ItemStack stacc) {
        i.setItem(startFromSlot, stacc);
        i.setItem(startFromSlot + 9, stacc);
        i.setItem(startFromSlot + 9 + 9, stacc);
        i.setItem(startFromSlot + 9 + 9 + 9, stacc);
        i.setItem(startFromSlot + 9 + 9 + 9 + 9, stacc);
    }

    public TradeGUI() {
        this(UUID.randomUUID());
    }

    public TradeGUI(final UUID uuid) {
        super("You                  " + TradeGUI.player2.get(uuid).getName(), 45);
        this.ls = new int[]{0, 1, 2, 3, 9, 10, 11, 12, 18, 19, 20, 21, 27, 28, 29, 30};
        this.rs = new int[]{5, 6, 7, 8, 14, 15, 16, 17, 23, 24, 25, 26, 32, 33, 34, 35};
        this.tradeUUID = uuid;
        if (!TradeGUI.itemOfferP1.containsKey(uuid) && TradeGUI.itemOfferP1.get(uuid) == null) {
            TradeGUI.itemOfferP1.put(uuid, new ArrayList<ItemStack>());
        }
        if (!TradeGUI.itemOfferP2.containsKey(uuid) && TradeGUI.itemOfferP2.get(uuid) == null) {
            TradeGUI.itemOfferP2.put(uuid, new ArrayList<ItemStack>());
        }
    }

    @Override
    public void onOpen(final GUIOpenEvent e) {
        final Player player = e.getPlayer();
        final Inventory i = e.getInventory();
        final ItemStack stk = SUtil.getSingleLoreStack(ChatColor.GRAY + "⇦ Your stuff", Material.STAINED_GLASS_PANE, (short) 0, 1, ChatColor.GRAY + "Their stuff ⇨");
        stk.setDurability((short) 7);
        this.fillFrom(i, 4, 5, stk);
        TradeMenu.tradeP1Ready.put(this.tradeUUID, false);
        this.set(new GUIClickableItem() {
            @Override
            public void run(final InventoryClickEvent e) {
                if (TradeMenu.tradeP1Countdown.containsKey(TradeGUI.this.tradeUUID) && TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) <= 0 && (TradeGUI.itemOfferP1.get(TradeGUI.this.tradeUUID).size() > 0 || TradeGUI.itemOfferP2.get(TradeGUI.this.tradeUUID).size() > 0) && !TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID)) {
                    TradeMenu.tradeP1Ready.put(TradeGUI.this.tradeUUID, true);
                    TradeGUI.player2.get(TradeGUI.this.tradeUUID).playSound(TradeGUI.player2.get(TradeGUI.this.tradeUUID).getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                    TradeGUI.player1.get(TradeGUI.this.tradeUUID).playSound(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                }
            }

            @Override
            public int getSlot() {
                return 39;
            }

            @Override
            public ItemStack getItem() {
                final ItemStack stack = SUtil.getStack(Sputnik.trans("&aTrading!"), Material.STAINED_CLAY, (short) 13, 1, ChatColor.GRAY + "Click an item in your", ChatColor.GRAY + "inventory to offer it for", ChatColor.GRAY + "trade.");
                return stack;
            }
        });
        this.set(new GUIClickableItem() {
            @Override
            public void run(final InventoryClickEvent e) {
            }

            @Override
            public int getSlot() {
                return 41;
            }

            @Override
            public ItemStack getItem() {
                final ItemStack stack = SUtil.getStack(Sputnik.trans("&eNew deal"), Material.INK_SACK, (short) 8, 1, ChatColor.GRAY + "Trading with " + TradeGUI.player2.get(TradeGUI.this.tradeUUID).getName() + ".");
                return stack;
            }
        });
        new BukkitRunnable() {
            public void run() {
                if (TradeGUI.this != GUI_MAP.get(player.getUniqueId())) {
                    this.cancel();
                    return;
                }
                if (TradeMenu.tradeP1Countdown.containsKey(TradeGUI.this.tradeUUID) && TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) > 0) {
                    TradeMenu.tradeP1Countdown.put(TradeGUI.this.tradeUUID, TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) - 1);
                }
            }
        }.runTaskTimer(SkyBlock.getPlugin(), 0L, 20L);
        new BukkitRunnable() {
            public void run() {
                if (TradeGUI.this != GUI_MAP.get(player.getUniqueId())) {
                    this.cancel();
                    return;
                }
                if (TradeMenu.tradeP2Ready.containsKey(TradeGUI.this.tradeUUID) && TradeMenu.tradeP1Countdown.containsKey(TradeGUI.this.tradeUUID)) {
                    if (TradeMenu.tradeP2Ready.get(TradeGUI.this.tradeUUID)) {
                        i.setItem(41, SUtil.getStack(Sputnik.trans("&aOther player confirmed!"), Material.INK_SACK, (short) 10, 1, ChatColor.GRAY + "Trading with " + TradeGUI.player2.get(TradeGUI.this.tradeUUID).getName() + ".", ChatColor.GRAY + "Waiting for you to confirm..."));
                    } else if (TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) > 0 && !TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID)) {
                        i.setItem(41, SUtil.getStack(Sputnik.trans("&eDeal timer..."), Material.INK_SACK, (short) 8, 1, ChatColor.GRAY + "Trading with " + TradeGUI.player2.get(TradeGUI.this.tradeUUID).getName() + ".", ChatColor.GRAY + "The trade changed recently."));
                    } else if (TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID)) {
                        i.setItem(41, SUtil.getStack(Sputnik.trans("&ePending their confirm."), Material.INK_SACK, (short) 8, 1, ChatColor.GRAY + "Trading with " + TradeGUI.player2.get(TradeGUI.this.tradeUUID).getName() + ".", ChatColor.GRAY + "Waiting for them to confirm."));
                    } else {
                        i.setItem(41, SUtil.getStack(Sputnik.trans("&eNew deal"), Material.INK_SACK, (short) 8, 1, ChatColor.GRAY + "Trading with " + TradeGUI.player2.get(TradeGUI.this.tradeUUID).getName() + "."));
                    }
                }
                if (TradeMenu.tradeP1Countdown.containsKey(TradeGUI.this.tradeUUID)) {
                    if (TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) > 0) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&eDeal timer! &7(&e" + TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) + "&7)"), Material.STAINED_CLAY, (short) 4, TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID), ChatColor.GRAY + "The trade recently changed.", ChatColor.GRAY + "Please review it before", ChatColor.GRAY + "accepting."));
                        TradeMenu.tradeP1Ready.put(TradeGUI.this.tradeUUID, false);
                    } else if (TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID)) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&aDeal accepted!"), Material.STAINED_CLAY, (short) 13, 1, ChatColor.GRAY + "You accepted the trade.", ChatColor.GRAY + "wait for the other party to", ChatColor.GRAY + "accept."));
                    } else if (TradeGUI.itemOfferP1.get(TradeGUI.this.tradeUUID).size() <= 0 && TradeGUI.itemOfferP2.get(TradeGUI.this.tradeUUID).size() <= 0) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&aTrading!"), Material.STAINED_CLAY, (short) 13, 1, ChatColor.GRAY + "Click an item in your", ChatColor.GRAY + "inventory to offer it for", ChatColor.GRAY + "trade."));
                    } else if (TradeGUI.itemOfferP2.get(TradeGUI.this.tradeUUID).size() <= 0 && !TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID)) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&eWarning!"), Material.STAINED_CLAY, (short) 1, 1, ChatColor.GRAY + "You are offering items", ChatColor.GRAY + "without getting anything in", ChatColor.GRAY + "return.", " ", ChatColor.YELLOW + "Click to accept anyway!"));
                    } else if (TradeGUI.itemOfferP1.get(TradeGUI.this.tradeUUID).size() <= 0) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&bGift!"), Material.STAINED_CLAY, (short) 11, 1, ChatColor.GRAY + "You are receiving items", ChatColor.GRAY + "without offering anything in", ChatColor.GRAY + "return.", " ", ChatColor.YELLOW + "Click to accept!"));
                    } else if (TradeGUI.itemOfferP1.get(TradeGUI.this.tradeUUID).size() > 0 && TradeGUI.itemOfferP2.get(TradeGUI.this.tradeUUID).size() > 0) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&eDeal!"), Material.STAINED_CLAY, (short) 5, 1, ChatColor.GRAY + "All trades are final and", ChatColor.GRAY + "cannot be reverted.", " ", ChatColor.GREEN + "Make sure to review the", ChatColor.GREEN + "trade before accepting", " ", ChatColor.YELLOW + "Click to accept the trade!"));
                    }
                }
                if (TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID) && TradeMenu.tradeP2Ready.get(TradeGUI.this.tradeUUID)) {
                    this.cancel();
                    TradeMenu.successTrade.put(TradeGUI.this.tradeUUID, true);
                    TradeMenu.triggerCloseEvent(TradeGUI.this.tradeUUID, true, e.getPlayer());
                }
                final List<ItemStack> stl1 = TradeGUI.itemOfferP1.get(TradeGUI.this.tradeUUID);
                final List<ItemStack> stl2 = TradeGUI.itemOfferP2.get(TradeGUI.this.tradeUUID);
                final ItemStack stk = SUtil.getSingleLoreStack(ChatColor.GRAY + "⇦ Your stuff", Material.STAINED_GLASS_PANE, (short) 0, 1, ChatColor.GRAY + "Their stuff ⇨");
                stk.setDurability((short) 7);
                TradeGUI.this.fillFrom(i, 4, 5, stk);
                int a = -1;
                for (final int slot : TradeGUI.this.ls) {
                    if (a < stl1.size() - 1) {
                        ++a;
                        if (SItem.find(stl1.get(a)) != null) {
                            i.setItem(slot, User.getUser(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getUniqueId()).updateItemBoost(SItem.find(stl1.get(a))));
                        } else {
                            i.setItem(slot, stl1.get(a));
                        }
                    } else {
                        i.setItem(slot, null);
                    }
                }
                int b = -1;
                for (final int slot2 : TradeGUI.this.rs) {
                    if (b < stl2.size() - 1) {
                        ++b;
                        if (SItem.find(stl2.get(b)) != null) {
                            i.setItem(slot2, User.getUser(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getUniqueId()).updateItemBoost(SItem.find(stl2.get(b))));
                        } else {
                            i.setItem(slot2, stl2.get(b));
                        }
                    } else {
                        i.setItem(slot2, null);
                    }
                }
            }
        }.runTaskTimer(SkyBlock.getPlugin(), 0L, 1L);
        new BukkitRunnable() {
            public void run() {
                if (!TradeGUI.player1.get(TradeGUI.this.tradeUUID).isOnline() || !TradeGUI.player1.get(TradeGUI.this.tradeUUID).getWorld().equals(TradeGUI.player2.get(TradeGUI.this.tradeUUID).getWorld())) {
                    this.cancel();
                    TradeMenu.triggerCloseEvent(TradeGUI.this.tradeUUID, false, TradeGUI.player1.get(TradeGUI.this.tradeUUID));
                } else if (!TradeGUI.player2.get(TradeGUI.this.tradeUUID).isOnline() || !TradeGUI.player2.get(TradeGUI.this.tradeUUID).getWorld().equals(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getWorld())) {
                    this.cancel();
                    TradeMenu.triggerCloseEvent(TradeGUI.this.tradeUUID, false, TradeGUI.player2.get(TradeGUI.this.tradeUUID));
                }
            }
        }.runTaskTimer(SkyBlock.getPlugin(), 0L, 1L);
        this.set(new GUISignItem() {
            @Override
            public GUI onSignClose(final String query, final Player target) {
                if (target != TradeGUI.player1.get(TradeGUI.this.tradeUUID)) {
                    return null;
                }
                if (query == "$canc") {
                    return new TradeGUI(TradeGUI.this.tradeUUID);
                }
                try {
                    final long add = Long.parseLong(query);

                    final double cur = User.getUser(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getUniqueId()).getBits();
                    if (add <= 0L) {
                        TradeGUI.player1.get(TradeGUI.this.tradeUUID).playSound(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                        player.sendMessage(ChatColor.RED + "Couldn't validate this Coins amount!");
                        return new TradeGUI(TradeGUI.this.tradeUUID);
                    }
                    if (add > cur) {
                        TradeGUI.player1.get(TradeGUI.this.tradeUUID).playSound(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                        player.sendMessage(ChatColor.RED + "You don't have that much Coins for this.");
                        return new TradeGUI(TradeGUI.this.tradeUUID);
                    }
                    if (TradeGUI.itemOfferP1.get(TradeGUI.this.tradeUUID).size() < 16) {
                        if (User.getUser(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getUniqueId()).subBits(add)) {
                            TradeGUI.player1.get(TradeGUI.this.tradeUUID).playSound(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
                            TradeGUI.player2.get(TradeGUI.this.tradeUUID).playSound(TradeGUI.player2.get(TradeGUI.this.tradeUUID).getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
                            final long stackamount = Math.min(64L, Math.max(10000L, add) / 10000L);
                            ItemStack coinsStack = SUtil.getSkullURLStack(ChatColor.AQUA + Sputnik.formatFull((float) add) + " Coins", "7b951fed6a7b2cbc2036916dec7a46c4a56481564d14f945b6ebc03382766d3b", (int) stackamount, ChatColor.GRAY + "Lump-sum amount");
                            final net.minecraft.server.v1_8_R3.ItemStack tagStack = CraftItemStack.asNMSCopy(coinsStack);
                            final NBTTagCompound tagCompound = tagStack.hasTag() ? tagStack.getTag() : new NBTTagCompound();
                            tagCompound.set("data_bits", new NBTTagLong(add));
                            tagStack.setTag(tagCompound);
                            coinsStack = CraftItemStack.asBukkitCopy(tagStack);
                            TradeGUI.itemOfferP1.get(TradeGUI.this.tradeUUID).add(coinsStack);
                            TradeMenu.tradeP1Countdown.put(TradeGUI.this.tradeUUID, 3);
                            TradeMenu.tradeP2Countdown.put(TradeGUI.this.tradeUUID, 3);
                        } else {
                            TradeGUI.player1.get(TradeGUI.this.tradeUUID).playSound(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                            player.sendMessage(ChatColor.RED + "An unexpected error occured while attempting to access the economy!");
                        }
                    }
                    return new TradeGUI(TradeGUI.this.tradeUUID);
                } catch (final NumberFormatException ex) {
                    player.sendMessage(ChatColor.RED + "Couldn't parse this Coins amount!");
                    TradeGUI.player1.get(TradeGUI.this.tradeUUID).playSound(TradeGUI.player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                    return new TradeGUI(TradeGUI.this.tradeUUID);
                }
            }

            @Override
            public void run(final InventoryClickEvent e) {
                player.playSound(player.getLocation(), Sound.CLICK, 1.0f, 1.0f);
            }

            @Override
            public int getSlot() {
                return 36;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getSkullURLStack(ChatColor.AQUA + "Coins transaction", "7b951fed6a7b2cbc2036916dec7a46c4a56481564d14f945b6ebc03382766d3b", 1, ChatColor.GRAY + " ", ChatColor.YELLOW + "Click to add bits!");
            }

            @Override
            public UUID inti() {
                return TradeGUI.this.tradeUUID;
            }
        });
    }

    @Override
    public void onBottomClick(final InventoryClickEvent e) {
        if (e.getSlot() < 0) {
            e.setCancelled(true);
            return;
        }
        ItemStack cs = null;
        if (TradeGUI.player1.get(this.tradeUUID).getInventory().getItem(e.getSlot()) != null) {
            cs = TradeGUI.player1.get(this.tradeUUID).getInventory().getItem(e.getSlot());
        }
        if (cs != null) {
            final SItem sItem = SItem.find(cs);
            if (sItem != null && SItem.isSpecItem(cs)) {
                if (!(sItem.getType().getGenericInstance() instanceof Untradeable)) {
                    if (TradeGUI.itemOfferP1.get(this.tradeUUID).size() < 16) {
                        TradeGUI.itemOfferP1.get(this.tradeUUID).add(cs);
                        TradeGUI.player1.get(this.tradeUUID).playSound(TradeGUI.player1.get(this.tradeUUID).getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
                        TradeGUI.player2.get(this.tradeUUID).playSound(TradeGUI.player2.get(this.tradeUUID).getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
                        TradeGUI.player1.get(this.tradeUUID).getInventory().setItem(e.getSlot(), null);
                        TradeMenu.tradeP1Countdown.put(this.tradeUUID, 3);
                        TradeMenu.tradeP2Countdown.put(this.tradeUUID, 3);
                    } else {
                        TradeGUI.player1.get(this.tradeUUID).playSound(TradeGUI.player1.get(this.tradeUUID).getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                        TradeGUI.player1.get(this.tradeUUID).sendMessage(Sputnik.trans("&c&lIT'S FULL! &7Your trade window is full!"));
                    }
                } else {
                    TradeGUI.player1.get(this.tradeUUID).sendMessage(Sputnik.trans("&cYou cannot trade this item!"));
                    TradeGUI.player1.get(this.tradeUUID).playSound(TradeGUI.player1.get(this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                }
            } else {
                TradeGUI.player1.get(this.tradeUUID).sendMessage(Sputnik.trans("&cYou cannot trade this item!"));
                TradeGUI.player1.get(this.tradeUUID).playSound(TradeGUI.player1.get(this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
            }
        }
    }

    @Override
    public void onTopClick(final InventoryClickEvent e) {
        if (TradeGUI.itemOfferP1.get(this.tradeUUID).contains(e.getInventory().getItem(e.getSlot())) && isContain(this.ls, e.getSlot())) {
            TradeGUI.itemOfferP1.get(this.tradeUUID).remove(e.getInventory().getItem(e.getSlot()));
            final ItemStack stack = e.getInventory().getItem(e.getSlot());
            TradeGUI.player1.get(this.tradeUUID).playSound(TradeGUI.player1.get(this.tradeUUID).getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
            final net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
            if (!nmsStack.getTag().hasKey("data_bits")) {
                Sputnik.smartGiveItem(stack, TradeGUI.player1.get(this.tradeUUID));
            } else {
                User.getUser(TradeGUI.player1.get(tradeUUID).getUniqueId()).addCoins(nmsStack.getTag().getLong("data_bits"));
            }
            TradeMenu.tradeP1Countdown.put(this.tradeUUID, 3);
            TradeMenu.tradeP2Countdown.put(this.tradeUUID, 3);
        }
    }

    @Override
    public void onClose(final InventoryCloseEvent e) {
        TradeMenu.triggerCloseEvent(this.tradeUUID, false, (Player) e.getPlayer());
        ((Player) e.getPlayer()).playSound(e.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        GUIListener.QUERY_MAP.remove(e.getPlayer().getUniqueId());
        GUIListener.QUERY_MAPPING.remove(e.getPlayer().getUniqueId());
    }

    public static boolean isContain(final int[] array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

}
