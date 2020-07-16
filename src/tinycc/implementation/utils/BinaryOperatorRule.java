package tinycc.implementation.utils;

import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Pointer;
import tinycc.implementation.type.Type;
import tinycc.implementation.type.WholeNumber;

public enum BinaryOperatorRule {

    MUL_INT_INT(BinaryOperator.MUL, WholeNumber.class, WholeNumber.class, Integer.class),
    DIV_INT_INT(BinaryOperator.DIV, WholeNumber.class, WholeNumber.class, Integer.class),
    ADD_INT_INT(BinaryOperator.ADD, WholeNumber.class, WholeNumber.class, Integer.class),
    ADD_POINTER_INT(BinaryOperator.ADD, Pointer.class, WholeNumber.class, Pointer.class, AdditionalRule.COMPLETE_TYPE_POINTER),
    SUB_INT_INT(BinaryOperator.SUB, WholeNumber.class, WholeNumber.class, Integer.class),
    SUB_POINTER_INT(BinaryOperator.SUB, Pointer.class, WholeNumber.class, Pointer.class, AdditionalRule.COMPLETE_TYPE_POINTER),
    SUB_POINTER_POINTER(BinaryOperator.SUB, Pointer.class, Pointer.class, Integer.class, AdditionalRule.IDENTICAL_POINTER_CT),
    EQ_INT_INT(BinaryOperator.EQ, WholeNumber.class, WholeNumber.class, Integer.class),
    EQ_POINTER_POINTER(BinaryOperator.EQ, Pointer.class, Pointer.class, Integer.class, AdditionalRule.IP_VOID_NULL_POINTER),
    BANG_EQ_INT_INT(BinaryOperator.BANG_EQ, WholeNumber.class, WholeNumber.class, Integer.class),
    BANG_EQ_POINTER_POINTER(BinaryOperator.BANG_EQ, Pointer.class, Pointer.class, Integer.class, AdditionalRule.IP_VOID_NULL_POINTER),
    LT_INT_INT(BinaryOperator.LT, WholeNumber.class, WholeNumber.class, Integer.class),
    LT_POINTER_POINTER(BinaryOperator.LT, Pointer.class, Pointer.class, Integer.class, AdditionalRule.IDENTICAL_POINTER_CT),
    GT_INT_INT(BinaryOperator.GT, WholeNumber.class, WholeNumber.class, Integer.class),
    GT_POINTER_POINTER(BinaryOperator.GT, Pointer.class, Pointer.class, Integer.class, AdditionalRule.IDENTICAL_POINTER_CT),
    LE_INT_INT(BinaryOperator.LE, WholeNumber.class, WholeNumber.class, Integer.class),
    LE_POINTER_POINTER(BinaryOperator.LE, Pointer.class, Pointer.class, Integer.class, AdditionalRule.IDENTICAL_POINTER_CT),
    GE_INT_INT(BinaryOperator.GE, WholeNumber.class, WholeNumber.class, Integer.class),
    GE_POINTER_POINTER(BinaryOperator.GE, Pointer.class, Pointer.class, Integer.class, AdditionalRule.IDENTICAL_POINTER_CT),
    AND_INT_INT(BinaryOperator.AND, Integer.class, Integer.class, Integer.class),
    OR_INT_INT(BinaryOperator.OR, Integer.class, Integer.class, Integer.class);

    private final BinaryOperator binaryOperator;
    private final Class<? extends Type> LOperandClass, ROperandClass, resultTypeClass;
    private final AdditionalRule additionalRule;

    BinaryOperatorRule(BinaryOperator binaryOperator, Class<? extends Type> LOperandClass, Class<? extends Type> ROperandClass, Class<? extends Type> resultTypeClass) {
        this.binaryOperator = binaryOperator;
        this.LOperandClass = LOperandClass;
        this.ROperandClass = ROperandClass;
        this.resultTypeClass = resultTypeClass;
        this.additionalRule = AdditionalRule.NONE;
    }

    BinaryOperatorRule(BinaryOperator binaryOperator, Class<? extends Type> LOperandClass, Class<? extends Type> ROperandClass, Class<? extends Type> resultTypeClass, AdditionalRule additionalRule) {
        this.binaryOperator = binaryOperator;
        this.LOperandClass = LOperandClass;
        this.ROperandClass = ROperandClass;
        this.resultTypeClass = resultTypeClass;
        this.additionalRule = additionalRule;
    }

    public enum AdditionalRule {
        NONE, COMPLETE_TYPE_POINTER, IDENTICAL_POINTER_CT, IP_VOID_NULL_POINTER
    }

    public BinaryOperator getBinaryOperator() {
        return binaryOperator;
    }

    public Class<? extends Type> getLOperandClass() {
        return LOperandClass;
    }

    public Class<? extends Type> getROperandClass() {
        return ROperandClass;
    }

    public Class<? extends Type> getResultTypeClass() {
        return resultTypeClass;
    }

    public AdditionalRule getAdditionalRule() {
        return additionalRule;
    }
}
