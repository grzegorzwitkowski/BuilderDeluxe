package builderdeluxe;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

import static java.lang.Character.toLowerCase;

public class BuilderClassCodeGenerator {

    private PsiClass sourceClass;
    private String sourceClassName;

    public BuilderClassCodeGenerator(PsiClass sourceClass) {
        this.sourceClass = sourceClass;
        this.sourceClassName = sourceClass.getName();
    }

    public String builderClass() {
        return "public static class Builder {}";
    }

    public String constructor() {
        return "private Builder() {}";
    }

    public String staticInstanceMethod() {
        String sourceClassCC = toLowerCase(sourceClass.getName().charAt(0)) + sourceClassName.substring(1);
        return "public static Builder " + sourceClassCC + "() { return new Builder(); }";
    }

    public String field(PsiField sourceClassField) {
        String typeName = sourceClassField.getTypeElement().getText();
        return "private " + typeName + " " + sourceClassField.getName() + ";";
    }

    public String fieldMethod(PsiField sourceClassField) {
        String typeName = sourceClassField.getTypeElement().getText();
        String variableName = sourceClassField.getName();
        return "public Builder " + variableName + "(" + typeName + " " + variableName +
                ") { this." + variableName + " = " + variableName + "; return this; }";
    }

    public String buildMethod() {
        return "public " + sourceClass.getName() + " build() { return new " + sourceClass.getName() + "(); }";
    }
}
