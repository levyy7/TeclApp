# Directorio src

Este directorio contiene todo el código del proyecto.

## Elementos del directorio

- **Excepcions:**
Contiene todas las excepciones que hacemos saltar en caso de que algo
en el programe no siga la ruta que debería (*especificado en los casos
de uso*)
- **drivers:**
Contiene los drivers (.java) de todos los controladores del dominio,
que sirven para probar el programa.
- **main:**
Contiene los archivos de las clases del dominio.
- **tests:**
Contiene los archivos de los tests implementados de todas las clases
menos la de los controladores.

## Makefile

Para usar cualquiera de las opciones (menos make all) de abajo,
primero se tiene que usar make all para generar los ejecutables.

- `make all`

	Se Crean todos los ejecutables de los drivers (.jar) en el
	directorio EXE.

- `testAlfabeto`

	Se ejectua el testAlfabeto.

- `testPlayout`
	Se ejecuta el testPlayout

- `testTeclado`
	Se ejecuta el testTeclado

- `testTexto`
	Se ejecuta el testTexto

- `testUtA`
	Se ejecuta el testUtA

- `testQAP`
	Se ejecuta el testQAP

- `testAll`
	Se ejecutan todos los tests

- `driverAlgoritmo`
	Se ejecuta el driverAlgoritmo

- `driverDominio`
	Se ejecuta el driverDominio

- `driverEntrada`
	Se ejecuta el driverEntrada

- `clean`
	Se borran todos los ejecutables generados en el make all