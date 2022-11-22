package ar.com.mercadolibre.challenge.drivers.trilaterationService;

import ar.com.mercadolibre.challenge.adapters.ITrilaterationService;
import ar.com.mercadolibre.challenge.drivers.exceptions.BadRequestException;
import ar.com.mercadolibre.challenge.entities.Position;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NonLinearTrilaterationSolver implements ITrilaterationService {

    @Override
    public Position getLocation(List<Float> distances, List<Position> positions) throws BadRequestException {
        if (distances.stream().anyMatch(Objects::isNull) || positions.isEmpty() || distances.isEmpty())
            throw new BadRequestException("Error: Distances and/or Positions not found");

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positionsToDouble(positions), distances.stream().mapToDouble(Float::doubleValue).toArray()), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();


        double[] centerid = optimum.getPoint().toArray();


        return new Position((float) centerid[0], (float) centerid[1]);


    }

    private double[][] positionsToDouble(List<Position> positions) throws BadRequestException {
        double[][] positionDouble = null;
        try {
            positionDouble = new double[][]{{positions.get(0).getX(), positions.get(0).getY()}, {positions.get(1).getX(), positions.get(1).getY()}, {positions.get(2).getX(), positions.get(2).getY()}};
        } catch (Exception e) {
            throw new BadRequestException("Error invalid data");
        }

        return positionDouble;

    }
}
