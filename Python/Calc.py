import sys
from antlr4 import *
from LabeledExprLexer import LabeledExprLexer
from LabeledExprParser import LabeledExprParser
from EvalVisitor import EvalVisitor


def process_input(input_stream, visitor):
    lexer = LabeledExprLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = LabeledExprParser(stream)
    tree = parser.prog()
    visitor.visit(tree)    


def main():
    input_file = sys.argv[1] if len(sys.argv) > 1 else None
    visitor = EvalVisitor()

    if input_file:
        try:
            with open(input_file, 'r') as file:
                input_text = file.read()  # Leer todo el archivo
                input_stream = InputStream(input_text)  # Convertir a InputStream
                process_input(input_stream, visitor)

        except FileNotFoundError:
            print(f"Error: No se encuentra el archivo '{input_file}'")

    else:
        while True:
            try:
                expr = input("calc> ")
                input_stream = InputStream(expr)
                process_input(input_stream, visitor)
            except EOFError:
                break


if __name__ == "__main__":
    main()

