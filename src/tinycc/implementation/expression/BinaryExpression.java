package tinycc.implementation.expression;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Void;
import tinycc.implementation.type.*;
import tinycc.implementation.utils.BinaryOperator;
import tinycc.implementation.utils.BinaryOperatorRule;

public class BinaryExpression extends Expression {

    private final BinaryOperator binaryOperator;
    private final Expression firstExpression, secondExpression;

    public BinaryExpression(BinaryOperator binaryOperator, Expression firstExpression, Expression secondExpression) {
        this.binaryOperator = binaryOperator;
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;

        this.firstExpression.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
        this.secondExpression.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());
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
    public void checkSemantics() {
        firstExpression.checkSemantics();
        secondExpression.checkSemantics();

        BinaryOperatorRule rule = getRule();

        if(rule == null)
            throw new FatalCompilerError(firstExpression.getLocatable(), "Illegitimate binary operation '"
                    + firstExpression.getType().toString() + " " + binaryOperator.toString() + " " + secondExpression.getType().toString() + "'.");

        switch (rule.getAdditionalRule()) {
            case COMPLETE_TYPE_POINTER:
                if(firstExpression.getType() instanceof Pointer) {
                    if(!pointsToCompleteType((Pointer) firstExpression.getType()))
                        throw new FatalCompilerError(firstExpression.getLocatable(), "Pointer does not point to a complete type.");
                }

                if(secondExpression.getType() instanceof Pointer) {
                    if(!pointsToCompleteType((Pointer) secondExpression.getType()))
                        throw new FatalCompilerError(secondExpression.getLocatable(), "Pointer does not point to a complete type.");
                }
                break;
            case IDENTICAL_POINTER_CT:
                if(firstExpression.getType() instanceof Pointer && secondExpression.getType() instanceof Pointer) {
                    Pointer pointer1 = (Pointer) firstExpression.getType(),
                            pointer2 = (Pointer) secondExpression.getType();

                    if (!isIdenticalPointerType(pointer1, pointer2))
                        throw new FatalCompilerError(firstExpression.getLocatable(), "Pointers do not have the same type.");

                    if(!pointsToCompleteType(pointer1))
                        throw new FatalCompilerError(firstExpression.getLocatable(), "Pointer does not point to a complete type.");

                    if(!pointsToCompleteType(pointer2))
                        throw new FatalCompilerError(secondExpression.getLocatable(), "Pointer does not point to a complete type.");
                }
                break;
            case IP_VOID_NULL_POINTER:
                if(firstExpression.getType() instanceof Pointer && secondExpression.getType() instanceof Pointer) {
                    Pointer pointer1 = (Pointer) firstExpression.getType(),
                            pointer2 = (Pointer) secondExpression.getType();

                    if (!isIdenticalPointerType(pointer1, pointer2))
                        throw new FatalCompilerError(firstExpression.getLocatable(), "Pointers do not have the same type.");

                    if(pointer1.getPointsTo().getClass() != (new Void()).getClass() && pointer1.getInnerType() != null)
                        throw new FatalCompilerError(firstExpression.getLocatable(), "Pointer does not equal a void pointer, nor a null pointer.");

                    if(pointer2.getPointsTo().getClass() != (new Void()).getClass() && pointer2.getInnerType() != null)
                        throw new FatalCompilerError(secondExpression.getLocatable(), "Pointer does not equal a void pointer, nor a null pointer.");
                }
                break;
            case ASSIGNABLE_LVALUE:
                //TODO
                break;
            default:
                break;
        }

        if(!rule.getLOperandClass().isAssignableFrom(firstExpression.getType().getClass()))
            throw new FatalCompilerError(firstExpression.getLocatable(), "Operand class does not correspond with the rules operand class. Right class = "
                    + rule.getLOperandClass().toString() + ", got class " + firstExpression.getType().getClass().toString() + ".");

        if(!rule.getROperandClass().isAssignableFrom(secondExpression.getType().getClass()))
            throw new FatalCompilerError(secondExpression.getLocatable(), "Operand class does not correspond with the rules operand class. Right class = "
                    + rule.getROperandClass().toString() + ", got class " + secondExpression.getType().getClass().toString() + ".");
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

        return null;
    }

    @Override
    public Type eval() {
        return null;
    }

    @Override
    public String toString() {
        return "(" + firstExpression.toString() + " " + binaryOperator.toString() + " " + secondExpression.toString() + ")";
    }
}
