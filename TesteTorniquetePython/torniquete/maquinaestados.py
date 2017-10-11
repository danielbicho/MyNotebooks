from collections import defaultdict

from torniquete.accao import Accao
from torniquete.estado import Estado
from torniquete.evento import Evento


class MaquinaEstados():
    def __init__(self):
        self.estado_atual = Estado.BLOQUEADO
        self.tae = defaultdict(Accao)
        self.tte = defaultdict(Estado)

        self.tte[(Estado.BLOQUEADO, Evento.CARTAO)] = Estado.DESBLOQUEADO
        self.tte[(Estado.DESBLOQUEADO, Evento.PASSAGEM)] = Estado.BLOQUEADO
        self.tte[(Estado.BLOQUEADO, Evento.PASSAGEM)] = Estado.ALARME
        self.tte[(Estado.ALARME, Evento.REINICIAR)] = Estado.BLOQUEADO

        self.tae[(Estado.BLOQUEADO, Evento.CARTAO)] = Accao.desbloquear
        self.tae[(Estado.DESBLOQUEADO, Evento.PASSAGEM)] = Accao.bloquear
        self.tae[(Estado.BLOQUEADO, Evento.PASSAGEM)] = Accao.activar_sirene
        self.tae[(Estado.ALARME, Evento.REINICIAR)] = Accao.desactivar_sirene

    def processar(self, evento):
        accao = self.tae.get((self.estado_atual, evento))
        novoEstado = self.tte.get((self.estado_atual, evento))
        if novoEstado:
            self.estado_atual = novoEstado
        return accao
