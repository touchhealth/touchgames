package br.com.touchtec.games.core.crud;

import static br.com.touchtec.log.ANSIColor.ForegroundColor.FOREGROUND_MAGENTA;

import org.apache.log4j.Logger;

import br.com.touchtec.dali.crud.operation.OperationChain;
import br.com.touchtec.dali.crud.operation.OperationHandler;
import br.com.touchtec.log.ANSIColor;

public class LogCriacaoRegistrosHandler implements OperationHandler {

    private static final Logger LOG = Logger.getLogger(LogCriacaoRegistrosHandler.class);

    @Override
    public void handle(OperationChain chain) {
        Object entity = chain.getEntity();

        LOG.info(ANSIColor.color(FOREGROUND_MAGENTA, "Registro criado: " + entity));

        // Sem esta chamada, os próximos handlers não são executados
        // Às vezes queremos abortar a chain mesmo; neste caso, não chamaríamos doChain()
        chain.doChain();
    }

}
