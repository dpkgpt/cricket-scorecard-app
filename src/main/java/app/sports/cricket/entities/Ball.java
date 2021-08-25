package app.sports.cricket.entities;

import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.constants.RunsType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Ball {
    int runs;
    List<HandlerTypes> handlers;
    RunsType runsType;
}
