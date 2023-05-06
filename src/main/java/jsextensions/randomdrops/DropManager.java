package jsextensions.randomdrops;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;

public class DropManager implements Listener {

    public DropManager(RandomDrops plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private Random random = new Random();
    public HashMap<Material, Material> dropMap = new HashMap<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) return;
        e.setDropItems(false);
        Material block = e.getBlock().getType();
        if (e.getBlock().getDrops().isEmpty()) return;
        if (block.isItem() && !block.isAir()) {
            Material dropMaterial = dropMap.get(block);
            if (dropMaterial == null || !dropMaterial.isItem()) {
                Material randomBlock;
                do {
                    randomBlock = Material.values()[random.nextInt(Material.values().length)];
                } while (!randomBlock.isItem() || e.getBlock().getDrops().isEmpty());
                dropMap.put(block, randomBlock);
                dropMaterial = randomBlock;
            }
            ItemStack drop = new ItemStack(dropMaterial, 1);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), drop);
        }
    }
}