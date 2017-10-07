import java.util.Scanner;

public class Detector {
    public EventoTorniquete detectar(){
        return processar();
    }

    public EventoTorniquete processar(){
        System.out.println("Evento:");
        Scanner scanner = new Scanner(System.in);
        String strEvento = scanner.nextLine();
        EventoTorniquete eventoTorniquete = null;
        switch (strEvento){
            case "CARTAO": eventoTorniquete = EventoTorniquete.CARTAO; break;
            case "PASSAGEM": eventoTorniquete = EventoTorniquete.PASSAGEM; break;
            case "REINICIAR": eventoTorniquete = EventoTorniquete.REINICIAR; break;
        }
        return eventoTorniquete;
    }
}