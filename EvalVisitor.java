/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
 * EvalVisitor.java
 * Adaptado para un entorno de producción.
 * Mejora del manejo de errores críticos con excepciones controladas.
***/

import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Float> {
    /** Mapa de memoria para almacenar variables y sus valores */
    Map<String, Float> memory = new HashMap<>();

    /** Asignación de variables: ID '=' expr NEWLINE */
    @Override
    public Float visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        try {
            Float value = visit(ctx.expr());
            if (value == null) {
                throw new IllegalArgumentException("Asignación inválida para la variable: " + id);
            }
            memory.put(id, value); // Almacena el valor en la memoria
            return value;
        } catch (Exception e) {
            System.err.println("Error en la asignación: " + e.getMessage());
            return null;
        }
    }

    /** Evaluación de expresiones y su impresión: expr NEWLINE */
    @Override
    public Float visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        try {
            Float value = visit(ctx.expr());
            if (value == null) {
                throw new IllegalArgumentException("Expresión inválida en la impresión");
            }
            System.out.println(value); // Muestra el resultado en consola
            return 0f;
        } catch (Exception e) {
            System.err.println("Error al imprimir: " + e.getMessage());
            return null;
        }
    }

    /** Manejo de números flotantes: FLOAT */
    @Override
    public Float visitFloat(LabeledExprParser.FloatContext ctx) {
        try {
            return Float.valueOf(ctx.FLOAT().getText());
        } catch (NumberFormatException e) {
            System.err.println("Número flotante mal formado: " + e.getMessage());
            return null;
        }
    }

    /** Evaluación de identificadores: ID */
    @Override
    public Float visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id); // Retorna el valor almacenado
        }
        System.err.println("Variable desconocida: " + id);
        return null;
    }

    /** Operaciones de multiplicación y división: expr op=('*'|'/') expr */
    @Override
    public Float visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        try {
            Float left = visit(ctx.expr(0)); // Evaluación del operando izquierdo
            Float right = visit(ctx.expr(1)); // Evaluación del operando derecho

            if (left == null || right == null) {
                throw new IllegalArgumentException("Expresión de multiplicación o división inválida");
            }

            if (ctx.op.getType() == LabeledExprParser.MUL) {
                return left * right; // Multiplicación
            }

            if (right == 0) {
                throw new ArithmeticException("División por 0");
            }
            return left / right; // División
        } catch (Exception e) {
            System.err.println("Error en la operación multiplicación/división: " + e.getMessage());
            return null;
        }
    }

    /** Operaciones de suma y resta: expr op=('+'|'-') expr */
    @Override
    public Float visitAddSub(LabeledExprParser.AddSubContext ctx) {
        try {
            Float left = visit(ctx.expr(0)); // Evaluación del operando izquierdo
            Float right = visit(ctx.expr(1)); // Evaluación del operando derecho

            if (left == null || right == null) {
                throw new IllegalArgumentException("Expresión de suma o resta inválida");
            }

            if (ctx.op.getType() == LabeledExprParser.ADD) {
                return left + right; // Suma
            }
            return left - right; // Resta
        } catch (Exception e) {
            System.err.println("Error en la operación suma/resta: " + e.getMessage());
            return null;
        }
    }

    /** Evaluación de expresiones entre paréntesis: '(' expr ')' */
    @Override
    public Float visitParens(LabeledExprParser.ParensContext ctx) {
        try {
            Float value = visit(ctx.expr()); // Evaluación de la expresión interna
            if (value == null) {
                throw new IllegalArgumentException("Paréntesis mal balanceados o expresión inválida");
            }
            return value; // Devuelve el resultado de la expresión interna
        } catch (Exception e) {
            System.err.println("Error en la expresión con paréntesis: " + e.getMessage());
            return null;
        }
    }

    /** Manejo de números negativos: '-' expr */
    @Override
    public Float visitNeg(LabeledExprParser.NegContext ctx) {
        try {
            Float value = visit(ctx.expr()); // Evaluación de la expresión
            if (value == null) {
                throw new IllegalArgumentException("Signo menos sin argumento válido");
            }
            return -value; // Retorna el valor negativo
        } catch (Exception e) {
            System.err.println("Error en la expresión negativa: " + e.getMessage());
            return null;
        }
    }
}

