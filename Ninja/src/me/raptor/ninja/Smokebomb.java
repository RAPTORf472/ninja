package me.raptor.ninja;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;





public class Smokebomb implements Listener {
	Ninja plugin;
	public Smokebomb(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	ArrayList<String> using = new ArrayList<String>();
	public String prefix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Ninja" + ChatColor.WHITE + "" + ChatColor.BOLD+ "] ";
	}
	@EventHandler
	public void onNinjaSmokebomb(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();
		if (p.isSneaking() == false) return;
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			EquipmentSlot hand = e.getHand();
			if (!hand.equals(EquipmentSlot.HAND)) return;
			if (Cooldown.isOnCooldown(p, "Smokebomb")) {
				p.sendMessage(prefix() + "Smokebomb will available again in " + Cooldown.getTimeLeft(p, "Smokebomb"));
				return;
			}
			if (using.contains(p.getName())) {
				p.sendMessage(prefix() + "You are using smokebomb!");
				return;
			}
			PlayerInventory pi = p.getInventory();
			if (pi.getChestplate() == null || pi.getChestplate().getItemMeta() == null || pi.getChestplate().getItemMeta().getDisplayName() == null) return;
			if (pi.getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lArrowproof Chestplate")) {
				if (pi.getLeggings() == null || pi.getLeggings().getItemMeta() == null || pi.getLeggings().getItemMeta().getDisplayName() == null) return;
				if (pi.getLeggings().getItemMeta().getDisplayName().equals("§c§lFine Leggings")) {
					if (pi.getHelmet() == null || pi.getHelmet().getItemMeta() == null || pi.getHelmet().getItemMeta().getDisplayName() == null) return;
					if (pi.getHelmet().getItemMeta().getDisplayName().equals("§c§lIron Visor")) {
						if (pi.getBoots() == null || pi.getBoots().getItemMeta() == null || pi.getBoots().getItemMeta().getDisplayName() == null) return;
						if (pi.getBoots().getItemMeta().getDisplayName().equals("§c§l'Sneak'ers")) {
							if (!p.hasPermission("ninja.smokebomb")) {
								p.sendMessage(prefix() + "You don't have permission to use Smokebomb!");
								return;
							}
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 15, 1), true);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 15, 2), true);
			p.sendMessage(prefix() + "Smokebomb!");
p.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, p.getLocation().getX(), p.getLocation().getY(),p.getLocation().getZ(), 1000, 1, 1, 1);


for (Player player : Bukkit.getServer().getOnlinePlayers()) {
	player.hidePlayer(plugin, p);
}
			using.add(p.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					using.remove(p.getName());
					p.sendMessage(prefix() + "Smokebomb's over!");
					for (Player player : Bukkit.getServer().getOnlinePlayers()) {
						player.showPlayer(plugin, p);
						
					}
					Cooldown.setCooldown(p, "Smokebomb", 120);
				}
			}, 20 * 15);

		}
	}
	}
	}
	}
	}
}
