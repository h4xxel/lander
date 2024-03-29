#lander
MAKEFLAGS+=--no-print-directory
JAVAC=javac
JAR=jar
RM=rm -f

JAVAFLAGS+=-d build

SRCFILES=$(wildcard *.java)
SRCFILES+=test/TestLander.java
OBJFILES=$(SRCFILES:.java=.class)
BINFILE=lander.jar

.PHONY: all jar clean

all:
	@mkdir -p build
	+@make jar

jar: ${OBJFILES} test
	@echo " [ JAR ] $(BINFILE)"
	@cd build && $(JAR) -cfm ../$(BINFILE) ../lander.manifest *.class
	@chmod +x lander.jar
	
clean:
	@echo " [ RM  ] build/"
	@$(RM) -rf build
	@echo " [ RM  ] $(BINFILE)"
	@$(RM) $(BINFILE)
	
%.class: %.java
	@echo " [JAVAC] $<"
	@$(JAVAC) $(JAVAFLAGS) "$<"
