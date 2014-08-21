package builderdeluxe;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

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
        String typeNameCC = toLowerCase(sourceClass.getName().charAt(0)) + sourceClassName.substring(1);
        return "public static Builder " + typeNameCC + "() { return new Builder(); }";
    }

    public String field(PsiField sourceClassField) {
        String typeName = sourceClassField.getTypeElement().getText();
        String fieldName = sourceClassField.getName();
        return "private " + typeName + " " + fieldName + ";";
    }

    public String fieldMethod(PsiField sourceClassField) {
        String typeName = sourceClassField.getTypeElement().getText();
        String fieldName = sourceClassField.getName();
        String fieldNameUC = toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        return "public Builder with" + fieldNameUC + "(" + typeName + " " + fieldName +
                ") { this." + fieldName + " = " + fieldName + "; return this; }";
    }

    public String buildMethod() {
        return "public " + sourceClass.getName() + " build() { return new " + sourceClass.getName() + "(); }";
    }
}
