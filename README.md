Documentación Prueba Técnica – QA Automation

Implementación de Marco de Trabajo:
•	Lenguaje de Programación: Java
•	Sistema de Construcción: Gradle (v 8.9)
•	JDK: 17.0.10

Repositorio GitHub
https://github.com/franciscomendozatamayo/PruebaTecnicaOrbi.git


Configuración build.gradle: Este archivo define las configuraciones del proyecto, las dependencias necesarias, y las tareas que se ejecutan para compilar, probar y desplegar el software. En un proyecto de pruebas automatizadas, build.gradle es esencial para gestionar las dependencias de prueba, configurar los plugins necesarios, y automatizar la ejecución de las pruebas.


Configuración de Marco de Trabajo
•	src/main/java/activities: contiene las clases Java correspondientes a las actividades de la aplicación móvil en el proyecto de pruebas automatizadas. Cada actividad representada en este directorio reflejará una pantalla o funcionalidad específica dentro de la aplicación móvil que se está probando.

•	src/main/java/config/EnvoronmentConfig.java: El archivo EnvironmentConfig.java es una clase que centraliza la configuración del entorno de prueba. Este archivo permite definir, establecer y gestionar configuraciones específicas.

•	src/main/java/config/ReadConfig.java: El archivo ReadConfig.java es una clase fundamental dentro del proyecto, diseñada para gestionar la carga y acceso a configuraciones de prueba desde archivos externos. Este archivo proporciona una manera uniforme y centralizada de acceder a diferentes configuraciones necesarias para la ejecución de las pruebas automatizadas, en este caso los recursos son extraidos de src/test/resources/qa.properties.

•	src/main/java/core/Driver.java: El archivo Driver.java dentro del directorio src/main/java/core es una clase esencial que actúa como una fábrica de controladores (drivers) para los navegadores o emuladores/dispositivos móviles utilizados en las pruebas automatizadas. Este archivo centraliza la lógica de inicialización, configuración y gestión de los controladores de Selenium WebDriver o Appium Driver.

•	src/main/java/pages: contiene las clases Java correspondientes a los pages de la aplicación web en el proyecto de pruebas automatizadas. Cada page representada en este directorio reflejará una pantalla o funcionalidad específica dentro de la aplicación web que se está probando.

•	src/test/java/report/ReportGenerator.java: El archivo ReportGenerator.java dentro del directorio src/test/java/report es una clase dedicada a la generación de informes de resultados de pruebas automatizadas. Este se personaliza bajo la demanda del cliente ya que se puede configurar el logo de la empresa y ciertas etiquetas de información.

•	src/test/java/runner/hook/Hooks.java: El archivo Hooks.java en el directorio src/test/java/runner/hook es una clase que contiene métodos de configuración y limpieza (setup y teardown) que se ejecutan antes y después de cada prueba o conjunto de pruebas. Estos métodos son conocidos como "hooks" y se utilizan para gestionar el estado y el entorno de las pruebas automatizadas.

•	src/test/java/runner/steps: El directorio src/test/java/runner/steps contiene clases que implementan los pasos de los escenarios de prueba definidos en los archivos de características (features) de Cucumber. Estas clases son esenciales para conectar los escenarios de prueba escritos en lenguaje Gherkin con el código de automatización que interactúa con la aplicación bajo prueba.

•	src/test/resources/testdata/logo/logo.png: El archivo logo.png ubicado en src/test/resources/testdata/logo/ es un recurso gráfico utilizado en el proyecto de pruebas automatizadas. Este archivo puede servir para varias finalidades relacionadas con las pruebas, como la validación de la presencia y la integridad de un logotipo dentro de la aplicación bajo prueba o para incluir en informes de prueba personalizados.

•	src/test/resources/qa.properties: El archivo qa.properties ubicado en src/test/resources/ es un archivo de configuración que contiene propiedades clave y valores utilizados para definir el entorno de prueba de calidad (QA) en el proyecto de automatización de pruebas. Este archivo es fundamental para parametrizar las pruebas y asegurar que se ejecuten en un entorno controlado y consistente.


Limpiar proyecto:
./gradlew clean build


Ejecución de proyecto
gradle cucumber -Psuite=@Mobile ; gradle report

Tags de ejecución
Mobile: gradle cucumber -Psuite=@Mobile ; gradle report
API: gradle cucumber -Psuite=@APIOrbi ; gradle report

Reporte de Ejecución
Path donde se genera reporte HTML Cucumber:
build/reports/cucumber/Orbi/cucumber-html-reports/overview-features.html






