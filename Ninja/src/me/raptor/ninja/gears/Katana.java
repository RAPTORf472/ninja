package me.raptor.ninja.gears;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.ninja.Ninja;
import net.md_5.bungee.api.ChatColor;

public class Katana extends ItemStack implements Listener{
	Ninja plugin;
	public Katana(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	public Katana() {
		super(Material.IRON_SWORD);
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Keen Katana");
		im.setLore(Arrays.asList(ChatColor.RED + "An epic item of an epic set!",
				ChatColor.RED + "" + ChatColor.BOLD + "Made of unbreakable steel",
				ChatColor.GOLD + "" + ChatColor.BOLD + "SPECIAL: Multihit: " + ChatColor.GRAY + "Has a 20% of performing a combo of hits"));
	    im.setUnbreakable(true);
	    im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);


		this.setItemMeta(im);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
		this.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 4);
	}
    int i = 0;
	public String prefix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Ninja" + ChatColor.WHITE + "" + ChatColor.BOLD+ "] ";
	}
	@EventHandler
	public void onNinjaAttack(EntityDamageByEntityEvent e) {

	
		if (!(e.getDamager() instanceof Player)) return;

		Player p = (Player) e.getDamager();
if (e.getCause() == DamageCause.THORNS) return;
		PlayerInventory pi = p.getInventory();
		if (pi.getItemInMainHand() == null || pi.getItemInMainHand().getType() != Material.IRON_SWORD) return;

		ItemStack sword = pi.getItemInMainHand();
		if (sword.getItemMeta() == null || sword.getItemMeta().getDisplayName() == null) return;
		if (sword.getItemMeta().getDisplayName().equals("§c§lKeen Katana")) {

			Random r = new Random();
			int n = r.nextInt(100);
			if (n > 20) return;
p.sendMessage(prefix() + "Combo!");
				new BukkitRunnable( ) {
					public void run() {
						if (i == 3 || e.getEntity().isDead() == true) {
							this.cancel();
							i = 0;
						}
						
						i++;
						((Damageable) e.getEntity()).damage((e.getDamage()));
						e.getEntity().setVelocity(e.getEntity().getLocation().getDirection().multiply(-0.25));

					}

				}.runTaskTimer(plugin, 0, 10);
				



			
	}

}
}
