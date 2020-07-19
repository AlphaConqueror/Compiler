package tinycc.implementation;

import tinycc.diagnostic.Locatable;
import tinycc.diagnostic.Location;
import tinycc.implementation.expression.*;
import tinycc.implementation.external.ExternalDeclaration;
import tinycc.implementation.external.GlobalVariable;
import tinycc.implementation.external.TranslationUnit;
import tinycc.implementation.external.function.*;
import tinycc.implementation.statement.*;
import tinycc.implementation.type.Character;
import tinycc.implementation.type.Integer;
import tinycc.implementation.type.Void;
import tinycc.implementation.type.*;
import tinycc.implementation.utils.BinaryOperator;
import tinycc.implementation.utils.EnvironmentalDeclaration;
import tinycc.implementation.utils.Identifier;
import tinycc.implementation.utils.UnaryOperator;
import tinycc.parser.ASTFactory;
import tinycc.parser.Token;
import tinycc.parser.TokenKind;

import java.util.ArrayList;
import java.util.List;

public class AST implements ASTFactory {

    private final TranslationUnit translationUnit;

    public AST() {
        translationUnit = new TranslationUnit();
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

        declaration.setLocatable(getTokenLocation(name));

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
        return new FunctionType(returnType, new ArrayList<>()).addParameters(parameters);
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

        Expression expression;

        if(binaryOperator != BinaryOperator.ASSIGN)
            expression = new BinaryExpression(binaryOperator, left, right);
        else
            expression = new AssignExpression(left, right);

        expression.setLocatable(getTokenLocation(operator));

        return expression;
    }

    @Override
    public Expression createCallExpression(Token token, Expression callee, List<Expression> arguments) {
        CallExpression callExpression = new CallExpression(callee, arguments);

        callExpression.setLocatable(getTokenLocation(token));

        return callExpression;
    }

    @Override
    public Expression createConditionalExpression(Token token, Expression condition, Expression consequence, Expression alternative) {
        ConditionalExpression conditionalExpression = new ConditionalExpression(condition, consequence, alternative);

        conditionalExpression.setLocatable(getTokenLocation(token));

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

        unaryExpression.setLocatable(getTokenLocation(operator));

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

        primaryExpression.setLocatable(getTokenLocation(token));

        return primaryExpression;
    }

    @Override
    public void createExternalDeclaration(Type type, Token name) {
        ExternalDeclaration externalDeclaration;

        if(type instanceof FunctionType) {
            FunctionType functionType = (FunctionType) type;
            List<Parameter> parameters = new ArrayList<>();

            for(Type parameterType : functionType.getParameters())
                parameters.add(new Parameter(parameterType));

            externalDeclaration = new FunctionDeclaration(functionType.getReturnType(), new Identifier(name.getText()), new ParameterList(parameters));
        } else
            externalDeclaration = new GlobalVariable(type, new Identifier(name.getText()));

        externalDeclaration.setLocatable(getTokenLocation(name));
        externalDeclaration.addEnvironmentalDeclarations(translationUnit.getLastEnvironmentalDeclarations());

        translationUnit.addExternalDeclaration(externalDeclaration);
    }

    @Override
    public void createFunctionDefinition(Type type, Token name, List<Token> parameterNames, Statement body) {
        if(type instanceof FunctionType) {
            FunctionType functionType = (FunctionType) type;
            NamedParameterList namedParameters = new NamedParameterList();

            for (int i = 0; i < functionType.getParameters().size(); i++)
                namedParameters.addNamedParameter(new NamedParameter(functionType.getParameters().get(i), new Identifier(parameterNames.get(i).getText())));

            Function function = new Function(functionType.getReturnType(), new Identifier(name.getText()), namedParameters, (Block) body);
            List<EnvironmentalDeclaration> environmentalDeclarations = new ArrayList<>();

            for(EnvironmentalDeclaration environmentalDeclaration : translationUnit.getLastEnvironmentalDeclarations())
                environmentalDeclarations.add(environmentalDeclaration);

            for(ExternalDeclaration externalDeclaration : translationUnit.getExternalDeclarations()) {
                if(externalDeclaration instanceof FunctionDeclaration && externalDeclaration.getIdentifier().toString().equals(function.getIdentifier().toString())) {
                    environmentalDeclarations.remove(externalDeclaration);
                    break;
                }
            }

            function.setLocatable(getTokenLocation(name));
            function.addEnvironmentalDeclarations(environmentalDeclarations);

            translationUnit.addExternalDeclaration(function);
        }
    }

    public TranslationUnit getTranslationUnit() {
        return translationUnit;
    }

    private Location getTokenLocation(Token token) {
        return new Location(token.getInputName(), token.getLine(), token.getColumn());
    }
}
