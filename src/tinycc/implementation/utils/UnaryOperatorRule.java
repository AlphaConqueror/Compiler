package tinycc.implementation.utils;

import tinycc.implementation.type.Integer;
import tinycc.implementation.type.*;

public enum UnaryOperatorRule {
    POINT_TO_POINTER(UnaryOperator.POINT_TO, Pointer.class, Scalar.class, AdditionalRule.COMPLETE_TYPE_POINTER),
    ADDRESS_OF_OBJECT(UnaryOperator.ADDRESS_OF, ObjectType.class, Pointer.class, AdditionalRule.COMPLETE_TYPE_ASSIGNABLE),
    SIZE_OF_OBJECT(UnaryOperator.SIZE_OF, ObjectType.class, Integer.class, AdditionalRule.COMPLETE_TYPE),
    NOT_INT(UnaryOperator.NOT, Integer.class, Integer.class);

    private final UnaryOperator unaryOperator;
    private final Class<? extends Type> operandClass, resultTypeClass;
    private final AdditionalRule additionalRule;

    UnaryOperatorRule(UnaryOperator unaryOperator, Class<? extends Type> operand, Class<? extends Type> resultType) {
        this.unaryOperator = unaryOperator;
        this.operandClass = operand;
        this.resultTypeClass = resultType;
        this.additionalRule = AdditionalRule.NONE;
    }

    UnaryOperatorRule(UnaryOperator unaryOperator, Class<? extends Type> operand, Class<? extends Type> resultType, AdditionalRule additionalRule) {
        this.unaryOperator = unaryOperator;
        this.operandClass = operand;
        this.resultTypeClass = resultType;
        this.additionalRule = additionalRule;
    }

    public enum AdditionalRule {
        NONE, COMPLETE_TYPE_POINTER, COMPLETE_TYPE, COMPLETE_TYPE_ASSIGNABLE
    }

    public UnaryOperator getUnaryOperator() {
        return unaryOperator;
    }

    public Class<? extends Type> getOperandClass() {
        return operandClass;
    }

    public Class<? extends Type> getResultTypeClass() {
        return resultTypeClass;
    }

    public AdditionalRule getAdditionalRule() {
        return additionalRule;
    }
}
