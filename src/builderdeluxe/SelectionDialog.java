package builderdeluxe;

import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class SelectionDialog extends DialogWrapper {

    private JBList fieldList;
    private final LabeledComponent<JPanel> labeledComponent;

    public SelectionDialog(PsiClass sourceClass) {
        super(sourceClass.getProject());
        setTitle("Select Fields to Include in Builder");
        fieldList = new JBList(new CollectionListModel<>(sourceClass.getFields()));
        fieldList.setCellRenderer(new DefaultPsiElementCellRenderer());
        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(fieldList);
        decorator.disableAddAction();
        decorator.disableRemoveAction();
        labeledComponent = LabeledComponent.create(decorator.createPanel(), "Fields to include in builder");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return labeledComponent;
    }

    public List<PsiField> getFields() {
        return fieldList.getSelectedValuesList();
    }
}
