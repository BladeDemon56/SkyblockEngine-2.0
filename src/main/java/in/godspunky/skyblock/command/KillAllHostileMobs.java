package in.godspunky.skyblock.command;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import in.godspunky.skyblock.util.Sputnik;

@CommandParameters(description = "Gets the NBT of your current item.", aliases = "kamh", permission = "spt.item")
public class KillAllHostileMobs extends SCommand {
    @Override
    public void run(final CommandSource sender, final String[] args) {
        final Player player = sender.getPlayer();
        if (player == null) {
            this.send(ChatColor.RED + "You can't use this command here!");
        }
        if (player.isOp()) {
            final World world = player.getWorld();
            for (final Entity entity : world.getEntities()) {
                if (entity instanceof Monster && !entity.hasMetadata("pets") && !entity.hasMetadata("Ire")) {
                    entity.remove();
                }
            }
            this.send(ChatColor.WHITE + "You removed all" + ChatColor.RED + " HOSTILE" + ChatColor.RESET + " mobs in this world.");
        } else {
            this.send(Sputnik.trans("&cYou cant use this, lol."));
        }
    }
}