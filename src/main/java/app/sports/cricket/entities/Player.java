package app.sports.cricket.entities;

import app.sports.cricket.constants.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Player {
    private String name;
    private List<Roles> roles;
}
