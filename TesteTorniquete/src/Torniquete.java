public class Torniquete {
    private Detector detector;
    private Trinco trinco;
    private Sirene sirene;
    private MaquinaEstados maquinaEstados;

    public Torniquete(){
        this.detector = new Detector();
        this.trinco = new Trinco();
        this.sirene = new Sirene();
        maquinaEstados = new MaquinaEstados();
    }

    public void executar(){
        EventoTorniquete evento = null;
        do {
            evento = detector.detectar();
            Accao accao = maquinaEstados.processar(evento);
            System.out.println("ACCAO: " + accao);
            switch(accao){
                case bloquear: trinco.bloquear(); break;
                case desbloquear: trinco.desbloquear(); break;
                case activar_sirene: sirene.activar(); break;
                case desactivar_sirene: sirene.desactivar(); break;

            }
        }
        while(evento != EventoTorniquete.REINICIAR);
    }
}
