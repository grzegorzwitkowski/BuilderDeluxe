package builderdeluxe;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

import java.util.List;

public class BuilderDeluxeAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        PsiFacade psiFacade = new PsiFacade(e.getProject());
        PsiClass sourceClass = psiFacade.getPsiClassFromEvent(e);
        SelectionDialog selectionDialog = new SelectionDialog(sourceClass);
        selectionDialog.show();
        if (selectionDialog.isOK()) {
            List<PsiField> selectedFields = selectionDialog.getFields();
            generateBuilderClass(sourceClass, selectedFields, psiFacade);
        }
    }

    @Override
    public void update(AnActionEvent e) {
        PsiFacade psiFacade = new PsiFacade(e.getProject());
        PsiClass psiClass = psiFacade.getPsiClassFromEvent(e);
        e.getPresentation().setEnabled(psiClass != null);
    }

    private void generateBuilderClass(final PsiClass sourceClass, final List<PsiField> selectedFields, final PsiFacade psiFacade) {
        new WriteCommandAction.Simple(sourceClass.getProject()) {

            @Override
            protected void run() throws Throwable {
                new BuilderClassAssembler(sourceClass, selectedFields, psiFacade).assemble();
            }
        }.execute();
    }
}
