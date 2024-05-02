# TeclApp - Custom Layout Generator

This is an app developed for the PROP subject, 3rd grade at Universitat Politécnica de Catalunya.

The goal of this project is to create a user-friendly app which can be used to generate efficietly-writable layouts for any given language. That is, given some set of characters and texts using that language, it generates a layout in which, after some adjustment, one should be able to write really fast.

We have implemented two approach this problem:

- QAP, which gives the optimal solution to the problem but, as it has an exponential cost, takes too long for n>20.
- Genetic Algorithm, which gives a pseudo-optimal solution. This AI approach has polynomial cost and thus can handle large problems.

For a broader understanding of the project, it is advisable to check out the [Algorithm Documentation](DOCS/QAPDocumentacion.pdf), [User Manual](DOCS/ManualDeUsuario.pdf) and [Javadoc](Javadoc/index.html).

## Group Members

- Sabaté Iturgaiz, Eneko 
- Aguilera Folqué, Mariona
- Montero Flores, Miguel Ángel
- Ribera Moreno, Pol

- [eneko.sabate@estudiantat.upc.edu]()
- [mariona.aguilera@estudiantat.upc.edu]()
- [miguel.angel.montero@estudiantat.upc.edu]()
- [pol.ribera@estudiantat.upc.edu]()

## Professor

- Ricardo Fernández Domenech ([ricardo.fernandez.domenech@upc.edu]())

## Usage

To run the app, follow these steps:

```
cd FONTS/src/
make all
make start
```

For more information regarding the usage of the app, check the [User Manual](DOCS/ManualDeUsuario.pdf).

## Directory Elements

Each folder (except lib and Javadoc) contains an index.txt file that further explains its contents.

## DOCS

Contains the documentation assigned to the third delivery. All the necessary information to use the program is found in a single PDF file (UserManual.pdf).

### EXE

The executable files (.class) of all implemented classes found in the FONTS folder are located here. All output is in the out folder, where the subdirectories of the corresponding classes are located.

### FONTS

It has a folder to store the necessary project information and the src folder, which contains all classes, including tests, drivers, exceptions, and all domain, persistence, and presentation classes.

Additionally, it contains the Makefile that generates the executables for all .java files.

### lib

Contains the necessary external libraries (JARs) to use the JUnit tests.

### Javadoc

Contains the Javadoc for all implemented code.



