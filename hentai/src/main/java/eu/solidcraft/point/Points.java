package eu.solidcraft.point;


import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
class Points {
    public static final Points NO_POINTS = new Points(0);

    private final int value;

    public Points add(Points points) {
        return new Points(value + points.value);
    }
}
