# PROP Grupo 41.2

## Miembros del grupo:

- Sabaté Iturgaiz, Eneko 
- Aguilera Folqué, Mariona
- Montero Flores, Miguel Ángel
- Ribera Moreno, Pol

- [eneko.sabate@estudiantat.upc.edu]()
- [mariona.aguilera@estudiantat.upc.edu]()
- [miguel.angel.montero@estudiantat.upc.edu]()
- [pol.ribera@estudiantat.upc.edu]()

## Profesor: 

- Ricardo Fernández Domenech ([ricardo.fernandez.domenech@upc.edu]())


## Elementos del directorio

Cada carpeta (menos lib), contiene un **README.md** que explica más
detalladamente el contenido de ésta.

### DOCS:

Contiene la documentación asignada a la primera entrega. En un solo
archivo pdf se encuentra la información necesaria, que son los casos
de uso, el diagrama de clases, las estructuras de datos y algoritmos
y la relación clase miembro.

A parte de ese documento están los gráficos de los casos de uso y
del diagrama de clases.

### EXE:

Se encuentran los archivos ejecutables (.class) de todas las clases
implementadas que se encuentran en la carpeta FONTS. Toda la salida
está en la carpeta out y ahí se encuentran los subdirectorios
de las clases correspondientes.

### FONTS:

Solo tiene la carpeta src, que contiene todas las clases, que son los
tests, los drivers, las excepciones y todas las clases del dominio. 
A parte también tiene el Makefile que genera los ejecutables de todas
los archivos .java.

### lib:

Contiene las bibliotecas externas necesarias (JARs) para utilizar los
tests de los JUnit.

## Nota

El algoritmo implementado en QAP optimized, no da la solución óptima
aunque estuviese programada para que fuese así. El cálculo de la
lower bound durante el branch and bound es erróneo, ya que la mejor
solución que encuentra nuestro algoritmo es la solución dada por
el algoritmo greedy. Somos conscientes de ello y es un fallo que no 
hemos podido resolver.