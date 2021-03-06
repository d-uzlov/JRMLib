package rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.extern;

import org.jetbrains.annotations.NotNull;
import rxtd.rainmeter.core.GradientOption;
import rxtd.rainmeter.core.SkinUtils;

import java.util.List;
import java.util.Objects;

/**
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Fill">Rainmeter documentation</a>
 */
public class LinearGradient implements Gradient {
    private final String name;
    private final String image;

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Fill">Rainmeter documentation</a>
     */
    public LinearGradient(String name, boolean useGammaCorrection, double angle, List<GradientOption> colors) {
        this.name = name + (useGammaCorrection ? "1" : "");
        this.image = SkinUtils.joinGradient(colors);
    }

    @Override
    public String getType() {
        return "LinearGradient";
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        LinearGradient that = (LinearGradient) o;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.image);
    }
}
