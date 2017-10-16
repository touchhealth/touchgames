package br.com.touchtec.games.web;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.impl.DesenvolvedoraServiceImpl;

@Component
public class Populator {

    private DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraServiceImpl();

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

    @PostConstruct
    public void populateOnStartup() {
        List<Desenvolvedora> desenvolvedoras = desenvolvedoraService.listarTodos();
        if (!desenvolvedoras.isEmpty()) {
            return;
        }

        System.out.println("Criando desenvolvedoras no banco...");

        for (String desenvolvedora : DESENVOLVEDORAS) {
            desenvolvedoraService.criar(newDesenvolvedora(desenvolvedora));
            System.out.println(desenvolvedora + " persistida.");
        }
    }

    private static Desenvolvedora newDesenvolvedora(String nome) {
        Desenvolvedora desenvolvedora = new Desenvolvedora();
        desenvolvedora.setNome(nome);
        return desenvolvedora;
    }

}
