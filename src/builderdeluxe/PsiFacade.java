package builderdeluxe;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.util.PsiTreeUtil;

import static com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR;
import static com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE;

public class PsiFacade {

    private Project project;

    public PsiFacade(Project project) {
        this.project = project;
    }

    public PsiClass getPsiClassFromEvent(AnActionEvent e) {
        PsiFile file = e.getData(PSI_FILE);
        Editor editor = e.getData(EDITOR);
        if (file == null || editor == null) {
            return null;
        }
        int offset = editor.getCaretModel().getOffset();
        PsiElement element = file.findElementAt(offset);
        return PsiTreeUtil.getParentOfType(element, PsiClass.class);
    }

    public PsiClass createClassFromText(String classText) {
        return JavaPsiFacade.getElementFactory(project).createClassFromText(classText, null).getInnerClasses()[0];
    }

    public PsiMethod createMethodFromText(String methodText, PsiClass targetClass) {
        return JavaPsiFacade.getElementFactory(project).createMethodFromText(methodText, targetClass);
    }

    public PsiField createFieldFromText(String fieldText, PsiClass targetClass) {
        return JavaPsiFacade.getElementFactory(project).createFieldFromText(fieldText, targetClass);
    }

    public PsiElement shortenClassReferences(PsiClass psiClass) {
        return JavaCodeStyleManager.getInstance(project).shortenClassReferences(psiClass);
    }

    public PsiElement reformat(PsiClass psiClass) {
        return CodeStyleManager.getInstance(project).reformat(psiClass);
    }
}
