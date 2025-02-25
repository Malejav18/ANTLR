/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Float> {
    /** "memory" for our calculator; variable/value pairs go here */
    Map<String, Float> memory = new HashMap<String, Float>();

    /** ID '=' expr NEWLINE */
    @Override
    public Float visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();  // id is left-hand side of '='
        if(visit(ctx.expr())==null){
            //System.exit(1);
            return null;
        }
        float value = visit(ctx.expr());   // compute value of expression on right
        memory.put(id, value);           // store it in our memory
        return value;
    }

    /** expr NEWLINE */
    @Override
    public Float visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        Float value = visit(ctx.expr()); // Evaluar la expresión
        if (value != null) { // Solo imprimir si no es null
            System.out.println(value);
        }
        return value;
    }


    /** FLOAT */
    @Override
    public Float visitFloat(LabeledExprParser.FloatContext ctx) {
        return Float.valueOf(ctx.FLOAT().getText());
    }

    /** expr '**' expr (Potencia) */
    @Override
    public Float visitPower(LabeledExprParser.PowerContext ctx) {
        float base = visit(ctx.expr(0));  
        float exponent = visit(ctx.expr(1));  
        return (float) Math.pow(base, exponent);
    }
    
    @Override
    public Float visitAbsolute(LabeledExprParser.AbsoluteContext ctx){
        float value = visit(ctx.expr());
        return Math.abs(value);
    }
    
    

    /** ID */
    @Override
    public Float visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText(); 
        if ( memory.containsKey(id) ) return memory.get(id);
        /* 
        
        */
        return null;
    }

    /** expr op=('*'|'/') expr */
    @Override
    public Float visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        if((null==visit(ctx.expr(0))) || (null==visit(ctx.expr(1)))){
            System.out.println("Error: Argumento vacio mudi");
            //System.exit(1);
            return null;
        }
        float left = visit(ctx.expr(0));  // get value of left subexpression
        float right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == LabeledExprParser.MUL ) return left * right;
        if (right!=0) return left / right; 
        else {
            System.out.println("Error: Division por 0");
            //System.exit(1);
            return null;
        } // must be DIV 
    }

    /** expr op=('+'|'-') expr */
    @Override
    public Float visitAddSub(LabeledExprParser.AddSubContext ctx) {
        if((null==visit(ctx.expr(0))) || (null==visit(ctx.expr(1)))){
            System.out.println("Error: Argumento vacio");
            //System.exit(1);
            return null;
        }
        float left = visit(ctx.expr(0));  // get value of left subexpression
        float right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == LabeledExprParser.ADD ) return left + right;
        return left - right; // must be SUB
    }

    /** '(' expr ')' */
    @Override
    public Float visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr()); // return child expr's value
    }
    
    /** '-' expr */
    @Override
    public Float visitNeg(LabeledExprParser.NegContext ctx) {
        if(null==visit(ctx.expr())){
            System.out.println("Error: Signo - sin argumento");
            //System.exit(1);
            return null;
        }
        float value=visit(ctx.expr());
        return -(value); // return negative of child expr's value
    }

    @Override
    public Float visitError(LabeledExprParser.ErrorContext ctx){
        System.out.println("Error: Operadores seguidos");
        return null;
    }
}
