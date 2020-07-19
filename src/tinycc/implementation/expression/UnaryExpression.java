package tinycc.implementation.expression;

import prog2.tests.FatalCompilerError;
import tinycc.implementation.external.function.Function;
import tinycc.implementation.external.function.FunctionDeclaration;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Pointer;
import tinycc.implementation.type.Type;
import tinycc.implementation.type.WholeNumber;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.UnaryOperator;
import tinycc.implementation.utils.UnaryOperatorRule;

import java.util.Collection;

public class UnaryExpression extends Expression {

    private final UnaryOperator unaryOperator;
    private final boolean isPostfix;
    private final Expression expression;

    public UnaryExpression(UnaryOperator unaryOperator, boolean isPostfix, Expression expression) {
        this.unaryOperator = unaryOperator;
        this.isPostfix = isPostfix;
        this.expression = expression;
    }

    public UnaryOperator getUnaryOperator() {
        return unaryOperator;
    }

    public boolean isPostfix() {
        return isPostfix;
    }

    public Expression getExpression() {
        return expression;
    }

    public UnaryOperatorRule getRule() {
        for(UnaryOperatorRule rule : UnaryOperatorRule.values()) {
            if(rule.getUnaryOperator().equals(unaryOperator)) {
                if(rule.getOperandClass().isAssignableFrom(expression.getType().getClass()))
                    return rule;
            }
        }

        return null;
    }

    private EnvironmentalDeclaration getDeclarationByIdentifier(String identifier) {
        for(EnvironmentalDeclaration environmentalDeclaration : this.getEnvironmentalDeclarations()) {
            if(environmentalDeclaration.getIdentifier().toString().equals(identifier))
                return environmentalDeclaration;
        }

        return null;
    }

    @Override
    public void updateEnvironment(Collection<EnvironmentalDeclaration> environmentalDeclarations) {
        expression.addEnvironmentalDeclarations(environmentalDeclarations);
    }

    @Override
    public void checkSemantics() {
        expression.checkSemantics();

        UnaryOperatorRule rule = getRule();

        if(rule == null)
            throw new FatalCompilerError(this.getLocatable(),
                    "Illegitimate unary operation '" + toString() + "'.");

        switch (rule.getAdditionalRule()) {
            case COMPLETE_TYPE_POINTER:
                if(expression.getType() instanceof Pointer) {
                    if(!pointsToCompleteType((Pointer) expression.getType()))
                        throw new FatalCompilerError(this.getLocatable(),
                                "Unary expression: Pointer does not point to a complete type. Got type " + ((Pointer) expression.getType()).getType() + ".");
                }
                break;
            case COMPLETE_TYPE:
                if(!(expression.getType() instanceof WholeNumber))
                    throw new FatalCompilerError(this.getLocatable(), "Expression is not a complete type. Got type " + expression.getType().toString() + ".");
                break;
            case COMPLETE_TYPE_ASSIGNABLE:
                if(!(expression.getType() instanceof WholeNumber))
                    throw new FatalCompilerError(this.getLocatable(), "Expression is not a complete type. Got type " + expression.getType().toString() + ".");

                if(getDeclarationByIdentifier(expression.toString()) == null)
                    throw new FatalCompilerError(this.getLocatable(), "Expression '" + expression.toString() + "' is not assignable.");
                break;
            default:
                break;
        }

        if(expression.isWrongCalledFunction())
            throw new FatalCompilerError(expression.getLocatable(), "The call '" + expression.toString() + "' is not a correct function call.");

        if(unaryOperator == UnaryOperator.SIZE_OF && expression.isIdentifier()) {
            for(EnvironmentalDeclaration environmentalDeclaration : this.getEnvironmentalDeclarations()) {
                if(environmentalDeclaration.getIdentifier().toString().equals(expression.toString())) {
                    if(environmentalDeclaration instanceof Function || environmentalDeclaration instanceof FunctionDeclaration)
                        throw new FatalCompilerError(this.getLocatable(), "You can not get the size of a function.");
                }
            }
        }
    }

    private boolean pointsToCompleteType(Pointer pointer) {
        Type innerType = pointer.getInnerType();

        if (!WholeNumber.class.isAssignableFrom(innerType.getClass()))
            return false;

        return true;
    }

    @Override
    public boolean isIdentifier() {
        UnaryOperatorRule rule = getRule();

        if(rule.getUnaryOperator() == UnaryOperator.ADDRESS_OF || rule.getUnaryOperator() == UnaryOperator.POINT_TO)
            return expression.isIdentifier();

        return false;
    }

    @Override
    public Type getType() {
        UnaryOperatorRule rule = getRule();

        if(rule.getResultTypeClass() == Integer.class)
            return new Integer();

        if(rule.getUnaryOperator() == UnaryOperator.POINT_TO)
            return ((Pointer) expression.getType()).getType();

        if(rule.getUnaryOperator() == UnaryOperator.ADDRESS_OF)
            return new Pointer<>(expression.getType());

        throw new RuntimeException("Unknown type: " + rule.getResultTypeClass().toString());
    }

    @Override
    public Type eval() {
        return null;
    }

    @Override
    public Expression clone() {
        UnaryExpression unaryExpression = new UnaryExpression(unaryOperator, isPostfix, expression.clone());

        unaryExpression.setLocatable(this.getLocatable());
        unaryExpression.addEnvironmentalDeclarations(this.getEnvironmentalDeclarations());

        return unaryExpression;
    }

    @Override
    public String toString() {
        if(isPostfix())
            return "(" + expression.toString() + unaryOperator.toString() + ")";

        return "(" + unaryOperator.toString() + expression.toString() + ")";
    }
}
