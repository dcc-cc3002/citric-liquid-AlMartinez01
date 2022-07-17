package cl.uchile.dcc.citricliquid.model.controller.Transferencia.BossEvent;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;

public interface Solicitor {
    UnitsEnemy solicitar(Informer informer);
}
