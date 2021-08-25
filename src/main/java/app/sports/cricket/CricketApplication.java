package app.sports.cricket;

import app.sports.cricket.entities.Game;
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
		int totalOvers = 2;
		Game game = new Game(totalOvers,teamA,teamB);

		List<List<String>> oversForA = new LinkedList<List<String>>() {{
			add(Arrays.asList("1","1","1","1","1","2"));
			add(Arrays.asList("W","4","4","Wd","W","1","6"));
		}};

		List<List<String>> oversForB = new LinkedList<List<String>>() {{
			add(Arrays.asList("4","6","W","W","1", "1"));
			add(Arrays.asList("6","1","W","W"));
		}};
		game.playGame(oversForA,oversForB);
	}

}
