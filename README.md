Documentation is done in Notion: https://andredemir.notion.site/andredemir/King-of-the-Castle-fd58ec3c7a534f3194e3da06d526b9d5

King of the castle is a multiplayer game which supports up to 8 participating players within the same network.
How to play:
1. One participant has to start the Server through the KingOfTheCastle/core/src/com/mygdx/kotc/server/Server.java class
2. Following the server start a hostname will be displayed through the command line
3. Each participating player then has to edit their config.cfg file and enter the hostname as serverHostnam
4. Afterwards the players can start their game through the KingOfTheCastle/desktop/src/com/mygdx/kotc/DesktopLauncher.java class
5. After startup the client automatically connects to the specified server and the player will be able to click play and choose his character afterwards
