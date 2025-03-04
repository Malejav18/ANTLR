grammar LabeledExpr;

prog : stat+;

stat: expr NEWLINE	        # printExpr
	| ID '=' expr NEWLINE   # assign
	| NEWLINE		# blank
	;

expr:'sin' '(' expr ')'              #sin 
    |'cos' '(' expr ')'              #cos
    |'tan' '(' expr ')'              #tan
    |'log3' '(' expr ')'           #log3
	|'(' expr ')'	# Parens
	| '|' expr '|'  # Absolute
	| expr POW expr # Power
	| expr DIVENT expr #Divent
	| expr MOD expr # Modulus
	| '-' expr	# Neg
	| '+' expr #Pos
	| expr op=('*' | '/') expr #MulDiv
	| expr op=('+' | '-') expr #AddSub
	| FLOAT #Float
	| ID #Id
	;

MUL : '*';
DIV : '/';
ADD : '+';
SUB : '-';
POW : '**';
DIVENT : '//';
MOD : '%';
ID : [a-zA-Z]+ ;
FLOAT : [0-9]+ ('.' [0-9]*)? | ('.' [0-9]+) ;
NEWLINE : 'r'? '\n';
WS :  [ \t]+ -> skip ;
