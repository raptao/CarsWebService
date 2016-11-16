# Auteurs

- FRECHET Nicolas
- HATHAT Mohamed
- RABEARIJAO Maminirina Thierry

# Projet

Le nom du projet est : **MLVCarWebService**

## Description et but du projet

Le but du projet est l'élaboration d'un service de partage 
de véhicules électriques aux enseignants et étudiants de l'université de 
Paris-Est Marne-la-Vallée.


# Contenus de ce dossier CarService

Ci-dessous est détaillé le contenu du dossier courant **CarService**

## Dossier: docs

Le dossier **docs** contient toutes les documentations sur le projet:

- Rapport
- Manuel d'utilisation de l'application
- Installation du projet.
- ce README en pdf ( pour une meilleure lisibilité )

## Dossier: conf 

Ce dossier contient des fichiers de configuration pour pouvoir déployer le projet facilement sur un serveur **tomcat**.

## Dossier: bin

Ce dossier contient l'**executable du serveur rmi** et le fichier **war** qui sera déployé sur un serveur **tomcat**.

## Projet Maven

Nous avons utilisé `maven` pour faciliter notre processus de build.
Le projet contient **4** modules : 

- **CarWebService** : le module parent de tous les autres modules.
- **RMIClient** : le module qui est le client du serveur rmi
- **RMIInterface** : ce module contient les interfaces communes entre le serveur rmi et le client rmi
- **RMIServer** : le module qui sera le serveur rmi.


### Module: CarWebService

Ce module ne contient pas de build particulier, il sert afin de pouvoir regrouper les autres modules et
afin de lancer les _build_ ( entre autre la compilation des sources et le "packaging" des composants )
de ses sous-modules en une seule commande.

### Module: RMIClient

Ce module est l'un des composants les plus importants du projet.
Il contient tous les fichiers sources du **client rmi**.

Il génèrera le fichier **car-webservice.war** après son build.

## Module: RMIInterface

Ce module sert d'intermédiaire entre le server et le client, il est nécessaire pour éviter des configurations manuelles, 
comme par exemple:
> faire un copier coller des classes des interfaces afin que le client contient les interfaces pour manipuler le serveur


### Module: RMIServer

Ce module est l'un des composants les plus importants du projet.
Il contient tous les fichiers sources du **serveur rmi**.

Il génèrera le fichier **car-loan.server.jar** après son build.

> **Remarque**: nous entrons plus en détail sur chaque module dans le rapport 
qui se trouve dans le dossier **docs/**


## Compilation et installation

Il est nécessaire d'avoir **maven** installé sur votre ordinateur.

Maven peut facilement être téléchargé sur le [**site d'Apache**](https://maven.apache.org/download.cgi)

### Processus de build

Pour compiler les sources du projet avec maven: 

à partir d'un terminal, entrez dans le dossier **CarWebService/** et entrez les commandes suivantes:

```cmd
mvn clean install
```

Cette commande peut prendre du temps car elle va télécharger toutes les dépendances 
requises pour faire le **packaging** du projet.

Elle génèrera les deux fichiers : 

- car-webservice.war ( pour le client rmi) dans le dossier `RMIClient/target`
- car-loan.server.jar ( pour le serveur rmi ) dans le dossier `RMIServer/target`


### Déploiement sur Tomcat

Il est nécessaire d'avoir **tomcat** installé sur votre ordinateur.

Tomcat ( version 8.5 recommandée ) peut facilement être téléchargé 
sur le [**site d'Apache**](https://tomcat.apache.org/download-80.cgi#8.5.8)


#### Méthode 1

Après avoir lancé le serveur tomcat, dans le même dossier **CarWebService/**, 
avec votre terminal, entrez la commande suivante:

```cmd
mvn tomcat7:deploy
```

<Context antiResourceLocking="true">


Il est attendu que le déploiement ne marche pas seulement en lancant cette commande, 
c'est pour cela que nous avons des fichiers de configuration dans **conf/**

- Copier/coller le fichier (ou son contenu) **tomcat-users.xml** dans le dossier `conf/` de votre serveur tomcat.
- Copier/coller le fichier (ou son contenu) **settings.xml**  dossier `$HOME/.m2/` 
(où $HOME pointe vers votre dossier d'utilisateur)

Relancez la commande de déploiement.

#### Méthode 2

Cette méthode est plus facile, mais est manuelle.

Il vous suffit de copier/coller le fichier **car-webservice.war** dans le dossier `webapps/` de votre serveur tomcat.



Après déploiement de l'application sur le serveur tomcat et elle sera accessible sur :

**localhost:8080/car-webservice**, à partir de votre navigateur web.


## Prérequis d'utilisation de l'application

Afin de pouvoir utiliser et aprécier toutes les fonctionnalités qu'offrent le service, 
vous aurez besoin:

- de lancer le serveur rmi avec la commande `java -jar car-loan.server.jar`
- d'avoir une connexion internet.

# Remerciements

Merci d'avoir lu ce fichier README jusqu'à la fin.
Vous pouvez maintenant apprécier le service de partage/location de véhicules.