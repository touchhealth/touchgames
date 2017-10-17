package br.com.touchtec.games.web;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.impl.DesenvolvedoraServiceImpl;
import br.com.touchtec.games.core.service.impl.JogoServiceImpl;

@Component
public class Populator {

    private DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraServiceImpl();

    private JogoService jogoService = new JogoServiceImpl();

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
    }

    private static Desenvolvedora newDesenvolvedora(String nome) {
        Desenvolvedora desenvolvedora = new Desenvolvedora();
        desenvolvedora.setNome(nome);
        return desenvolvedora;
    }

    private static Jogo newJogo(String nome) {
        Jogo jogo = new Jogo();
        jogo.setNome(nome);
        return jogo;
    }

}
