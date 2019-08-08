package me.raptor.ninja.gears;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import me.raptor.ninja.Ninja;
import net.md_5.bungee.api.ChatColor;

public class GrapplingHook extends ItemStack implements Listener {
	Ninja plugin;
	Location loc;
	ArrayList<String> hook = new ArrayList<String>(), inte = new ArrayList<String>();
	public String prefix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Ninja" + ChatColor.WHITE + "" + ChatColor.BOLD+ "] ";
	}
	public GrapplingHook(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	public GrapplingHook() {
		super(Material.FISHING_ROD);
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Steel Hook");
		im.setLore(Arrays.asList(ChatColor.RED + "An epic item of an epic set!",
				ChatColor.RED + "" + ChatColor.BOLD + "This grappling hook was made by some epic toolsmith",
				ChatColor.RED + "" + ChatColor.BOLD + "Dealt some damage to enemies and fling you to the hook's location",
				ChatColor.GOLD + "" + ChatColor.BOLD + "SPECIAL: Interference: " + ChatColor.GRAY + "Have a 10% chance to make enemies",
				ChatColor.GRAY + "unable to use shield for 10 seconds"));
	    im.setUnbreakable(true);
	    im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);


		this.setItemMeta(im);
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
	}
	@EventHandler
	public void onHookHit(ProjectileHitEvent e) {
		if (e.getHitBlock() == null) return;
		if (!(e.getEntity() instanceof FishHook)) return;
loc = e.getEntity().getLocation();
Player p = (Player) e.getEntity().getShooter();
if (!hook.contains(p.getName()))
hook.add(p.getName());
	}
	@EventHandler
	public void onNinjaHook(PlayerInteractEvent e) {
		   Player p = (Player) e.getPlayer();

		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {

		if (!hook.contains(p.getName())) return;
        if (p.getInventory().getItemInMainHand() == null) return;
        ItemStack a = p.getInventory().getItemInMainHand();
        if (a.getItemMeta() == null || a.getItemMeta().getDisplayName() == null) return;
		if (a.getItemMeta().getDisplayName().equals("§c§lSteel Hook")) {

		   Vector v = (p.getLocation().toVector()).subtract(loc.toVector());
		   p.setVelocity(v.multiply(-0.25).setY(v.getY() / 1.5 + 0.25));
		   hook.remove(p.getName());
	}
		}
	}
	@EventHandler
	public void onHookHit2(ProjectileHitEvent e) {
		if (e.getHitEntity() == null) return;
		if (!(e.getHitEntity() instanceof LivingEntity)) return;
			if (!(e.getEntity() instanceof FishHook)) return;
Player p = (Player) e.getEntity().getShooter();
ItemStack a = p.getInventory().getItemInMainHand();
if (a.getItemMeta() == null || a.getItemMeta().getDisplayName() == null) return;
if (a.getItemMeta().getDisplayName().equals("§c§lSteel Hook")) {
	if (e.getHitEntity() == p) return;
((LivingEntity) e.getHitEntity()).damage(3);
if (e.getHitEntity() instanceof Player) {
	if (!inte.contains(p.getName())) {
	Random r = new Random();
	int ran = r.nextInt(100);
	if (ran < 10) {
		p.sendMessage(prefix() + "Interferenced!");
		e.getHitEntity().sendMessage(prefix() + "You have been interferenced");
	inte.add(e.getHitEntity().getName());
	Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
		public void run() {
		if (inte.contains(e.getHitEntity().getName())) {
			inte.remove(e.getHitEntity().getName());
			e.getHitEntity().sendMessage(prefix() + "Interference over!");
			p.sendMessage(prefix() + e.getHitEntity().getName() + " is no more interference!");
			return;
		}

		}
	}, 20 * 10);
	}
	
}
}
}
	}
	@EventHandler
	public void onNinjaHook2(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			EquipmentSlot hand = e.getHand();
			if (hand.equals(EquipmentSlot.HAND) || hand.equals(EquipmentSlot.OFF_HAND)) {
		if (e.getItem() == null) return;
		if (e.getItem().getType() == Material.SHIELD) {
			if (inte.contains(e.getPlayer().getName())) {
				e.setUseItemInHand(Event.Result.DENY);
				return;
			}
			
		}
		}
		}
		}
	}

