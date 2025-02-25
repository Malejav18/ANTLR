grammar LabeledExpr; // rename to distinguish from Expr.g4

prog:   stat+ ;

stat:   expr NEWLINE                # printExpr
    |   ID '=' expr NEWLINE         # assign
    |   NEWLINE                     # blank
    ;

expr:   '(' expr ')'                # Parens
    |   '|' expr '|'                # Absolute
    |   '-' expr                    # Neg
    |   expr POW expr               # Power
    |   expr op=('*'|'/') expr      # MulDiv
    |   expr op=('+'|'-') expr      # AddSub
    |	expr op=('+'|'-'|'**'|'*'|'/') op=('+'|'-'|'**'|'*'|'/') expr      #Error
    |   FLOAT                       # Float
    |   ID                          # Id
    ;
    
MUL :   '*' ; // assigns token name to '*' used above in grammar
DIV :   '/' ;
ADD :   '+' ;
SUB :   '-' ;
POW :   '**' ;
ID  :   [a-zA-Z]+ ;      // match identifiers
FLOAT : [0-9]+ ('.' [0-9]*)?|('.' [0-9]+) ;
NEWLINE:'\r'? '\n' ;     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace
