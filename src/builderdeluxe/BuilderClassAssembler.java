package builderdeluxe;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

import java.util.List;

public class BuilderClassAssembler {

  private PsiClass sourceClass;
  private List<PsiField> selectedFields;
  private String prefix;
  private PsiFacade psiFacade;
  private BuilderClassFactory builderClassFactory;

  public BuilderClassAssembler(PsiClass sourceClass, List<PsiField> selectedFields, String prefix, PsiFacade psiFacade) {
    this.sourceClass = sourceClass;
    this.selectedFields = selectedFields;
    this.prefix = prefix;
    this.psiFacade = psiFacade;
    this.builderClassFactory = new BuilderClassFactory(new BuilderClassCodeGenerator(sourceClass), psiFacade);
  }


  public void assemble() {
    PsiClass builderClass = assembleBuilderClass();
    psiFacade.shortenClassReferences(builderClass);
    psiFacade.reformat(builderClass);
    sourceClass.add(builderClass);

  }

  private PsiClass assembleBuilderClass() {
    PsiClass builderClass = builderClassFactory.createBuilderClass();
    for (PsiField sourceClassField : selectedFields) {
      builderClass.add(builderClassFactory.createField(sourceClassField, builderClass));
    }
    builderClass.add(builderClassFactory.createConstructor(builderClass));
    builderClass.add(builderClassFactory.createStaticInstanceMethod(builderClass));
    for (PsiField sourceClassField : selectedFields) {
      builderClass.add(builderClassFactory.createFieldMethod(sourceClassField, prefix, builderClass));
    }
    builderClass.add(builderClassFactory.createBuildMethod(builderClass));
    return builderClass;
  }
}
