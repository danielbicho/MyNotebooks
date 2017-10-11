from enum import Enum, auto


class Estado(Enum):
    BLOQUEADO = auto()
    DESBLOQUEADO = auto()
    ALARME = auto()
