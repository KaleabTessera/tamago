OCAMLC=ocamlc
OCAMLYACC=ocamlyacc
OCAMLLEX=ocamllex
OCAMLFLAGS=str.cma
OCAMLDEP=ocamldep
OCAMLOPT=ocamlopt
OCAMLDOC=ocamldoc
BIN=tamagocdl
OCAMLJAVA=java -jar ~/firefox/ocaml*bin*/bin/ocamljava.jar
OCAMLJAVALEX=java -jar ~/firefox/ocaml*bin*/bin/ocamllex.jar
OCAMLJAVAFLAGS=-g -i -rectypes -jopt "-g -Xms 256m -Xmx 1024m" -c -java-package tamagocdl


GENSOURCES=lexer.ml parser.ml

OBJS=synt.cmo parser.cmo lexer.cmo convertion.cmo convertion_latex.cmo main.cmo 
OPTS=$(OBJS:.cmo=.cmx)

SOURCES=$(OBJS:.cmo=.ml)

OBJSCMJ=$(OBJS:.cmo=.cmj)

all:but

but:$(OBJS) 
	$(OCAMLC) $(OCAMLFLAGS) -o $(BIN) $(OBJS)

butopt:$(OPTS)
	$(OCAMLOPT) $(OCAMLFLAGS) -o $(BIN) $(OPTS)

doc:
	$(OCAMLDOC) -v -html -d documentation -t "Tamago-CDL Generator" $(SOURCES)

run:but 
	./$(BIN) -xml service.cdl
	./$(BIN) -xml component.cdl
	./$(BIN) -xml composite.cdl
runtex:but
	./$(BIN) -latex service.cdl > service.tex
	./$(BIN) -latex component.cdl > component.tex
	./$(BIN) -latex composite.cdl > composite.tex

java: $(OBJSCMJ)

lexer.cmj:
	$(OCAMLJAVALEX) -ml lexer.mll
	$(OCAMLJAVA) $(OCAMLJAVAFLAGS) lexer.ml

%.cmj: %.ml
	$(OCAMLJAVA) $(OCAMLJAVAFLAGS) $<

.SUFFIXES: .mly .mll .ml .mli .cmo .cmi .cmx


.ml.cmo:
	$(OCAMLC) $(OCAMLFLAGS) -c $<

.mli.cmi:
	$(OCAMLC) $(OCAMLFLAGS) -c $<

.ml.cmx:
	$(OCAMLOPT) $(OCAMLFLAGS) -c $<


.mly.ml:
	$(OCAMLYACC) -v $<
	$(OCAMLC) -i $(<:.mly=.ml) > $(<:.mly=.mli)
	$(OCAMLC) $(<:.mly=.mli)


.mll.ml:
	$(OCAMLLEX) $<

clean:
	rm -f $(BIN) *.cmi *.cmo *.o *.cmx $(GENSOURCES:.ml=.cmi)  $(GENSOURCES:.ml=.cmo) $(GENSOURCES) *.xml *.cmj *.jo


depend: synt.cmo $(GENSOURCES)
	$(OCAMLDEP) *.ml *.mli > .depend

parser.mli: 
include .depend
