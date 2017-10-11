from torniquete.maquinaestados import MaquinaEstados
from torniquete.sirene import Sirene
from torniquete.detector import Detector
from torniquete.trinco import Trinco
from torniquete.evento import Evento
from torniquete.accao import Accao


class Torniquete():
    def __init__(self):
        self._maqEst = MaquinaEstados()
        self._detector = Detector()
        self._trinco = Trinco()
        self._sirene = Sirene()

    def _processar(self, evento):
        pass

    def executar(self):
        evento = None
        while evento != Evento.REINICIAR:
            evento = self._detector.detectar()
            accao = self._maqEst.processar(evento)
            if accao == Accao.bloquear:
                self._trinco.bloquear()
            elif accao == Accao.desbloquear:
                self._trinco.desbloquear()
            elif accao == Accao.activar_sirene:
                self._sirene.activar()
            elif accao == Accao.desactivar_sirene:
                self._sirene.desactivar()
