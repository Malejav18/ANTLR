import sys
from antlr4 import *
from LabeledExprLexer import LabeledExprLexer
from LabeledExprParser import LabeledExprParser
from LabeledExprVisitor import LabeledExprVisitor
from EvalVisitor import EvalVisitor


def process_input(input_stream, visitor):
	input_text = input_stream.getText(0, input_stream.size - 1)
	input_stream = InputStream(input_text + "\n")
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
				for line in file:
					line = line.strip()
					if line:
						print(f"> {line}")
						process_input(input_stream, visitor)

		except FileNotFoundError:
			print(f"Error: No se encuentra el archivo '{filename}'")
		
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
