package builderdeluxe;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;

public class BuilderClassFactory {

  private BuilderClassCodeGenerator codeGenerator;
  private PsiFacade psiFacade;

  public BuilderClassFactory(BuilderClassCodeGenerator codeGenerator, PsiFacade psiFacade) {
    this.codeGenerator = codeGenerator;
    this.psiFacade = psiFacade;
  }

  public PsiClass createBuilderClass() {
    return psiFacade.createClassFromText(codeGenerator.builderClass());
  }

  public PsiMethod createConstructor(PsiClass builderClass) {
    return psiFacade.createMethodFromText(codeGenerator.constructor(), builderClass);
  }

  public PsiMethod createStaticInstanceMethod(PsiClass builderClass) {
    return psiFacade.createMethodFromText(codeGenerator.staticInstanceMethod(), builderClass);
  }

  public PsiField createField(PsiField sourceClassField, PsiClass builderClass) {
    return psiFacade.createFieldFromText(codeGenerator.field(sourceClassField), builderClass);
  }

  public PsiMethod createFieldMethod(PsiField sourceClassField, String prefix, PsiClass builderClass) {
    return psiFacade.createMethodFromText(codeGenerator.fieldMethod(sourceClassField, prefix), builderClass);
  }

  public PsiMethod createBuildMethod(PsiClass builderClass) {
    return psiFacade.createMethodFromText(codeGenerator.buildMethod(), builderClass);
  }
}
