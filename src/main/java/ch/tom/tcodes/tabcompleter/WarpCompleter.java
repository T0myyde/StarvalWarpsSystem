package ch.tom.tcodes.tabcompleter;

import ch.tom.tcodes.WarpSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarpCompleter implements TabCompleter {

    private WarpSystem plugin;

    public WarpCompleter(WarpSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();

        StringUtil.copyPartialMatches(args[0], plugin.getCompletions(), completions);

        Collections.sort(completions);
        return completions;
    }
}
