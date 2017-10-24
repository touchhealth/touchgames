package br.com.touchtec.games.web;

import static br.com.touchtec.games.core.model.Genero.ACAO;
import static br.com.touchtec.games.core.model.Genero.RPG;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.log.ANSIColor;
import br.com.touchtec.persistence.dao.GenericDAO;

@Component
public class Populator {

    @Autowired
    private GenericDAO dao;


    @PostConstruct
    public void populateOnStartup() {
        Long totalJogos = dao.count(Jogo.class, null);
        if (totalJogos > 0) {
            return;
        }

        System.out.println(ANSIColor.info("Criando registros no banco..."));

        Desenvolvedora namco = createDesenvolvedora("Namco");
        Desenvolvedora squareEnix = createDesenvolvedora("SquareEnix");
        Desenvolvedora nintendo = createDesenvolvedora("Nintendo");
        Desenvolvedora sega = createDesenvolvedora("Sega");

        Plataforma atari = createPlataforma("Atari");
        Plataforma megaDrive = createPlataforma("Mega Drive");
        Plataforma snes = createPlataforma("SNES");

        createJogo("Pacman", ACAO, namco, atari);
        createJogo("Chrono Trigger", RPG, squareEnix, snes);
        createJogo("Final Fantasy VI", RPG, squareEnix, snes);
        createJogo("Super Mario World", ACAO, nintendo, snes);
        createJogo("Sonic II", ACAO, sega, megaDrive);

    }

    private Jogo createJogo(String nome, Genero genero, Desenvolvedora desenvolvedora, Plataforma plataforma) {
        Jogo jogo = new Jogo();
        jogo.setNome(nome);
        jogo.setPreco(140F);
        jogo.setGenero(genero);

        jogo.setDesenvolvedora(desenvolvedora);

        jogo.getPlataformas().add(plataforma);

        return dao.save(jogo);
    }


    private Desenvolvedora createDesenvolvedora(String nome) {
        Desenvolvedora desenvolvedora = new Desenvolvedora();
        desenvolvedora.setNome(nome);
        return dao.save(desenvolvedora);
    }


    private Plataforma createPlataforma(String nome) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);
        return dao.save(plataforma);
    }

}
