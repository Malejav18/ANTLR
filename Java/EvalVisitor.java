import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Float> {
	/** "memory" for our calculator; variable/value pairs go here */
	Map<String, Float> memory = new HashMap<String, Float>();
	
	/** ID '=' expr NEWLINE */
	@Override
	public Float visitAssign(LabeledExprParser.AssignContext ctx){
		String id = ctx.ID().getText();
		if(visit(ctx.expr()) == null){
			return null;
		}
		float  value = visit(ctx.expr());
		memory.put(id, value);
		return value;
	}
	
	/** expr NEWLINE */
	public Float visitPrintExpr(LabeledExprParser.PrintExprContext ctx){
		Float value = visit(ctx.expr());
		if (value != null){
			System.out.println(value);
		}
		return value;
	}
	
	/** FLOAT */
	@Override
	public Float visitFloat(LabeledExprParser.FloatContext ctx){
		return Float.valueOf(ctx.FLOAT().getText());
	}	
	
	/** expr '**' expr (Potencia) */
	@Override
	public Float visitPower(LabeledExprParser.PowerContext ctx) {
		float base = visit(ctx.expr(0));
		float exponente = visit(ctx.expr(1));
		return (float) Math.pow(base, exponente);
	}
	
	/** '|' expr '|' */
	@Override
	public Float visitAbsolute(LabeledExprParser.AbsoluteContext ctx){
		if(ctx.getStart().getText().equals("|") && !ctx.getStop().getText().equals("|")){
			System.out.println("Error: Falta el | de cierre.");
			return null;
		}
		float value = visit(ctx.expr());
		return Math.abs(value);
	}

	/** expr '//' expr */
	@Override
	public Float visitDivent(LabeledExprParser.DiventContext ctx){
		float num = visit(ctx.expr(0));
		float den = visit(ctx.expr(1));
		if(den == 0){
			System.out.println("Error: Division por 0");
			return null;
		}
		else{
			return (float) Math.floor(num / den);
		}
	}
	
	
	/** expr % expr */
	@Override
        public Float visitModulus(LabeledExprParser.ModulusContext ctx){
                float num = visit(ctx.expr(0));
                float den = visit(ctx.expr(1));
                if(den == 0){
                        System.out.println("Error: Division por 0");
                        return null;
                }
                else{
                        return num % den;
                }
        }

	/** ID */
	@Override
	public Float visitId(LabeledExprParser.IdContext ctx){
		String id = ctx.ID().getText();
		if(memory.containsKey(id)) return memory.get(id);
		return null;
	}

	/** expr op=('*'|'/') expr */
	public Float visitMulDiv(LabeledExprParser.MulDivContext ctx){
		if((null==visit(ctx.expr(0))) || (null==visit(ctx.expr(1)))){
			System.out.println("Error: Argumento vacio");
			return null;
		}
		float left = visit(ctx.expr(0));
		float right = visit(ctx.expr(1));
		if(ctx.op.getType() == LabeledExprParser.MUL) return left * right;
		if(right!=0) return left/right;
		else{
			System.out.println("Error: Division por 0");
			return null;
		}
	}
	
	/** expr op=('+' | '-') expr */
	@Override
	public Float visitAddSub(LabeledExprParser.AddSubContext ctx){
		if((null==visit(ctx.expr(0))) || (null==visit(ctx.expr(1)))){
			System.out.println("Error: Argumento vacio");
			return null;
		}
		float left = visit(ctx.expr(0));
		float right = visit(ctx.expr(1));
		if(ctx.op.getType() == LabeledExprParser.ADD) return left + right;
		return left - right;
	}

	/** '(' expr ')' */
        @Override
        public Float visitParens(LabeledExprParser.ParensContext ctx){
                if(ctx.getStart().getText().equals("(") && !ctx.getStop().getText().equals(")")){
                        System.out.println("Error: Falta el ) de cierre.");
                        return null;
                }
                float value = visit(ctx.expr());
                return value;
        }

	/** '-' expr */
	@Override
	public Float visitNeg(LabeledExprParser.NegContext ctx){
		if(null == visit(ctx.expr())){
			System.out.println("Error: Signo - sin argumento");
			return null;
		}
		float value=visit(ctx.expr());
		return -(value);
	}

	/** '+' expr */
	@Override
	public Float visitPos(LabeledExprParser.PosContext ctx){
		if(null==visit(ctx.expr())){
			System.out.println("Error: Signo + sin argumento");
			return null;
		}
		float value = visit(ctx.expr());
		return value;
	}

}
