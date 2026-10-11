# Village — Game rules (MVP)

This document is the reference for the first playable version of Village.
It follows the rules of *Les Loups-Garous de Thiercelieux* (base game), adapted to an online game
where the plugin plays the narrator.

Every number below (composition, timers) is a **default value**: it lives in configuration and will be
editable from the in-game admin menu.

---

## 1. Players

- **4 to 16 players** per game.
- Roles are dealt at random among the players when the game starts.

## 2. Roles

| Role | Team | Power |
|---|---|---|
| **Villager** | Village | None. Debates and votes. |
| **Werewolf** | Werewolves | Every night, the werewolves choose a victim together. |
| **Seer** | Village | Every night, learns the exact role of one player. |
| **Witch** | Village | One healing potion and one poison potion, each usable once per game. |
| **Hunter** | Village | When he dies, immediately shoots another player. |

### Werewolf
- Werewolves know each other from the start.
- They may target **any living player, including another werewolf** (a bluff that can fool the Witch).

### Seer
- Targets one living player other than herself and learns their exact role.

### Witch
- Is shown the werewolves' victim of the night (if any), even if the victim is herself or a werewolf.
- **Healing potion**: saves that night's werewolf victim. Once per game. She may save herself.
- **Poison potion**: kills one living player of her choice, **except herself**. Once per game.
- She may use both potions during the same night, or neither.

### Hunter
- When he dies, **whatever the cause** (werewolves, Witch's poison or vote), he immediately shoots one living player.
- If he does not choose in time, he does not shoot.
- A Hunter who **disconnects** does not shoot.

## 3. Mayor

- Elected on **day 1**, by relative majority (most votes wins). Every living player is a candidate and may vote for themselves.
- The Mayor's vote **counts double**.
- When the Mayor dies, they **designate a successor** among the living players. Without a choice in time,
  the successor is drawn at random.

## 4. Default composition

Roughly one werewolf for four players; special roles are added as the game grows.

| Players | Werewolves | Seer | Witch | Hunter | Villagers |
|---|---|---|---|---|---|
| 4 | 1 | ✔ | – | – | 2 |
| 5 | 1 | ✔ | – | – | 3 |
| 6 | 1 | ✔ | – | – | 4 |
| 7 | 2 | ✔ | ✔ | – | 3 |
| 8 | 2 | ✔ | ✔ | – | 4 |
| 9 | 2 | ✔ | ✔ | ✔ | 4 |
| 10 | 2 | ✔ | ✔ | ✔ | 5 |
| 11 | 3 | ✔ | ✔ | ✔ | 5 |
| 12 | 3 | ✔ | ✔ | ✔ | 6 |
| 13 | 3 | ✔ | ✔ | ✔ | 7 |
| 14 | 3 | ✔ | ✔ | ✔ | 8 |
| 15 | 4 | ✔ | ✔ | ✔ | 8 |
| 16 | 4 | ✔ | ✔ | ✔ | 9 |

This is a starting point, to be tuned after real games.

## 5. Game flow

```
Role reveal ─▶ NIGHT ─▶ MORNING ─▶ (day 1: Mayor election) ─▶ DEBATE & VOTE ─▶ NIGHT ─▶ ...
```

The game ends as soon as a victory condition is met (see §8), checked after every death.

### 5.1 Role reveal
Each player privately learns their role. Werewolves also see who the other werewolves are.

### 5.2 Night
The village falls asleep. Roles are called in the official order:

1. **Seer**
2. **Werewolves**
3. **Witch** (after the werewolves, so she knows their victim)

Deaths of the night are not revealed until the morning.

### 5.3 Morning
The village wakes up. The night's victims are announced and **their roles are revealed**.
Death effects are then resolved (see §7).

### 5.4 Mayor election (day 1 only)
All living players vote for a Mayor.

### 5.5 Debate & vote
Players debate and **may vote at any time during the debate**, and change their vote until the end.
When the phase ends, the player with the most votes is eliminated and their role is revealed
(ties: see §6). Players who did not vote abstain. Nobody may vote against themselves.

## 6. Votes and ties

| Vote | Who votes | Result | Tie |
|---|---|---|---|
| **Werewolves' victim** | Living werewolves (they see each other's votes) | Most votes | Random among the tied targets. No vote at all: no victim. |
| **Mayor election** | All living players | Most votes | Revote among the tied players; still tied: random among them. |
| **Elimination** | All living players (Mayor counts double) | Most votes | If the Mayor voted for one of the tied players, that player is eliminated. Otherwise, revote among the tied players (they may vote too); still tied: **nobody is eliminated**. |

## 7. Deaths

- A dead player's role is **revealed**.
- Dead players become **spectators**: they can watch but can no longer communicate with the living.
- Resolution order of a death:
  1. reveal the role;
  2. Hunter's shot, if the dead player is the Hunter;
  3. succession, if the dead player is the Mayor;
  4. victory check.
- A player who **disconnects** during a game is eliminated and their role revealed (no Hunter shot;
  random successor if they were Mayor).

## 8. Victory

Checked after every death:

1. **No werewolf alive** → the **village** wins.
2. Otherwise, **at most one non-werewolf alive** → the **werewolves** win
   (official rule: they win as soon as the second-to-last villager is eliminated).
3. If nobody is alive at all → **draw**.

## 9. Timers

| Phase | Duration | Early end |
|---|---|---|
| Role reveal | 10 s | – |
| Night — Seer | 20 s | as soon as she has chosen |
| Night — Werewolves | 30 s | when every living werewolf has voted, 5 s remain |
| Night — Witch | 20 s | as soon as she has finished |
| Morning announcement | 10 s | – |
| Hunter's shot | 15 s | as soon as he shoots |
| Mayor successor | 15 s | as soon as they choose |
| Mayor election (day 1) | 60 s | when every living player has voted, 30 s remain |
| Debate & vote | 30 s per living player, min 2 min, max 5 min | when every living player has voted, 30 s remain |
| Tie revote | 60 s | when every voter has voted, 30 s remain |

Early ends only ever **shorten** the remaining time, they never add time.

## 10. Hidden information ("narrator's bluff")

Players must not be able to deduce anything from how the night unfolds:

- Every night phase is announced to everyone ("The Seer wakes up...") **even if that role is dead or
  not in the game**.
- A phase whose role is dead or absent lasts a **random duration** between 5 s and its full duration,
  so it cannot be told apart from a living player acting.

## 11. Not in the MVP (planned)

- **Admin menu** (server admins only): composition per player count, timers, enabled roles.
- **Player introduction** before the first night: camera showcasing each player's skin
  (planned for just before v1.0.0).
- **Dead players' chat**.
- **More roles**: Cupid, Little Girl, Thief...

## Sources

- Base game rulebook (*Les Loups-Garous de Thiercelieux*, Lui-même): call order, Witch potions, Hunter,
  Captain (Mayor) double vote and succession, tie-break, victory conditions, revealed cards.
- "Narrator's bluff": calling dead roles at night to avoid giving information away.
