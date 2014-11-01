package builderdeluxe;

import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static builderdeluxe.GridBagConstraintsBuilder.gridBagConstraints;
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.HORIZONTAL;

public class SelectionDialog extends DialogWrapper {

  private JPanel rootPanel;
  private JBList fieldList;
  private JTextField prefixText;

  public SelectionDialog(PsiClass sourceClass, String storedPrefix) {
    super(sourceClass.getProject());
    setTitle("Select Fields to Include in Builder");
    rootPanel = new JPanel(new GridBagLayout());
    fieldList = new JBList(new CollectionListModel<>(sourceClass.getFields()));
    fieldList.setCellRenderer(new DefaultPsiElementCellRenderer());
    ToolbarDecorator decorator = ToolbarDecorator.createDecorator(fieldList).disableAddAction().disableRemoveAction();
    LabeledComponent<JPanel> labeledComponent = LabeledComponent.create(decorator.createPanel(), "Fields to include in builder");
    JLabel prefixLabel = new JLabel("Prefix");
    prefixText = new JTextField(storedPrefix);
    rootPanel.add(labeledComponent, gridBagConstraints().grid(0, 0).width(2).weight(0.9, 0.9).fill(BOTH).build());
    rootPanel.add(prefixLabel, gridBagConstraints().grid(0, 1).weight(0.2, 0.1).insets(10, 0, 0, 0).build());
    rootPanel.add(prefixText, gridBagConstraints().grid(1, 1).weight(0.8, 0.1).fill(HORIZONTAL).insets(10, 0, 0, 0).build());
    init();
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
    return rootPanel;
  }

  public List<PsiField> getFields() {
    return fieldList.getSelectedValuesList();
  }

  public String getPrefix() {
    return prefixText.getText();
  }
}