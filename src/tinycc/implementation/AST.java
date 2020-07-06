package tinycc.implementation;

import tinycc.diagnostic.Locatable;
import tinycc.diagnostic.Location;
import tinycc.implementation.expression.*;
import tinycc.implementation.function.FunctionDeclaration;
import tinycc.implementation.statement.*;
import tinycc.implementation.type.Character;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Void;
import tinycc.implementation.type.*;
import tinycc.implementation.utils.*;
import tinycc.parser.ASTFactory;
import tinycc.parser.Token;
import tinycc.parser.TokenKind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AST implements ASTFactory {

    private final TranslationUnit translationUnit;
    private final List<FunctionDeclaration> functionDeclarations;

    public AST() {
        translationUnit = new TranslationUnit();
        functionDeclarations = new ArrayList<>();
    }

    @Override
    public Statement createBlockStatement(Locatable loc, List<Statement> statements) {
        Block block = new Block().addStatements(statements);

        block.setLocatable(loc);

        return block;
    }

    @Override
    public Statement createBreakStatement(Locatable loc) {
        return null;
    }

    @Override
    public Statement createContinueStatement(Locatable loc) {
        return null;
    }

    @Override
    public Statement createDeclarationStatement(Type type, Token name, Expression init) {
        Declaration declaration = new Declaration(type, new Identifier(name.getText()), init);

        declaration.setLocatable(new Location(name.getInputName(), name.getLine(), name.getColumn()));

        return declaration;
    }

    @Override
    public Statement createEmptyStatement(Locatable loc) {
        EmptyStatement emptyStatement = new EmptyStatement();

        emptyStatement.setLocatable(loc);

        return emptyStatement;
    }

    @Override
    public Statement createErrorStatement(Locatable loc) {
        ErrorStatement errorStatement = new ErrorStatement();

        errorStatement.setLocatable(loc);

        return errorStatement;
    }

    @Override
    public Statement createExpressionStatement(Locatable loc, Expression expression) {
        ExpressionStatement expressionStatement = new ExpressionStatement(expression);

        expressionStatement.setLocatable(loc);

        return expressionStatement;
    }

    @Override
    public Statement createIfStatement(Locatable loc, Expression condition, Statement consequence, Statement alternative) {
        IfStatement ifStatement = new IfStatement(condition, consequence, alternative);

        ifStatement.setLocatable(loc);

        return ifStatement;
    }

    @Override
    public Statement createReturnStatement(Locatable loc, Expression expression) {
        ReturnStatement returnStatement = new ReturnStatement(expression);

        returnStatement.setLocatable(loc);

        return returnStatement;
    }

    @Override
    public Statement createWhileStatement(Locatable loc, Expression condition, Statement body, Expression invariant, Expression term) {
        WhileStatement whileStatement = new WhileStatement(condition, body, invariant, term);

        whileStatement.setLocatable(loc);

        return whileStatement;
    }

    @Override
    public Statement createAssumeStatement(Locatable loc, Expression condition) {
        AssumeStatement assumeStatement = new AssumeStatement(condition);

        assumeStatement.setLocatable(loc);

        return assumeStatement;
    }

    @Override
    public Statement createAssertStatement(Locatable loc, Expression condition) {
        AssertStatement assertStatement = new AssertStatement(condition);

        assertStatement.setLocatable(loc);

        return assertStatement;
    }

    @Override
    public Type createFunctionType(Type returnType, List<Type> parameters) {
        return new FunctionType(returnType, new LinkedList<>()).addParameters(parameters);
    }

    @Override
    public Type createPointerType(Type pointsTo) {
        return new Pointer(pointsTo);
    }

    @Override
    public Type createBaseType(TokenKind kind) {
        switch(kind) {
            case CHAR:
                return new Character('\u0000');
            case INT:
                return new Integer(null);
            case VOID:
                return new Void();
            default:
                break;
        }

        return null;
    }

    @Override
    public Expression createBinaryExpression(Token operator, Expression left, Expression right) {
        BinaryOperator binaryOperator = null;

        for(BinaryOperator op : BinaryOperator.values())
            if(op.toString().equals(operator.getText())) {
                binaryOperator = op;
                break;
            }

        BinaryExpression binaryExpression = new BinaryExpression(binaryOperator, left, right);

        binaryExpression.setLocatable(new Location(operator.getInputName(), operator.getLine(), operator.getColumn()));

        return binaryExpression;
    }

    @Override
    public Expression createCallExpression(Token token, Expression callee, List<Expression> arguments) {
        CallExpression callExpression = new CallExpression(callee, arguments);

        callExpression.setLocatable(new Location(token.getInputName(), token.getLine(), token.getColumn()));

        return callExpression;
    }

    @Override
    public Expression createConditionalExpression(Token token, Expression condition, Expression consequence, Expression alternative) {
        ConditionalExpression conditionalExpression = new ConditionalExpression(condition, consequence, alternative);

        conditionalExpression.setLocatable(new Location(token.getInputName(), token.getLine(), token.getColumn()));

        return conditionalExpression;
    }

    @Override
    public Expression createUnaryExpression(Token operator, boolean postfix, Expression operand) {
        UnaryOperator unaryOperator = null;

        for(UnaryOperator op : UnaryOperator.values())
            if(op.toString().equals(operator.getText())) {
                unaryOperator = op;
                break;
            }

        UnaryExpression unaryExpression = new UnaryExpression(unaryOperator, postfix, operand);

        unaryExpression.setLocatable(new Location(operator.getInputName(), operator.getLine(), operator.getColumn()));

        return unaryExpression;
    }

    @Override
    public Expression createPrimaryExpression(Token token) {
        PrimaryExpression primaryExpression = null;

        switch(token.getKind()) {
            case CHARACTER:
                primaryExpression = new PrimaryExpression(new Character(token.getText().charAt(0)));
                break;
            case IDENTIFIER:
                primaryExpression = new PrimaryExpression(new Identifier(token.getText()));
                break;
            case NUMBER:
                primaryExpression = new PrimaryExpression(new Integer(java.lang.Integer.parseInt(token.getText())));
                break;
            case STRING:
                primaryExpression = new PrimaryExpression(new StringLiteral(token.getText()));
                break;
            default:
                break;
        }

        primaryExpression.setLocatable(new Location(token.getInputName(), token.getLine(), token.getColumn()));

        return primaryExpression;
    }

    @Override
    public void createExternalDeclaration(Type type, Token name) {

    }

    @Override
    public void createFunctionDefinition(Type type, Token name, List<Token> parameterNames, Statement body) {

    }
}
