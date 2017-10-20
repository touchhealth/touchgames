package br.com.touchtec.games.web;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.PlataformaService;
import br.com.touchtec.games.core.service.impl.DesenvolvedoraServiceImpl;
import br.com.touchtec.games.core.service.impl.JogoServiceImpl;

@Component
public class Populator {

    private DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraServiceImpl();

    private JogoService jogoService = new JogoServiceImpl();

    @Autowired
    private PlataformaService plataformaService;

    private static final List<String> DESENVOLVEDORAS = newArrayList(
            "Bethesda",
            "Blizzard",
            "Firaxis Games",
            "Irrational Games",
            "Konami",
            "Nintendo",
            "Nippon Ichi",
            "Number None",
            "Playground Games",
            "SquareEnix",
            "Sony"
    );

    private static final List<String> JOGOS = newArrayList(
            "Chrono Trigger",
            "Final Fantasy VI",
            "FIFA"
    );

    private static final List<String> PLATAFORMAS = newArrayList(
            "Atari",
            "SNES"
    );

    @PostConstruct
    public void populateOnStartup() {
        List<Desenvolvedora> desenvolvedoras = desenvolvedoraService.buscarTodos();
        if (!desenvolvedoras.isEmpty()) {
            return;
        }

        System.out.println("Criando registros no banco...");

        for (String desenvolvedora : DESENVOLVEDORAS) {
            desenvolvedoraService.criar(newDesenvolvedora(desenvolvedora));
            System.out.println(desenvolvedora + " persistida.");
        }

        for (String jogo : JOGOS) {
            jogoService.criar(newJogo(jogo));
            System.out.println(jogo + " persistido.");
        }

        for (String plataforma : PLATAFORMAS) {
            plataformaService.criar(newPlataforma(plataforma));
            System.out.println(plataforma + " persistida.");
        }
    }

    private static Desenvolvedora newDesenvolvedora(String nome) {
        Desenvolvedora desenvolvedora = new Desenvolvedora();
        desenvolvedora.setNome(nome);
        return desenvolvedora;
    }

    private static Jogo newJogo(String nome) {
        Jogo jogo = new Jogo();
        jogo.setNome(nome);
        jogo.setPreco(140F);
        return jogo;
    }

    private static Plataforma newPlataforma(String nome) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);
        return plataforma;
    }

}
