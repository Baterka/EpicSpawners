package com.songoda.epicspawners.commands;

import com.songoda.core.commands.AbstractCommand;
import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.epicspawners.EpicSpawners;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class CommandSpawn extends AbstractCommand {

    private final EpicSpawners plugin;

    public CommandSpawn(EpicSpawners plugin) {
        super(CommandType.PLAYER_ONLY, "spawn");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;

        Block block = player.getTargetBlock(null, 200);

        if (CompatibleMaterial.getMaterial(block) != CompatibleMaterial.SPAWNER) {
            plugin.getLocale().newMessage("&cThat is not a spawner...").sendPrefixedMessage(player);
            return ReturnType.FAILURE;
        }

        plugin.getSpawnerManager().getSpawnerFromWorld(block.getLocation()).spawn();
        plugin.getLocale().newMessage("&aSpawning successful.").sendPrefixedMessage(player);
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return Collections.emptyList();
    }

    @Override
    public String getPermissionNode() {
        return "epicspawners.admin.spawn";
    }

    @Override
    public String getSyntax() {
        return "spawn";
    }

    @Override
    public String getDescription() {
        return "Force the spawner you are looking at to spawn so long as the spawn conditions are met.";
    }
}
