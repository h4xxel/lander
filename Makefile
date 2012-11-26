#Lander
JAVAC=javac
JAR=jar
RM=rm -f

SRCFILES=$(wildcard *.java)
OBJFILES=$(SRCFILES:.java=.class)
BINFILE=lander.jar

.PHONY: all clean

all: ${OBJFILES}
	@echo " [ JAR ] $(BINFILE)"
	@$(JAR) -cfm $(BINFILE) lander.manifest *.class
	
clean:
	@echo " [ RM  ] $(OBJFILES)"
	@$(RM) $(OBJFILES)
	@echo " [ RM  ] $(BINFILE)"
	@$(RM) $(BINFILE)

%.class: %.java
	@echo " [JAVAC] $<"
	@$(JAVAC) $(JAVAFLAGS) $<
