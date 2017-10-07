import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import java.util.List;

public class MaquinaEstados {

    private Table<Estado,EventoTorniquete,Accao> tae;
    private Table<Estado,EventoTorniquete,Estado> tte;
    private Estado estadoActual = Estado.BLOQUEADO;

    public MaquinaEstados(){
        // Inicializar TAE
        List<Estado> estadoRowTableTae = Lists.newArrayList(Estado.BLOQUEADO,Estado.DESBLOQUEADO, Estado.ALARME);
        List<EventoTorniquete> eventoColumnTableTae = Lists.newArrayList(EventoTorniquete.CARTAO,
                EventoTorniquete.PASSAGEM, EventoTorniquete.REINICIAR);
        this.tae = ArrayTable.create(estadoRowTableTae, eventoColumnTableTae);
        // Popular com valores TAE
        tae.put(Estado.BLOQUEADO, EventoTorniquete.CARTAO, Accao.desbloquear);
        tae.put(Estado.DESBLOQUEADO, EventoTorniquete.PASSAGEM, Accao.bloquear);
        tae.put(Estado.BLOQUEADO, EventoTorniquete.PASSAGEM, Accao.activar_sirene);
        tae.put(Estado.ALARME, EventoTorniquete.REINICIAR, Accao.desactivar_sirene);

        // Inicializar TTE
        List<Estado> estadoRowTableTte = Lists.newArrayList(Estado.BLOQUEADO,Estado.DESBLOQUEADO, Estado.ALARME);
        List<EventoTorniquete> eventoColumnTablette = Lists.newArrayList(EventoTorniquete.CARTAO,
                EventoTorniquete.PASSAGEM, EventoTorniquete.REINICIAR);
        this.tte = ArrayTable.create(estadoRowTableTte, eventoColumnTablette);
        // Popular com valores TTE
        tte.put(Estado.BLOQUEADO, EventoTorniquete.CARTAO, Estado.DESBLOQUEADO);
        tte.put(Estado.DESBLOQUEADO, EventoTorniquete.PASSAGEM, Estado.BLOQUEADO);
        tte.put(Estado.BLOQUEADO, EventoTorniquete.PASSAGEM, Estado.ALARME);
        tte.put(Estado.ALARME, EventoTorniquete.REINICIAR, Estado.BLOQUEADO);
    }

    public Accao processar(EventoTorniquete eventoTorniquete){
        Accao accao = tae.get(estadoActual, eventoTorniquete);
        Estado novoEstado = tte.get(estadoActual, eventoTorniquete);
        if (novoEstado != null){
            estadoActual = novoEstado;
        }
        return accao;
    }
}
