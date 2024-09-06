/** Datei: README.txt
  *
  * Beschreibung
  * Enthält alle relevanten Informationen um
  * im Kurs Q3 - Datenkommunikation des 
  * BG Datenverarbeitungstechnik ein zusteigen 
  *
  * @version 2.0 vom 27.08.2020
  * @author jHunter
  */


Vorgehen zur Einrichtung und Installation des TWusb Board an einem 64bit System  

 

    TWusb Board benötigt 32bit Java JRE (x86)  

        Download https://www.chip.de/downloads/Java-Runtime-Environment-32-Bit_13014576.html  

    Installieren der JRE  

    Kopieren der DLL Dateien in die Ordner  

    C:\WINDOWS\system32 

    C:\Windows\SysWOW64 

    Kopieren der JAR Dateien in die Root des Workspace  

    Starte Eclipse 

        Erstelle einen Workspace  

        Erstelle ein Projekt, Package ( Board ) und eine Klasse ( Zaehler ) 

    Nach dem Starten von eclipse wie folgt vorgehen: 

        Ein Rechtsklick auf das Projekt und den Menüpunkt "Properties" wählen 

        In dem sich öffnenden Fenster den Menüpunkt "Java Build Path" wählen 

        Dort den Unterpunkt "Libraries" wählen 

        Den Button "Add External JARs..." anklicken und alle drei .jar-Dateien aus dem Ordner (JAR_Dateien) importieren  

        Ändern der JRE Umgebung auf x86 ( siehe Bilder Reihe )  

        Eclipse per Restart neustarten  

    Code aus dem Testprogramm (Zaehler.java) kopieren und einfügen in die eigene Klasse  

    USB Board anschließen , warten bis USB Treiber fertig ist  

    Programm ausführen  

 