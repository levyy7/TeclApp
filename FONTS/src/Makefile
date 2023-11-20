CLASS_INPUT =	main/dominio/*.java \
				main/domain/algoritmos/*.java \
				main/domain/ed/*.java 

CLASS_OUTPUT =	../../EXE/out/

JUNIT_JARS = ../../lib/byte-buddy-1.12.16.jar:../../lib/byte-buddy-agent-1.12.16.jar:../../lib/hamcrest-core-1.3.jar:../../lib/unit-4-12_2.13-3.1.4.0.jar:../../lib/mockito-core-4.9.0.jar:../../lib/objenesis-3.3.jar


all:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT)


clean:
	rm -r ../../EXE/out/*
