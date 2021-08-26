package app.sports.cricket;

import app.sports.cricket.entities.Game;
import app.sports.cricket.entities.Over;
import app.sports.cricket.entities.Player;
import app.sports.cricket.entities.Team;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class CricketApplication {

	private static Team createTeam(String name, int teamSize) {
		return Team.builder().name("Team " + name)
				.players(IntStream.range(0, teamSize).boxed()
						.map(index -> Player.builder().name(name+(index+1)).build())
						.collect(Collectors.toCollection(LinkedList::new))).totalPlayers(teamSize).build();
	}

	public static void main(String[] args) {
		int totalPlayers = 5;
		Team teamA = createTeam("A",totalPlayers);
		Team teamB = createTeam("B",totalPlayers);
		int totalOvers = 1;
		Game game = new Game(totalOvers,teamA,teamB);

		List<List<String>> oversForA = new LinkedList<List<String>>() {{
			add(Arrays.asList("1","1","1","1","1","W"));
		}};

		List<List<String>> oversForB = new LinkedList<List<String>>() {{
			add(Arrays.asList("W","W","W","W"));
		}};
		LinkedList<Player> bowlersForInning1 = new LinkedList<Player>(){{
			add(teamB.getPlayers().get(0));
		}};
		LinkedList<Player> bowlersForInning2 = new LinkedList<Player>(){{
			add(teamA.getPlayers().get(0));
		}};
		List<Over> overForInning1 = oversForA.stream().map(o-> new Over(bowlersForInning1.pop(),o)).collect(Collectors.toCollection(LinkedList::new));
		List<Over> overForInning2 = oversForB.stream().map(o-> new Over(bowlersForInning2.pop(),o)).collect(Collectors.toCollection(LinkedList::new));
		game.playGame(overForInning1,overForInning2);
	}

}
