# Touch Games

[Demo](http://showcase.touchtec.com.br:8080/touch-games/Compras.action)

[Tutorial](tutorial/index.md)


# Ambiente

## POSTGRES

```
sudo apt-get install postgresql-9.1
sudo su - postgres
psql -d postgres -U postgres
alter user postgres with password 'postgres';
```

Em produção:
```
Time Machine: touch-games
```

## Docker
`docker run -it --rm -v $PWD/touch-games-web/target/:/opt/apache-tomcat-7.0.78/webapps -p 8081:8080 --link postgres docker.touchtec.com.br:5000/tomcat-cas-client:2.1.0`

# Cadastro
```
Fabricantes
---
Microsoft
Nintendo
Sony
Muitos

Plataformas
---
SNES: Nintendo
3DS: Nintendo
PS3: Sony
PC: Muitos
XBOX 360: Microsoft
WIIU: Nintendo


Desenvolvedoras
---
SquareEnix
Sony
Nippon Ichi
Blizzard
Number None
Playground Games
Bethesda
Firaxis Games
Irrational Games
Konami
Nintendo


Jogos
---
Chrono Trigger -> SquareEnix -> RPG
SNES
3DS

Bioshock Infinite -> Irrational Games -> ACAO
PC
PS3
XBOX 360

Braid -> Number None -> ACAO
PC

Diablo III -> Blizzard -> RPG
PC
PS3

Disgaea D2 -> Nippon Ichi -> RPG
PS3

Final Fantasy XIV -> SquareEnix -> RPG
PC
PS3

Forza Horizon -> Playground Games -> Esporte
XBOX 360

Pikmin III -> Nintendo -> ACAO
WIIU

Puppetter -> Sony -> ACAO
PS3

The Elder Scrolls V: Skyrim -> Bethesda -> RPG
PC
PS3
XBOX 360

The Last Remnant -> SquareEnix -> RPG
PC
XBOX 360

XCOM: Enemy Unknown -> Firaxis Games -> RPG
PC
PS3
XBOX 360

Final Fantasy VI -> SquareEnix -> RPG
SNES

PES 2013 -> Konami -> Esporte
3DS
PS3
PC
XBOX 360

Mario Tennis Open -> Nintendo -> Esporte
3DS

Gran Turismo 5 -> Sony -> Esporte
PS3
```