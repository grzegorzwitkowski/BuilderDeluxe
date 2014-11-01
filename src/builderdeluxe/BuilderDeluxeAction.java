package builderdeluxe;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

import java.util.List;

public class BuilderDeluxeAction extends AnAction {

  public static final String PREFIX_PROPERTY = "builderdeluxe.prefix";

  @Override
  public void actionPerformed(AnActionEvent e) {
    PsiFacade psiFacade = new PsiFacade(e.getProject());
    PsiClass sourceClass = psiFacade.getPsiClassFromEvent(e);
    PropertiesComponent properties = PropertiesComponent.getInstance();

    String storedPrefix = properties.getValue(PREFIX_PROPERTY, "with");
    SelectionDialog selectionDialog = new SelectionDialog(sourceClass, storedPrefix);
    selectionDialog.show();
    if (selectionDialog.isOK()) {
      List<PsiField> selectedFields = selectionDialog.getFields();
      String prefix = selectionDialog.getPrefix();
      if (!prefix.equals(storedPrefix)) {
        properties.setValue(PREFIX_PROPERTY, prefix);
      }
      generateBuilderClass(sourceClass, selectedFields, prefix, psiFacade);
    }
  }

  @Override
  public void update(AnActionEvent e) {
    PsiFacade psiFacade = new PsiFacade(e.getProject());
    PsiClass psiClass = psiFacade.getPsiClassFromEvent(e);
    e.getPresentation().setEnabled(psiClass != null);
  }

  private void generateBuilderClass(final PsiClass sourceClass, final List<PsiField> selectedFields, final String prefix,
                                    final PsiFacade psiFacade) {
    new WriteCommandAction.Simple(sourceClass.getProject()) {
      @Override
      protected void run() throws Throwable {
        new BuilderClassAssembler(sourceClass, selectedFields, prefix, psiFacade).assemble();
      }
    }.execute();
  }
}
