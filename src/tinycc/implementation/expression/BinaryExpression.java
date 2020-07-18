package tinycc.implementation.expression;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Void;
import tinycc.implementation.type.*;
import tinycc.implementation.utils.BinaryOperator;
import tinycc.implementation.utils.BinaryOperatorRule;
import tinycc.implementation.utils.EnvironmentalDeclaration;

import java.util.Collection;

public class BinaryExpression extends Expression {

    private final BinaryOperator binaryOperator;
    private final Expression firstExpression, secondExpression;

    public BinaryExpression(BinaryOperator binaryOperator, Expression firstExpression, Expression secondExpression) {
        this.binaryOperator = binaryOperator;
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    public BinaryOperator getBinaryOperator() {
        return binaryOperator;
    }

    public Expression getFirstExpression() {
        return firstExpression;
    }

    public Expression getSecondExpression() {
        return secondExpression;
    }

    public BinaryOperatorRule getRule() {
        for(BinaryOperatorRule rule : BinaryOperatorRule.values()) {
            if(rule.getBinaryOperator().equals(binaryOperator)) {
                if(rule.getLOperandClass().isAssignableFrom(firstExpression.getType().getClass()) && rule.getROperandClass().isAssignableFrom(secondExpression.getType().getClass()))
                    return rule;
            }
        }

        return null;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        firstExpression.addEnvironmentalDeclarations(environmentalDeclarations);
        secondExpression.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        firstExpression.checkSemantics();
        secondExpression.checkSemantics();

        BinaryOperatorRule rule = getRule();

        if(rule == null)
            throw new FatalCompilerError(getLocatable(), "Illegitimate binary operation '" + toString() + "'.");

        switch (rule.getAdditionalRule()) {
            case COMPLETE_TYPE_POINTER:
                if(firstExpression.getType() instanceof Pointer) {
                    if(!pointsToCompleteType((Pointer) firstExpression.getType()))
                        throw new FatalCompilerError(firstExpression.getLocatable(), "Binary expression: First expression pointer does not point to a complete type.");
                }

                if(secondExpression.getType() instanceof Pointer) {
                    if(!pointsToCompleteType((Pointer) secondExpression.getType()))
                        throw new FatalCompilerError(secondExpression.getLocatable(), "Binary expression: Second expression pointer does not point to a complete type.");
                }
                break;
            case IDENTICAL_POINTER_CT:
                if(firstExpression.getType() instanceof Pointer && secondExpression.getType() instanceof Pointer) {
                    Pointer pointer1 = (Pointer) firstExpression.getType(),
                            pointer2 = (Pointer) secondExpression.getType();

                    if (!isIdenticalPointerType(pointer1, pointer2))
                        throw new FatalCompilerError(this.getLocatable(), "Pointers do not have the same type.");

                    if(!pointsToCompleteType(pointer1))
                        throw new FatalCompilerError(firstExpression.getLocatable(), "Binary expression: First expression pointer does not point to a complete type.");

                    if(!pointsToCompleteType(pointer2))
                        throw new FatalCompilerError(secondExpression.getLocatable(), "Binary expression: Second expression pointer does not point to a complete type.");
                }
                break;
            case IP_VOID_NULL_POINTER:
                if(firstExpression.getType() instanceof Pointer && secondExpression.getType() instanceof Pointer) {
                    Pointer pointer1 = (Pointer) firstExpression.getType(),
                            pointer2 = (Pointer) secondExpression.getType();

                    if (!isIdenticalPointerType(pointer1, pointer2))
                        throw new FatalCompilerError(this.getLocatable(), "Pointers do not have the same type.");

                    if(pointer1.getType().getClass() != (new Void()).getClass() && pointer1.getInnerType() != null)
                        throw new FatalCompilerError(firstExpression.getLocatable(),
                                "Binary expression: First expression pointer does not equal a void pointer, nor a null pointer.");

                    if(pointer2.getType().getClass() != (new Void()).getClass() && pointer2.getInnerType() != null)
                        throw new FatalCompilerError(secondExpression.getLocatable(),
                                "Binary expression: Second expression pointer does not equal a void pointer, nor a null pointer.");
                }
                break;
            default:
                break;
        }

        if (!rule.getLOperandClass().isAssignableFrom(firstExpression.getType().getClass()))
            throw new FatalCompilerError(firstExpression.getLocatable(), "Operand class does not correspond with the rules operand class. Right class = "
                    + rule.getLOperandClass().toString() + ", got class " + firstExpression.getType().getClass().toString() + ".");

        if (!rule.getROperandClass().isAssignableFrom(secondExpression.getType().getClass()))
            throw new FatalCompilerError(secondExpression.getLocatable(), "Operand class does not correspond with the rules operand class. Right class = "
                    + rule.getLOperandClass().toString() + ", got class " + secondExpression.getType().getClass().toString() + ".");
    }

    private boolean pointsToCompleteType(Pointer pointer) {
        Type innerType = pointer.getInnerType();

        if (!WholeNumber.class.isAssignableFrom(innerType.getClass()))
            return false;

        return true;
    }

    private boolean isIdenticalPointerType(Pointer pointer1, Pointer pointer2) {
        if(!pointer1.getInnerType().equals(pointer2.getInnerType()))
            return false;

        return true;
    }

    @Override
    public Type getType() {
        BinaryOperatorRule rule = getRule();

        if(rule.getResultTypeClass() == Integer.class)
            return new Integer();
        if(rule.getResultTypeClass() == Pointer.class)
            return firstExpression.getType();

        throw new RuntimeException("Unknown type: " + rule.getResultTypeClass().toString());
    }

    @Override
    public Expression clone() {
        BinaryExpression binaryExpression = new BinaryExpression(binaryOperator, firstExpression.clone(), secondExpression.clone());

        binaryExpression.setLocatable(this.getLocatable());
        binaryExpression.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());

        return binaryExpression;
    }

    @Override
    public String toString() {
        return "(" + firstExpression.toString()
                + " " + binaryOperator.toString()
                + " " + secondExpression.toString() + ")";
    }
}
