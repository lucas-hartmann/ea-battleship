# Setup
Wird nicht benötigt (standard Spring)

H2 Database - alle Infos im application.properties
- localhost:8081/h2-console
- localhost:8082/h2-console
- localhost:8083/h2-console

RabbitMQ Container - starten für createPlayer
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

# How to use
- Game erstellen (50x50 grid)
- Spieler hinzufuegen
- Schiffe hinzufuegen (aktuell nur 1x1)
- Mit Guess raten, wo Schiffe stehen
- Mit display spielfeld anzeigen

"~" ist Wasser, Schiffe haben die Spieler ID, wenn sie getroffen sind "x"
