package me.raptor.ninja;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class Blink implements Listener{
	Ninja plugin;
	public Blink(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	public String prefix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Ninja" + ChatColor.WHITE + "" + ChatColor.BOLD+ "] ";
	}
	@EventHandler
	public void onNinjaBlink(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player)) return;
		if (Cooldown.isOnCooldown(e.getPlayer(), "Blink")) {
			e.getPlayer().sendMessage(prefix() +  "Blink will available again in " + Cooldown.getTimeLeft(e.getPlayer(), "Blink"));
			return;
		}
		EquipmentSlot hand = e.getHand();
		if (!hand.equals(EquipmentSlot.HAND)) return;

		PlayerInventory pi = e.getPlayer().getInventory();
		if (pi.getChestplate() == null || pi.getChestplate().getItemMeta() == null || pi.getChestplate().getItemMeta().getDisplayName() == null) return;
		if (pi.getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lArrowproof Chestplate")) {
			if (pi.getLeggings() == null || pi.getLeggings().getItemMeta() == null || pi.getLeggings().getItemMeta().getDisplayName() == null) return;
			if (pi.getLeggings().getItemMeta().getDisplayName().equals("§c§lFine Leggings")) {
				if (pi.getHelmet() == null || pi.getHelmet().getItemMeta() == null || pi.getHelmet().getItemMeta().getDisplayName() == null) return;
				if (pi.getHelmet().getItemMeta().getDisplayName().equals("§c§lIron Visor")) {
					if (pi.getBoots() == null || pi.getBoots().getItemMeta() == null || pi.getBoots().getItemMeta().getDisplayName() == null) return;
					if (pi.getBoots().getItemMeta().getDisplayName().equals("§c§l'Sneak'ers")) {
		Player p1 = (Player) e.getRightClicked();
		Cooldown.setCooldown(e.getPlayer(), "Blink", 60);
		e.getPlayer().sendMessage(prefix() +  p1.getName() + " has been blinked");
		p1.sendMessage(prefix() + "You has been blinked");
		p1.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 15, 4), true);
		p1.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 0), true);
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				p1.sendMessage(prefix() + "Blink's over");
			}
		}, 20 * 15);
		}
				}
			}
			
		}
	}
}
