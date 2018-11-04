package rxtd.rainmeter.elements.meters.string;

import rxtd.rainmeter.GradientOption;
import rxtd.rainmeter.SkinUtils;

import java.util.ArrayList;
import java.util.Collection;

public class InlineSettings {
    public enum CaseValue {
        LOWER("Lower"),
        UPPER("Upper"),
        PROPER("Proper"),
        SENTENCE("Sentence");
        private final String value;

        CaseValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public interface InlineOption {

    }

    private static class InlineOptionBase implements InlineOption {
        private String image;

        protected void setImage(String image, Object... values) {
            ArrayList<String> l = new ArrayList<>();
            l.add(image);
            for (var v : values) {
                if (v == null) {
                    l.add("*");
                } else {
                    l.add(v.toString());
                }
            }
            this.image = String.join("|", l.toArray(new String[0]));
        }

        @Override
        public final String toString() {
            return this.image;
        }
    }

    public static class Face extends InlineOptionBase {
        public Face(String fontFamily) {
            this.setImage("Face", fontFamily);
        }
    }

    public static class Size extends InlineOptionBase {
        public Size(int size) {
            this.setImage("Size", size);
        }
    }

    public static class Color extends InlineOptionBase {
        public Color(java.awt.Color color) {
            this.setImage("Color", SkinUtils.print(color));
        }
    }

    public static class Weight extends InlineOptionBase {
        public Weight(int weight) {
            if (weight < 1 || weight > 999) {
                throw new IllegalArgumentException();
            }
            this.setImage("Weight", weight);
        }
    }

    public static class Case extends InlineOptionBase {
        public Case(CaseValue value) {
            this.setImage("Color", value.toString());
        }
    }

    public static class CharacterSpacing extends InlineOptionBase {
        public CharacterSpacing(Number leading, Number trailing, Integer minimumAdvanceWidth) {
            this.setImage("CharacterSpacing", leading, trailing, minimumAdvanceWidth);
        }
    }

    public static class Italic extends InlineOptionBase {
        public Italic() {
            this.setImage("Italic");
        }
    }

    public static class Oblique extends InlineOptionBase {
        public Oblique() {
            this.setImage("Oblique");
        }
    }

    public static class Underline extends InlineOptionBase {
        public Underline() {
            this.setImage("Underline");
        }
    }

    public static class Strikethrough extends InlineOptionBase {
        public Strikethrough() {
            this.setImage("Strikethrough");
        }
    }

    public static class Shadow extends InlineOptionBase {
        public Shadow(Number xOffset, Number yOffset, Number blurAmount, Color color) {
            this.setImage("Shadow", xOffset, yOffset, blurAmount, color);
        }
    }

    public static class Stretch extends InlineOptionBase {
        public Stretch(int value) {
            if (value < 1 || value > 9) {
                throw new IllegalArgumentException();
            }
            this.setImage("Stretch", value);
        }
    }

    public static class Typography extends InlineOptionBase {
        public Typography(String value) {
            this.setImage("Typography", value);
        }
    }

    public static class GradientColor extends InlineOptionBase {
        public GradientColor(Collection<GradientOption> colors) {
            this.setImage("GradientColor", SkinUtils.joinGradient(colors));
        }
    }

    public static class None extends InlineOptionBase {
        public None() {
            this.setImage("None");
        }
    }
}
