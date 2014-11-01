package builderdeluxe;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class BuilderClassCodeGenerator {

  private PsiClass sourceClass;

  public BuilderClassCodeGenerator(PsiClass sourceClass) {
    this.sourceClass = sourceClass;
  }

  public String builderClass() {
    return "public static class Builder {}";
  }

  public String constructor() {
    return "private Builder() {}";
  }

  public String staticInstanceMethod() {
    return "public static Builder " + camelCase(sourceClass.getName()) + "() { return new Builder(); }";
  }

  public String field(PsiField sourceClassField) {
    String typeName = sourceClassField.getTypeElement().getText();
    String fieldName = sourceClassField.getName();
    return "private " + typeName + " " + fieldName + ";";
  }

  public String fieldMethod(PsiField sourceClassField, String prefix) {
    String typeName = sourceClassField.getTypeElement().getText();
    String fieldName = sourceClassField.getName();
    String fieldNameUC = toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    return "public Builder " + camelCase(prefix + fieldNameUC) + "(" + typeName + " " + fieldName +
            ") { this." + fieldName + " = " + fieldName + "; return this; }";
  }

  public String buildMethod() {
    return "public " + sourceClass.getName() + " build() { return new " + sourceClass.getName() + "(); }";
  }

  private String camelCase(String s) {
    return toLowerCase(s.charAt(0)) + s.substring(1);
  }
}
