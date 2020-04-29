package services;

import models.DigitResult;
import models.Triangle;

import java.util.List;
import java.util.UUID;

public interface TriangleService extends BasicService {
    List<Triangle> getAll();
    Triangle create(Triangle triangle);
    Triangle get(UUID id);
    boolean delete(UUID id);
    DigitResult getPerimeter(UUID id);
    DigitResult getArea(UUID id);
    }

