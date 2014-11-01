package builderdeluxe;

import java.awt.*;

final class GridBagConstraintsBuilder {

  private GridBagConstraints constraints = new GridBagConstraints();

  GridBagConstraintsBuilder() {
  }

  public static GridBagConstraintsBuilder gridBagConstraints() {
    return new GridBagConstraintsBuilder();
  }

  public GridBagConstraintsBuilder grid(int gridX, int gridY) {
    constraints.gridx = gridX;
    constraints.gridy = gridY;
    return this;
  }

  public GridBagConstraintsBuilder weight(double weightX, double weightY) {
    constraints.weightx = weightX;
    constraints.weighty = weightY;
    return this;
  }

  public GridBagConstraintsBuilder width(int width) {
    constraints.gridwidth = width;
    return this;
  }

  public GridBagConstraintsBuilder fill(int fill) {
    constraints.fill = fill;
    return this;
  }

  public GridBagConstraintsBuilder insets(int top, int left, int bottom, int right) {
    constraints.insets = new Insets(top, left, bottom, right);
    return this;
  }

  public GridBagConstraints build() {
    return constraints;
  }
}
