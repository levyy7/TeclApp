MAIN_INPUT = ./main/dominio/*.java ./main/dominio/algoritmos/*.java ./main/dominio/ed/*.java ./Excepcions/*.java ./main/persistencia/*.java

TEST_INPUT = ./tests/*.java

DRIVER_INPUT = ./drivers/*.java

CLASS_OUTPUT =	../../EXE/out/

LIBS = ../../lib/byte-buddy-1.12.16.jar:../../lib/byte-buddy-agent-1.12.16.jar:../../lib/hamcrest-core-1.3.jar:../../lib/junit-4.12.jar:../../lib/mockito-core-4.9.0.jar:../../lib/objenesis-3.3.jar



all:
	javac -d $(CLASS_OUTPUT) $(MAIN_INPUT) $(DRIVER_INPUT)
	javac -cp $(LIBS) -d $(CLASS_OUTPUT) $(MAIN_INPUT) $(TEST_INPUT)


testAlfabeto: 
	java -cp $(LIBS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore tests.AlfabetoTest

testListaPalabras: 
	java -cp $(LIBS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore tests.ListaPalabrasTest

testPlayout:
	java -cp $(LIBS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore tests.PlayoutTest

testTeclado: 
	java -cp $(LIBS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore tests.TecladoTest

testTexto: 
	java -cp $(LIBS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore tests.TextoTest
	
testGestorCSV: 
	java -cp $(LIBS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore tests.GestorCSVTest

testAll: testAlfabeto testListaPalabras testPlayout testTeclado testTexto testGestorCSV

driverAlgoritmo:
	java -cp $(CLASS_OUTPUT) drivers.DriverAlgoritmo

driverDominio:
	java -cp $(CLASS_OUTPUT) drivers.DriverDominio

driverEntrada:
	java -cp $(CLASS_OUTPUT) drivers.DriverEntrada

driverPersistencia:
	java -cp $(CLASS_OUTPUT) drivers.DriverPersistencia

clean:
	rm -r ../../EXE/out/*
