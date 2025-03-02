import sys
from antlr4 import *
from LabeledExprLexer import LabeledExprLexer
from LabeledExprParser import LabeledExprParser
from LabeledExprVisitor import LabeledExprVisitor

class EvalVisitor(LabeledExprVisitor):
    def __init__(self):
        self.memory = {}  # Diccionario para almacenar variables
    
    def visitAssign(self, ctx):
        id = ctx.ID().getText()
        value = self.visit(ctx.expr())
        if value is None:
            print("Error: asignación inválida")
            return None
        self.memory[id] = value
        return value

    def visitPrintExpr(self, ctx):
        value = self.visit(ctx.expr())
        if value is not None:
            print(value)
        return value

    def visitFloat(self, ctx):
        return float(ctx.FLOAT().getText())

    def visitPower(self, ctx):
        base = self.visit(ctx.expr(0))
        exponent = self.visit(ctx.expr(1))
        return base ** exponent

    def visitAbsolute(self, ctx):
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
        elif right != 0:
            return left / right
        else:
            print("Error: División por 0")
            return None

    def visitAddSub(self, ctx):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == LabeledExprParser.ADD:
            return round(left + right, 10)
        return round(left - right, 10)


    def visitParens(self, ctx):
        return self.visit(ctx.expr())

    def visitNeg(self, ctx):
        value = self.visit(ctx.expr())
        return -value if value is not None else None

    def visitError(self, ctx):
        print("Error: Operadores inválidos")
        return None

def process_input(input_stream, visitor):
    input_text = input_stream.getText(0, input_stream.size - 1)
    input_stream = InputStream(input_text + "\n")  # Agrega el salto de línea
    lexer = LabeledExprLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = LabeledExprParser(stream)
    tree = parser.prog()
    visitor.visit(tree)


def main():
    visitor = EvalVisitor()

    if len(sys.argv) > 1:  # Si se proporciona un archivo
        filename = sys.argv[1]
        try:
            with open(filename, "r") as file:
                for line in file:
                    line = line.strip()
                    if line:
                        print(f"> {line}")
                        input_stream = InputStream(line)
                        process_input(input_stream, visitor)
        except FileNotFoundError:
            print(f"Error: No se encontró el archivo '{filename}'")
    else:  # Modo interactivo
        while True:
            try:
                expr = input("calc> ")  
                if expr.lower() in {"exit", "quit"}:
                    break

                input_stream = InputStream(expr)
                process_input(input_stream, visitor)

            except Exception as e:
                print(f"Error: {e}")

if __name__ == "__main__":
    main()
