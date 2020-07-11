package tinycc.implementation.utils;

import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Pointer;
import tinycc.implementation.type.Scalar;

public enum UnaryOperatorRule {
    POINT_TO_POINTER(UnaryOperator.POINT_TO, Pointer.class, Scalar.class, AdditionalRule.COMPLETE_TYPE_POINTER),
    ADDRESS_OF_OBJECT(UnaryOperator.ADDRESS_OF, Object.class, Integer.class, AdditionalRule.COMPLETE_TYPE_ASSIGNABLE),
    SIZE_OF_OBJECT(UnaryOperator.SIZE_OF, Object.class, Integer.class, AdditionalRule.COMPLETE_TYPE);

    private final UnaryOperator unaryOperator;
    private final Class<?> operandClass, resultTypeClass;
    private final AdditionalRule additionalRule;

    UnaryOperatorRule(UnaryOperator unaryOperator, Class<?> operand, Class<?> resultType) {
        this.unaryOperator = unaryOperator;
        this.operandClass = operand;
        this.resultTypeClass = resultType;
        this.additionalRule = AdditionalRule.NONE;
    }

    UnaryOperatorRule(UnaryOperator unaryOperator, Class<?> operand, Class<?> resultType, AdditionalRule additionalRule) {
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

    public Class<?> getOperandClass() {
        return operandClass;
    }

    public Class<?> getResultTypeClass() {
        return resultTypeClass;
    }

    public AdditionalRule getAdditionalRule() {
        return additionalRule;
    }
}
