package me.raptor.ninja.gears;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.ninja.Ninja;
import net.md_5.bungee.api.ChatColor;

public class FineLeggings extends ItemStack implements Listener{
	Ninja plugin;
	ArrayList<String> startcon = new ArrayList<String>();
	ArrayList<String> coning = new ArrayList<String>();
	int i = 1;
	public FineLeggings(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	public FineLeggings() {
		super(Material.LEATHER_LEGGINGS);
		ItemMeta aam = this.getItemMeta();
		LeatherArmorMeta lam =  (LeatherArmorMeta) aam;
		lam.setColor(Color.BLACK);
		lam.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Fine Leggings");
		lam.setLore(Arrays.asList(ChatColor.RED + "An epic item of an epic set!",
				ChatColor.RED + "" + ChatColor.BOLD + "Damage back the enemies that hit you",
				ChatColor.GOLD + "" + ChatColor.BOLD + "SPECIAL: Concentrating: " + ChatColor.GRAY + "Sneaking for 10 seconds to regenerate",
				 ChatColor.GRAY + " health and makes incoming attacks reduces 40% damage!"));
	    lam.setUnbreakable(true);
	    lam.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    lam.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
	    lam.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);


		this.setItemMeta(lam);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 20);
		this.addUnsafeEnchantment(Enchantment.THORNS, 8);
	}
	public String prefix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Ninja" + ChatColor.WHITE + "" + ChatColor.BOLD+ "] ";
	}
	@EventHandler
	public void onNinjaConcentrating(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		PlayerInventory pi = p.getInventory();
	
		if (pi.getLeggings() == null || pi.getLeggings().getItemMeta() == null || pi.getLeggings().getItemMeta().getDisplayName() == null) return;
		if (pi.getLeggings().getItemMeta().getDisplayName().equals("§c§lFine Leggings")) {
			if (startcon.contains(p.getName())) return;
if (!startcon.contains(p.getName())) startcon.add(p.getName());
			new BukkitRunnable() {
				public void run() {
					if (p.isSneaking() == false) {
						if (startcon.contains(p.getName())) {
							startcon.remove(p.getName());
							i = 1;
							this.cancel();
							return;
						}
					}
					
						
					
					if (i == 20) {
						i = 1;
						this.cancel();
						if (startcon.contains(p.getName())) {
							startcon.remove(p.getName());
							coning.add(p.getName());
						}
					} else if (i > 8 && i % 2 == 0) {
						 p.sendMessage(prefix() + "You have been concentrating for " + i / 2 + " seconds");
					}
					if (startcon.contains(p.getName())) {		
					i++;
				} else {
					this.cancel();
					return;
				}
				}
	
			}.runTaskTimer(plugin, 0, 10);
			
	new BukkitRunnable() {

		public void run() {
if (coning.contains(p.getName())) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 2));
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
				p.sendMessage(prefix() + "Concentrated!");
				
				new BukkitRunnable() {
					public void run() {
						if (p.isSneaking() == false) {
							if (coning.contains(p.getName())) {
								coning.remove(p.getName());
								p.sendMessage(prefix() + "You are not anymore concentrating! Please do the whole procress again");
								p.removePotionEffect(PotionEffectType.REGENERATION);
								p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
								this.cancel();
								return;
							}

						}
						if (!coning.contains(p.getName())) {
							p.removePotionEffect(PotionEffectType.REGENERATION);
							p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
						this.cancel();
						return;
					}
					}
					}.runTaskTimer(plugin, 0, 20);
		}
		}
		}.runTaskLater(plugin, 20 * 10);
	
	
 } } 
	@EventHandler
	public void onNinjaConcentrating(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (startcon.contains(p.getName())) {
		if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ() ||  e.getFrom().getBlockY() != e.getTo().getBlockY()) {
			startcon.remove(p.getName());
			return;
		}
		} else if (coning.contains(p.getName())) {
			if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ() ||  e.getFrom().getBlockY() != e.getTo().getBlockY()) {
			coning.remove(p.getName());
			p.sendMessage(prefix() + "You are not anymore concentrating! Please do the whole procress again");
			return;
		}
		}
	}
	}
