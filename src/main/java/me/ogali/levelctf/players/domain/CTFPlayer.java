package me.ogali.levelctf.players.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.ogali.levelctf.floors.domain.Floor;
import me.ogali.levelctf.games.domain.Game;
import me.ogali.levelctf.teams.domain.Team;
import me.ogali.levelctf.utils.Chat;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@Getter
@Setter
public class CTFPlayer {

    private final Player player;
    private Team team;
    private Floor currentFloor;
    private Game currentGame;

    public void teleportPlayerToTeamSpawn() {
        this.currentFloor = currentGame.getArena().getFloorList().get(0);
        player.teleport(team.getSpawnLocation());
    }

    public void healAndTeleportToNextFloor() {
        player.setHealth(20);
        if (currentGame.getArena().getFloorList().indexOf(currentFloor) + 1 ==
                currentGame.getArena().getFloorList().size()) {
            teleportPlayerToPrevFloor();
            return;
        }
        Floor nextFloor = getNextFloor();
        player.teleport(nextFloor.getSpawnLocation());
        this.currentFloor = nextFloor;
    }

    public void teleportPlayerToPrevFloor() {
        player.setHealth(20);
        Floor previousFloor = getPreviousFloor();
        player.teleport(previousFloor.getSpawnLocation());
        this.currentFloor = previousFloor;
    }

    public void sendActionBarMessage(String message) {
        Chat.sendActionBarWithSound(player, message);
    }

    public void sendTitleMessage(String title) {
        sendTitleMessage(title, "");
    }

    public void sendTitleMessage(String title, String subtitle) {
        player.sendTitle(Chat.colorize(title), Chat.colorize(subtitle), 5, 5, 5);
    }

    private Floor getNextFloor() {
        int currentFloorIndex = currentGame.getArena().getFloorList().indexOf(currentFloor);
        return currentGame.getArena().getFloorList().get(currentFloorIndex + 1);
    }

    private Floor getPreviousFloor() {
        if (currentGame.getArena().getFloorList().indexOf(currentFloor) == 0) {
            return currentFloor;
        }
        int currentFloorIndex = currentGame.getArena().getFloorList().indexOf(currentFloor);
        return currentGame.getArena().getFloorList().get(currentFloorIndex - 1);
    }

}
