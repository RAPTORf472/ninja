package me.raptor.ninja.gears;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.raptor.ninja.Ninja;
import net.md_5.bungee.api.ChatColor;

public class SenseisBow extends ItemStack implements Listener{
	Ninja plugin;
	ArrayList<String> shoot = new ArrayList<String>();
	float force;
	int i = 0;
	public SenseisBow(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	public SenseisBow() {
		super(Material.BOW);
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Sensei's Bow");
		im.setLore(Arrays.asList(ChatColor.RED + "An epic item of an epic set!",
				ChatColor.RED + "" + ChatColor.BOLD + "This bow was made of epic wood",
				ChatColor.RED + "" + ChatColor.BOLD + "Highlight the enemies that is hit with this bow",
				ChatColor.GOLD + "" + ChatColor.BOLD + "SPECIAL: Master of bow shooting: " + ChatColor.GRAY + "Has a 30% chance to",
				ChatColor.GRAY	+ " shoot 5 arrows at a time"));
	    im.setUnbreakable(true);
	    im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);


		this.setItemMeta(im);
		this.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 10);
		this.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
	}
	@EventHandler
	public void onNinjaShot(EntityShootBowEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		ItemStack b = e.getBow();
		if (b.getItemMeta() == null || b.getItemMeta().getDisplayName() == null) return;
		if (b.getItemMeta().getDisplayName().equals("§c§lSensei's Bow")) {
			i++;
			if (i == 4) {
				i = 0;
			force = e.getForce();
			if (!shoot.contains(p.getName()))
			shoot.add(p.getName());
e.setCancelled(true);
Location l = p.getLocation().add(0, 1.2, 0);
p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
		for (int i = -2; i < 3; i++) {
			e.getProjectile().setCustomName(String.valueOf(i));
			l.setYaw(p.getLocation().getYaw() + i * 8);
			p.launchProjectile(Arrow.class).setVelocity(l.getDirection().multiply(e.getForce() * 3.3));

		}
	}
	}
	}
	@EventHandler
	public void onNinjaShoot(ProjectileLaunchEvent e) {
		if (!(e.getEntity() instanceof Arrow)) return;
		Arrow a = (Arrow) e.getEntity();
		if (!(a.getShooter() instanceof Player)) return;
		Player p = (Player) a.getShooter();
		@SuppressWarnings("deprecation")
		ItemStack bow = p.getInventory().getItemInHand();
		if (bow.getItemMeta() == null || bow.getItemMeta().getDisplayName() == null) return;
		if (bow.getItemMeta().getDisplayName().equals("§c§lSensei's Bow")) {
			if (!shoot.contains(p.getName())) return;
			a.setCustomName("arrow");
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					e.getEntity().remove();
				}
			}, 20 * 20);
		}
	}
	@EventHandler
	public void onNinjaShoot(ProjectileHitEvent e) {
		if (e.getHitEntity() == null) return;
		if (!(e.getEntity() instanceof Arrow)) return;

		Arrow a = (Arrow) e.getEntity();
		if (!(a.getShooter() instanceof Player)) return;
		Player p = (Player) a.getShooter();
		if (!shoot.contains(p.getName())) return;
		for (int i = -2; i < 3; i ++) {
			if (a.getCustomName() == null) return;
			if (a.getCustomName().equals("arrow"));
			a.remove();
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onArrowPickup(PlayerPickupArrowEvent e) {
		Arrow a = (Arrow) e.getArrow();
		if (a.getCustomName() == null) return;
		if (a.getCustomName().equals("arrow")) e.setCancelled(true);
	}
	@EventHandler
	public void onArrowHit(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Arrow)) return;
		if (!(e.getEntity() instanceof LivingEntity)) return;
		Arrow a = (Arrow) e.getDamager();
		if (a.getCustomName() == null) return;
		if (a.getCustomName().equals("arrow")) {
			e.setDamage(force * 20);
			((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 5, 0));

	}
	}
	}


