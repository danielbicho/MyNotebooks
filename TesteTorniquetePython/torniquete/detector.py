from torniquete.evento import Evento


class Detector():
    def detectar(self):
        return self.processar()

    def processar(self):
        input_value = input("Evento: ")
        dicEventos = {"CARTAO": Evento.CARTAO,
                      "PASSAGEM": Evento.PASSAGEM,
                      "REINICIAR": Evento.REINICIAR}

        eventoTorniquete = dicEventos.get(input_value, '')
        return eventoTorniquete
