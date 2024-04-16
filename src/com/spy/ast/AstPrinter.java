package com.spy.ast;

import com.spy.ast.Expr.Assign;
import com.spy.ast.Expr.Binary;
import com.spy.ast.Expr.Grouping;
import com.spy.ast.Expr.Literal;
import com.spy.ast.Expr.Unary;
import com.spy.ast.Expr.Variable;

class AstPrinter implements Expr.Visitor<String> {
  String print(Expr expr) {
    return expr.accept(this);
  }

  @Override
  public String visitBinaryExpr(Binary expr) {
    return parenthesize(expr.operator.lexeme, expr.left, expr.right);
  }

  @Override
  public String visitGroupingExpr(Grouping expr) {
    return parenthesize("group", expr.expression);
  }

  @Override
  public String visitLiteralExpr(Literal expr) {
    if (expr.value == null) return "nil";
    return expr.value.toString();
  }

  @Override
  public String visitUnaryExpr(Unary expr) {
    return parenthesize(expr.operator.lexeme, expr.right);
  }

  @Override
  public String visitVariableExpr(Variable expr) {
    StringBuilder builder = new StringBuilder();

    builder.append("(");
    builder.append(expr.name);
    builder.append(")");

    return builder.toString();
  }

  @Override
  public String visitAssignExpr(Assign expr) {
    StringBuilder builder = new StringBuilder();

    builder.append("(").append(expr.name.lexeme);
    builder.append(" ");
    builder.append(expr.value);
    builder.append(")");

    return builder.toString();
  }


  private String parenthesize(String name, Expr... exprs) {
    StringBuilder builder = new StringBuilder();

    builder.append("(").append(name);
    for (Expr expr : exprs) {
      builder.append(" ");
      builder.append(expr.accept(this));
    }
    builder.append(")");

    return builder.toString();
  }

  public static void main(String[] args) {
    Expr expression = new Expr.Binary(
        new Expr.Unary(
          new Token(TokenType.MINUS, "-", null, 1),
          new Expr.Literal(123)
        ),
        new Token(TokenType.STAR, "*", null, 1),
        new Expr.Grouping(new Expr.Literal(45.55))
      );

    System.out.println(new AstPrinter().print(expression));
  }
}
