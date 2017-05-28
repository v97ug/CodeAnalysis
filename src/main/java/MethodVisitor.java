import MyUtil.NodeListUtil;
import MyUtil.OptionalUtil;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.*;

/**
 * Created by takeyuki on 17/05/27.
 */
public class MethodVisitor extends VoidVisitorAdapter<Object> {
    private ListMethod methodsInfo = new ListMethod();

    public MethodVisitor(CompilationUnit cu) {
        visit(cu,0);
    }

    public ListMethod getMethodsInfo() {
        return methodsInfo;
    }

    @Override
    public void visit(MethodDeclaration declaration, Object arg) {
        System.out.printf("VISIT MethodDeclaration: %s, %s\n", declaration.getName(), arg);

        List<MyStatement> statements = new ArrayList<>();

        OptionalUtil.doIfPresent(declaration.getBody(), (BlockStmt smts) -> {
            for (Statement stmt : smts.getStatements()) {
                if (stmt instanceof ExpressionStmt) {
                    ExpressionStmt expressionStmt = (ExpressionStmt) stmt;
                    Expression expression = expressionStmt.getExpression();
                    if (expression instanceof VariableDeclarationExpr) {
                        VariableDeclarationExpr variableDeclarationExpr = (VariableDeclarationExpr) expression;
                        for (VariableDeclarator variableDeclarator : variableDeclarationExpr.getVariables()) {
                            SimpleName variableName = variableDeclarator.getName();

                            OptionalUtil.doIfPresent(variableDeclarator.getInitializer(), (Expression initializer) -> {
                                if (initializer instanceof MethodCallExpr) {
                                    MyStatement statement = makeStatement(variableName.toString(), (MethodCallExpr) initializer);
                                    statements.add(statement);
                                }
                            });
                        }
                    } else if (expression instanceof AssignExpr) {
                        AssignExpr assignExpr = (AssignExpr) expression;
                        Expression target = assignExpr.getTarget(); //必要
                        Expression value = assignExpr.getValue();

                        if (value instanceof MethodCallExpr) {
                            MyStatement statement = makeStatement(target.toString(), (MethodCallExpr) value);
                            statements.add(statement);
                        }
                    }
                }
            }
        });

        MyMethod method = new MyMethod(
                declaration.getType().toString(),
                declaration.getName().toString(),
                declaration.getParameters(),
                statements
        );
        methodsInfo.add(method);
        method.printMethod();

        super.visit(declaration, arg);
    }

    private MyStatement makeStatement(String variableName, MethodCallExpr methodCallExpr){
        // <scope>.<methodName>(<arguments>)
        // e.g.)
        // System.out.println("hello") => <System.out>.<println>(<"hello">)

        Optional<Expression> scopeOpt = methodCallExpr.getScope();
        String scope = scopeOpt.isPresent() ? scopeOpt.get().toString() : "";

        SimpleName methodName = methodCallExpr.getName();
        NodeList<Expression> arguments = methodCallExpr.getArguments();

        return new MyStatement(
                variableName,
                methodName.toString(),
                scope,
                NodeListUtil.toArrayListString(arguments));
    }
}
