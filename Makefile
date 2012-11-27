#lander
JAVAC=javac
JAR=jar
RM=rm -f

JAVAFLAGS+=-d build

SRCFILES=$(wildcard *.java)
OBJFILES=$(SRCFILES:.java=.class)
BINFILE=lander.jar

.PHONY: all clean

all: ${OBJFILES}
	@echo " [ JAR ] $(BINFILE)"
	@cd build && $(JAR) -cfm ../$(BINFILE) ../lander.manifest *.class
	@chmod +x lander.jar
	
clean:
	@echo " [ RM  ] build/*.class"
	@$(RM) "build/*.class"
	@echo " [ RM  ] $(BINFILE)"
	@$(RM) $(BINFILE)

%.class: %.java
	@echo " [JAVAC] $<"
	@$(JAVAC) $(JAVAFLAGS) "$<"
