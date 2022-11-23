package ar.com.mercadolibre.challenge.tdd;

import ar.com.mercadolibre.challenge.drivers.exceptions.BadRequestException;
import ar.com.mercadolibre.challenge.drivers.trilaterationService.NonLinearTrilaterationSolver;
import ar.com.mercadolibre.challenge.entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TrilaterationTests {

    NonLinearTrilaterationSolver solver = new NonLinearTrilaterationSolver();
    List<Float> distances = new ArrayList<>();

    List<Float> distancesNotFound = new ArrayList<>();
    List<Position> positions = new ArrayList<>();

    List<Position> positionsInvalidList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        distances.add(8.06f);
        distances.add(13.97f);
        distances.add(23.32f);

        positions.add(new Position(5.0f, -6.0f));
        positions.add(new Position(13.0f, -15.0f));
        positions.add(new Position(21.0f, -3.0f));

        positionsInvalidList.add(new Position(5.0f, -6.0f));
        positionsInvalidList.add(new Position(13.0f, -15.0f));

    }

    @Test
    public void getPositionTest() throws BadRequestException {
        Position position = solver.getLocation(distances, positions);
        assertTrue(position.getX() != 0.0f);
        assertTrue(position.getY() != 0.0f);
    }

    @Test
    void getPositionDistancesExceptionTest() {
        BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class,() -> solver.getLocation(distancesNotFound, positions),
                "Error: Distances and/or Positions not found");
        assertTrue(exception.getMessage().contains("Error: Distances and/or Positions not found"));
    }

    @Test
    void getPositionPositionsExceptionTest() {
        BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class,() -> solver.getLocation(distances, positionsInvalidList),
                "Error invalid data");
        assertTrue(exception.getMessage().contains("Error invalid data"));
    }
}
