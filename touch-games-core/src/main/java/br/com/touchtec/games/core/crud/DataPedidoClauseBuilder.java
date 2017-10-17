package br.com.touchtec.games.core.crud;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import br.com.touchtec.dali.crud.manager.CrudManager;
import br.com.touchtec.dali.crud.search.ClauseBuilder;
import br.com.touchtec.dali.crud.search.CrudQueryBuilder;
import br.com.touchtec.dali.target.Target;

public class DataPedidoClauseBuilder implements ClauseBuilder {

    @Override
    public void buildClause(Target target, CrudQueryBuilder builder, CrudManager manager) {
        Date dataAlvo = (Date) target.getValue();

        if (dataAlvo == null) {
            return;
        }

        builder.appendWhere("data >= :dataInicio AND data < :dataFim");

        builder.createParameter("dataInicio", DateUtils.truncate(dataAlvo, Calendar.DATE));
        builder.createParameter("dataFim", DateUtils.ceiling(dataAlvo, Calendar.DATE));
    }
}
