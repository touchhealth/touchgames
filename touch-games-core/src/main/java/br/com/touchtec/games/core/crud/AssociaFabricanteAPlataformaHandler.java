package br.com.touchtec.games.core.crud;

import java.util.Collections;
import java.util.List;

import br.com.touchtec.dali.crud.operation.OperationChain;
import br.com.touchtec.dali.crud.operation.OperationHandler;
import br.com.touchtec.games.core.model.Fabricante;
import br.com.touchtec.games.core.model.Plataforma;

/**
 * Uma forma mais simples de resolver relacionamentos bidirecionais e nao precisar deste handler
 * é fazendo o processo descrito aqui: http://showcase.touchtec.com.br/dali-showcase/relationship/bidirectional.action
 */
public class AssociaFabricanteAPlataformaHandler implements OperationHandler {

    @Override
    public void handle(OperationChain chain) {
        Fabricante fabricante = chain.getEntity();
        List<Plataforma> plataformas = fabricante.getPlataformas();



        for (Plataforma plataforma : plataformas) {
            plataforma.setFabricante(fabricante);
        }

        // invoca os próximos handlers;
        // sem isso, a chain é interrompida e nenhum handler seguinte será executado
        chain.doChain();
    }

}
