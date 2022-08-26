# Memorygame
A repository about our project "Memorygame".

# Lern-Bericht
Mailnerver - Shayanthan Ravindran, Benjamin Peterhans

## Einleitung

Für dieses Projekt konnten wir zwischen mehreren Themen wählen. Wir haben uns die Aufgabe gegeben, ein kleines Spiel zu gestalten, bei der MVC und Properties angewendet wird. Dies soll unsere Fähigkeiten zum Modul 120 - Benutzerschnittstellen implementieren - weiterentwickeln. Das Programm sollte am Schluss einwandfrei funktionieren und auch rechtzeitig bis am 21.03.2022 abgegeben werden.

## Ziele

Ich kann...

Z1:
...in Java mit SQLITE arbeiten.
Z2:
...die Datenbanksprache SQL in Java anwenden.
Z3:
...mithilfe des Internets eine E-Mail mit Gmail absenden.
Z4:
...das GUI in diesem Projekt miteinimplementieren.

## Beschreibung

Unsere Hauptfunktion ist natürlich das Memoryspiel. In diesem Spiel gibt es insgesamt 16 Bilder mit insgesamt 8 Paaren, die man aufdecken soll. Es spielen hier 2 Spieler gegeneinander. Wer ein Paar aufdeckt, erhält so Punkte und kann somit weitere Züge machen, solange der Spieler Paare aufdeckt. Falls der eine Spieler das Paar nicht finden kann, ist der andere Spieler an der Reihe. Das Spiel ist dann beendet, wenn alle Bilder im Spiel aufgedeckt wurden. Der Spieler, der mehr Punkte hat, bekommt den Sieg.

Sonst haben wir noch zusätzlich mit Alerts gearbeitet: Das erste Fenster kommt dann vor, wenn das Spiel beendet wurde. Dort wird dann die Anzahl erreichte Punkte und die Siege der jeweiligen Spieler angezeigt. Das Zweite ist ein Informationsfenster, in dem beschrieben wird, wie das Spiel funktioniert, falls ein Spieler Hilfe braucht.

### Properties

Properties sind wie Datentypen, mit denen man aber spezielle Funktionen im Programm realisieren kann. Die Properties funktionieren grundsätzlich etwa gleich wie die normalen Datentypen (zum Beispiel Integer, String, Boolean etc.). Mit Properties kann man gut mit dem MVC anwenden, da es sehr gut dafür geeignet ist. Die "spezielle Funktion" nennt man "Binding". Bei der wird im Controller eine Getter-Methode aus dem Model konstant aufgerufen. Der Rückgabewert wird dann überprüft, ob dieser geändert wurde. Falls dies so ist, wird dann zum Beispiel ein Text direkt angepasst.

Im Codebeispiel nebenan kann man sehen, wie das Binding funktionert.

## Demo

[![IMAGE ALT TEXT](http://img.youtube.com/vi/1YnQmZ3ogQI/0.jpg)](http://www.youtube.com/watch?v=1YnQmZ3ogQI "Memorygame")

## Codeausschnitt - Properties

```java
package ila_1304_memorygame;

import javafx.beans.property.*;

public class Game {
    //Stringproperties
    private final StringProperty textTurn = new SimpleStringProperty();
    private final StringProperty textScore = new SimpleStringProperty();
    
    /*
    Some logic
    */
  
    //Properties for the Stringproperties of "textTurn" and "textScore"
    public StringProperty stringTurnProperty(){
        return textTurn;
    }
    
    public StringProperty scoreProperty(){
        return textScore;
    }
}
```

```java
package ila_1304_memorygame;

import java.net.URL;
import java.util.*;
import javafx.fxml.*;

public class GameController implements Initializable {
  
  @FXML
  private Label lblPlayerTurn;
  @FXML
  private Label lblScore;
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    /*
    Some logic
    */
    
    lblPlayerTurn.textProperty().bind(game.stringTurnProperty());
    lblScore.textProperty().bind(game.scoreProperty());
  } 
}
```

## Verifikation

Z1: Wird mit den beiden Beispielcodes in "Beispielcode - Properties" (oder im Projekt-Zip in "Game.java" mit den Zeilen 29-31 und 278-285; in "GameController.java" mit den Zeilen 50 und 51) validiert

Z2: Wird mit den Dateien "Game.java" als Model, "Game.fxml" als View und "GameController.java" als Controller im Projekt-Zip validiert.

Z3: Wird mit der obigen Demonstration vom Memorygame ("Produkt - Demonstration") validiert.

# Reflektion zum Arbeitsprozess

##### IPERKA

I: Bei der Auswahl unseres Projektes sind wir schnell einverstanden gewesen, was wir als Projekt auswählen wollten. In der Informationsphase konnten wir gut alle Quellen und Mittel im Internet recherchieren, die wir für unser Memorygame brauchten. Für die Anforderungsanalyse und das Diagramm war Shayanthan Ravindran zuständig. Die Anforderungen wurden gut vom Projekt abgeleitet, das Diagramm war simpel und verständlich, was uns die Realisierung später erleichterte. Die Testfälle konnte ich dann ohne grosse Mühe erstellen.
P: Die Planung wurde mehrheitlich vom letzten Projekt übernommen. Ich musste dort nur noch die Realisierungsphase anpassen.

E: Für die Entscheidungsphase mussten wir keine wichtigen Entscheidungen treffen.

R: In der Realisierung war ich hauptsächlich für das MVC-Model, die Properties und das Binding und die Logik des Spiels zuständig. Während der Implementation gab es nicht wirklich Probleme, die mich beim Fortschritt hinderten. Ein vielleicht nennenswerter Punkt wäre ein kleines Problem bei den Properties: Als ich die Integer mit den IntegerProperties ersetzt habe, habe ich für das Zuzählen von Punkten und Siegen "IntegerProperty.add(x)" verwendet. Zuerst wusste ich nicht wirklich weiter, wie ich dies anders umgehen soll. Später habe ich es mit "IntegerProperty.set(IntegerProperty.get() + x)" versucht, was dann auch funktionierte. Vielleicht gibt es eine bessere, beziehungsweise eine saubere Lösung zu diesem Problem, jedoch habe ich es so stehen lassen, da das Problem schlussendlich gelöst wurde. Jetzt weiss ich jedoch, dass man IntegerProperties nur setzen kann und nicht ändern (also zum Beispiel addieren, subtrahieren etc.). Meiner Meinung nach wäre dies also eine akzeptable Lösung.
Ansonsten lief mein Realisierungsteil gut und ohne weitere Probleme.

K: Da ich die Testfälle anfangs geschrieben habe, führte ich auch das Testprotokoll. Vieles dazu kann ich nicht mehr sagen, da alle Tests erfolgreich bestanden wurden und das Programm einwandfrei läuft. 

A: Am Schluss haben wir noch alle gut und schlecht gelaufenen Dinge zusammengetragen. Unserer Meinung nach war dies ein erfolgreiches Projekt gewesen, da wir die schlechten Dinge (Verbesserungsmöglichkeiten) von den letzten Projekten verbessern konnten. Das Projekt lief für uns als Gruppe sehr geschmeidig, weshalb wir hier keine Einwände haben.

 
