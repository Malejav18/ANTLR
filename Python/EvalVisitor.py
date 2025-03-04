import sys
from antlr4 import *
from LabeledExprLexer import LabeledExprLexer
from LabeledExprParser import LabeledExprParser
from LabeledExprVisitor import LabeledExprVisitor

class EvalVisitor(LabeledExprVisitor):
	def __init__(self):
		self.memory = {} #Diccionario para almacenar variables
	def visitAssign(self,ctx):
		id = ctx.ID().getText()
		value = self.visit(ctx.expr())
		if value is None:
			print('Error: asignacion vacia')
			return None
		self.memory[id] = value
		return value
	def visitPrintExpr(self, ctx):
		value = self.visit(ctx.expr())
		if value is not None:
			print(value)
		return value
	def visitFloat(self,ctx):
		return float(ctx.FLOAT().getText())
	def visitPower(self, ctx):
		base = self.visit(ctx.expr(0))
		exponente = self.visit(ctx.expr(1))
		return base ** exponente
	def visitDivent(self,ctx):
		num = self.visit(ctx.expr(0))
		den = self.visit(ctx.expr(1))
		if den == 0:
			print("Error: Division por cero")
			return None
		else:
		    return num // den
	def visitModulus(self,ctx):
	    num = self.visit(ctx.expr(0))
	    den = self.visit(ctx.expr(1))
	    if den == 0:
	        print("Error: Division por cero")
	        return None
	    else:
	        return num % den
	def visitAbsolute(self,ctx):
	    text = ctx.getText()
	    if not (text.startswith("|") and text.endswith("|")):
	        print("Error: Parentesis no cerrados correctamente")
	        return None
	    value = self.visit(ctx.expr())
	    return abs(value)
	def visitId(self, ctx):
		id = ctx.ID().getText()
		return self.memory.get(id, None)
	def visitMulDiv(self, ctx):
		left = self.visit(ctx.expr(0))
		right = self.visit(ctx.expr(1))
		if ctx.op.type == LabeledExprParser.MUL:
			return left * right
		else:
			if right == 0:
				print("Error: Division por cero")
				return None
			else:
				return left / right
	def visitAddSub(self,ctx):
		left = self.visit(ctx.expr(0))
		right = self.visit(ctx.expr(1))
		if ctx.op.type == LabeledExprParser.ADD:
			return round(left + right, 10)
		return round(left - right, 10)
	def visitParens(self, ctx):
	    text = ctx.getText()
	    if not (text.startswith("(") and text.endswith(")")):
	        print("Error: Parentesis no cerrados correctamente")
	        return None
	    return self.visit(ctx.expr())
	def visitNeg(self,ctx):
		value = self.visit(ctx.expr())
		return -value if value is not None else None
	def visitPos(self,ctx):
	    value = self.visit(ctx.expr())
	    return value if value is not None else None
	def visitSin(self, ctx):
		value = self.visit(ctx.expr())
		return math.sin(value)
	def visitCos(self, ctx):
		value = self.visit(ctx.expr())
		return math.cos(value)
	def visitTan(self, ctx):
		value = self.visit(ctx.expr())
		return math.tan(value)
	def visitLog3(self, ctx):
		value = self.visit(ctx.expr())
		return math.log(value,3
	
