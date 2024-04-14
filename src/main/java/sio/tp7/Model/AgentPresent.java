package sio.tp7.Model;

public class AgentPresent
{
private String numeroAgent;
private String nomAgent;
private String prenomAgent;
private boolean presenceAgent;

    public AgentPresent(String numeroAgent, String nomAgent, String prenomAgent, boolean presenceAgent) {
        this.numeroAgent = numeroAgent;
        this.nomAgent = nomAgent;
        this.prenomAgent = prenomAgent;
        this.presenceAgent = presenceAgent;
    }

    public boolean isPresenceAgent() {
        return presenceAgent;
    }

    public String getNumeroAgent() {
        return numeroAgent;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public String getPrenomAgent() {
        return prenomAgent;
    }
}
