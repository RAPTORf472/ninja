package me.raptor.ninja;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class DeadShot implements Listener{
	Ninja plugin;
	int i = 0;
	public DeadShot(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	ArrayList<String> ds = new ArrayList<String>();
	public String prefix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Ninja" + ChatColor.WHITE + "" + ChatColor.BOLD+ "] ";
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Location l = p.getLocation().add(0, 1.2, 0);
		PlayerInventory pi = p.getInventory();
		if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (p.isSneaking() == false) return;
			if (Cooldown.isOnCooldown(p, "DeadShot")) {
				p.sendMessage(prefix() + "Deadshot will available again in " + Cooldown.getTimeLeft(p, "DeadShot"));
				return;
			}
			EquipmentSlot hand = e.getHand();
			if (!hand.equals(EquipmentSlot.HAND)) return;

			if (pi.getChestplate() == null || pi.getChestplate().getItemMeta() == null || pi.getChestplate().getItemMeta().getDisplayName() == null) return;
			if (pi.getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lArrowproof Chestplate")) {
				if (pi.getLeggings() == null || pi.getLeggings().getItemMeta() == null || pi.getLeggings().getItemMeta().getDisplayName() == null) return;
				if (pi.getLeggings().getItemMeta().getDisplayName().equals("§c§lFine Leggings")) {
					if (pi.getHelmet() == null || pi.getHelmet().getItemMeta() == null || pi.getHelmet().getItemMeta().getDisplayName() == null) return;
					if (pi.getHelmet().getItemMeta().getDisplayName().equals("§c§lIron Visor")) {
						if (pi.getBoots() == null || pi.getBoots().getItemMeta() == null || pi.getBoots().getItemMeta().getDisplayName() == null) return;
						if (pi.getBoots().getItemMeta().getDisplayName().equals("§c§l'Sneak'ers")) {
							for (int i = -2; i < 3; i++) {
								l.setYaw(p.getLocation().getYaw() + i * 8);
								p.launchProjectile(Arrow.class).setVelocity(l.getDirection().multiply(5));
							}
							ds.add(p.getName());
							if (i == 2) {
								i = 0;
								Cooldown.setCooldown(p, "DeadShot", 45);
								p.sendMessage(prefix() + "Deadshot's over!");
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									public void run() {
										p.sendMessage(prefix() + "Deadshot available again");
										return;
									}
								}, 20 * 45);
							} else {
								i++;
new BukkitRunnable() {
	public void run() {
		if (i == 0) return;
							Cooldown.setCooldown(p, "DeadShot", 45);
							p.sendMessage(prefix() + "Deadshot's over!");
							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								public void run() {
									p.sendMessage(prefix() + "Deadshot available again");
									return;
								}
							}, 20 * 45);
							}
}.runTaskLater(plugin, 100);
						}
						}

							
						}
		}
	}
}
		}
	
	@EventHandler
	public void onNinjaShoot(ProjectileLaunchEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player)) return;
		if (!(e.getEntity() instanceof Arrow)) return;
		if (ds.contains(((Player) e.getEntity().getShooter()).getName())) {
			e.getEntity().setCustomName("kunai");
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					e.getEntity().remove();
				}
			}, 20 * 20);
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onNinjaShoot(PlayerPickupArrowEvent e) {
		Arrow a = (Arrow) e.getArrow();
		if (a.getCustomName() == null) return;
		if (a.getCustomName().equals("kunai")) e.setCancelled(true);
	}
	@EventHandler
	public void onArrowHit(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Arrow)) return;
		if (!(e.getEntity() instanceof LivingEntity)) return;
		Arrow a = (Arrow) e.getDamager();
		if (a.getCustomName() == null) return;
		if (a.getCustomName().equals("kunai")) {
			Random r = new Random();
			int ran = r.nextInt(100);
			if (ran < 10) {
			((Damageable) e.getEntity()).setHealth(((Damageable) e.getEntity()).getHealth() / 4);
			((Player) a.getShooter()).sendMessage(prefix() + "Deadshot!");
			((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 10, 1));
			return;
			}
			e.setDamage(10);
	}
	}
		
	}

