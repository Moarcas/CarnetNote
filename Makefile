# Variables
JAVAC = javac
JAVA = java
JAR = jar
SRCDIRS := $(shell find $(SRC) -type d)
SRCS := $(foreach dir, $(SRCDIRS), $(wildcard $(dir)/*.java))
BINDIR = bin
LIBDIR = lib
MAINCLASS = Main
CLASSPATH = $(LIBDIR)/sqlite-jdbc-3.36.0.3.jar:$(BINDIR)

# Set the default target
default: run

# Compile the Java classes
classes:
	mkdir -p $(BINDIR)
	$(JAVAC) -d $(BINDIR) -cp $(CLASSPATH) $(SRCS)

# Create the JAR file
jar: classes
	$(JAR) cvfm myapp.jar Manifest.txt -C $(BINDIR) .

# Run the application
run: jar
	$(JAVA) -jar myapp.jar

# Clean the build files
clean:
	rm -rf $(BINDIR)/* myapp.jar

# Specify the dependencies for the jar target
jar: $(wildcard $(addsuffix /*.java, $(SRCDIRS))) $(LIBDIR)/sqlite-jdbc-3.36.0.3.jar

# Copy the SQLite JDBC driver to the build directory
$(LIBDIR)/sqlite-jdbc-3.36.0.3.jar:
	mkdir -p $(LIBDIR)
	cp $@ $(LIBDIR)

# Define a phony target for the run command
.PHONY: run

