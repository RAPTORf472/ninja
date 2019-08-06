package me.raptor.ninja.gears;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.raptor.ninja.Ninja;
import net.md_5.bungee.api.ChatColor;

public class Sneakers extends ItemStack implements Listener{
	Ninja plugin;
	public Sneakers(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	public Sneakers() {
		super(Material.LEATHER_BOOTS);
		ItemMeta bt = this.getItemMeta();
		LeatherArmorMeta lam = (LeatherArmorMeta) bt;
		lam.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "'Sneak'ers");
		lam.setLore(Arrays.asList(ChatColor.RED + "An epic item of an epic set!",
				ChatColor.RED + "" + ChatColor.BOLD + "Will increase your speed by 20%",
				ChatColor.RED + "" + ChatColor.BOLD + "Immune to fall damage!",
				ChatColor.GOLD + "" + ChatColor.BOLD + "SPECIAL: DOUBLE JUMP: " + ChatColor.GRAY + "Give you the ability to double jump"));
	    lam.setColor(Color.BLACK);
	    lam.setUnbreakable(true);
	    lam.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    lam.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
	    lam.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		this.setItemMeta(lam);
		this.addUnsafeEnchantment(Enchantment.FROST_WALKER, 4);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
		this.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 4);
	}
	@EventHandler
	public void onNinjaWalk(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		PlayerInventory pi = p.getInventory();
		if (pi.getBoots() == null || pi.getBoots().getItemMeta() == null || pi.getBoots().getItemMeta().getDisplayName() == null || !(pi.getBoots().getItemMeta().getDisplayName().equals("§c§l'Sneak'ers"))) {
			p.removePotionEffect(PotionEffectType.SPEED);
			return;
		}
		if (pi.getBoots().getItemMeta().getDisplayName().equals("§c§l'Sneak'ers")) {
			if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
			return;
		}
			
		}

	
	}
	
	@EventHandler
	public void onNinjaDoubleJump(PlayerToggleFlightEvent e) {

		Player p = e.getPlayer();
		PlayerInventory pi = p.getInventory();
		if (pi.getBoots() == null || pi.getBoots().getItemMeta() == null || pi.getBoots().getItemMeta().getDisplayName() == null) return;
		if (pi.getBoots().getItemMeta().getDisplayName().equals("§c§l'Sneak'ers")) {
		if (p.getGameMode() == GameMode.CREATIVE) return;
		if (p.isOnGround() == true) return;
		e.setCancelled(true);
		p.setAllowFlight(false);
		p.setFlying(false);
		Vector v = p.getLocation().getDirection().multiply(0.25).setY(0.5);
		p.setVelocity(v);
		}
	}
		@EventHandler
		public void onNinjaDoubleJump(PlayerMoveEvent e) {

			Player p = e.getPlayer();
			PlayerInventory pi = p.getInventory();
			
			if (pi.getBoots() == null || pi.getBoots().getItemMeta() == null || pi.getBoots().getItemMeta().getDisplayName() == null) {
				p.setAllowFlight(false);
				return;
			}
			if (pi.getBoots().getItemMeta().getDisplayName().equals("§c§l'Sneak'ers")) {
			if (p.getGameMode() == GameMode.CREATIVE) return;
			if (p.isFlying() == true) {
				p.setFlying(false);
				return;
			}
		
			if (p.isOnGround() == false) return;
			if (e.getFrom().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) return;
				p.setAllowFlight(true);
			}
		}

}
