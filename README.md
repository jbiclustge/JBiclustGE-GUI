# JBiclustGE-GUI

This repository contains the source-code of the graphical interfaces concerning the features provided in [JBiclustGE API](https://github.com/jbiclustge/JBiclustGE)

To make use of all these features, you need to compile the current release. Just follow the instructions presented bellow. 

Next, please visit the [website](https://jbiclustge.github.io) to see the usage instructions.

## Compile JBiclustGE-GUI
### Requirements
 - [Java SDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 
 - [Git](https://git-scm.com)
 - [Maven](https://maven.apache.org)

### Compile

1. Install git 
  - Follow the instruction to install [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
  - Run the following command in console:
     ```
     git clone https://github.com/jbiclustge/JBiclustGE-GUI.git
     ```

2. Install maven
  - Follow the instructions to install [Maven](https://maven.apache.org/install.html)
  - Windows users have to define JAVA_HOME to JDK, follow this [instructions](http://roufid.com/no-compiler-is-provided-in-this-environment/)

3. Enter into the folder of JBiclustGE-GUI (downloaded in the previous step)
   - The compilation process produces verbose output, presenting all the operations that are being performed in compilation. Thus, you can perform the compilation in two ways:
      - Quiet Mode, presenting only the errors:
           ```
           mvn clean package -q
           ```
           
      - Normal Mode, presenting the main operations occurring in compilation:
           ```
           mvn clean package
           ```      

3. Installers will be placed into folder "target"
    - File "jbiclustge-installer.jar" is the installer that can be used in windows or linux
    - File "jbiclustge.deb" is the deb installer that can be used only in linux

### Compiled versions

If you do not want to compile the release from git repository, you can download one of the following installers:
   - [Jar installer](https://jbiclustge.github.io/configs/download/#windows-and-linux-jar-installer) for windows and linux
   - [Deb installer](https://jbiclustge.github.io/configs/download/#linux-deb-installer) for linux

## Installation

To proceed with installation using jbiclustge-installer.jar, follow this [instructions](https://jbiclustge.github.io/configs/download/#windows-and-linux-jar-installer)

Otherwise if you are using the jbiclustge.deb, follow this [instructions](https://jbiclustge.github.io/configs/download/#linux-deb-installer)


## Manual
See usage instructions in the following [section](https://jbiclustge.github.io/manual/manualjbiclustgegui/)

